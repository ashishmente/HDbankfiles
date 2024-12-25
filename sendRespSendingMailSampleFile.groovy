import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import java.util.Random;
import java.util.Arrays;
import java.util.List;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.MailException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

public class TransactionProcessor implements Processor {

    private JavaMailSender mailSender;

    // Inject JavaMailSender through setter injection
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void process(Exchange exchange) throws Exception {
        // Retrieve the incoming request body as a string
        String responseBody = exchange.getIn().getBody(String.class);
        System.out.println("response body is :" + responseBody);

        // Extract data from the response
        String CustomerId = responseBody.substring(665, 695).trim();
        String ChannelRef = responseBody.substring(695, 725).trim();
        String randomEfmsResponse = responseBody.substring(635, 665).trim();
        String mailindicator = "";

        if (!ChannelRef.isEmpty()) {
            // Construct the JSON response as a String
            jsonResponse = String.format(
                "{\"Data\": {\"EFMSChannelReferenceNumber\": \"%s\", \"CustomerId\": \"%s\", \"EFMSResponse\": \"%s\"}}",
                ChannelRef, CustomerId, randomEfmsResponse
            );
        } else {
            jsonResponse = String.format(
                "{\"Data\": {\"CustomerId\": \"%s\", \"EFMSResponse\": \"%s\"}}",
                CustomerId, randomEfmsResponse
            );
        }

        // Set the JSON response as the exchange body
        exchange.getIn().setBody(jsonResponse);

        // Fetch the email ID using the CustomerId from the database (mocked for simplicity)
        String emailId = "";
		if(mailindicator=="Yes")
		{
        if (emailId != null && !emailId.isEmpty()) {
            // Send email to the customer
            sendEmail(emailId, "Transaction Details", "sample mail");
        }
		}
    }

    private void sendEmail(String toEmail, String subject, String body) throws MailException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        try {
            helper.setFrom("your-email@gmail.com"); // Sender email address
            helper.setTo(toEmail); // Recipient email address
            helper.setSubject(subject); // Subject of the email
            helper.setText(body); // Body of the email

            mailSender.send(message); // Send the email
            System.out.println("Email sent successfully to: " + toEmail);
        } catch (MessagingException e) {
            System.err.println("Error sending email: " + e.getMessage());
        }
    }
}

<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans 
                           http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!-- JavaMailSender Bean Configuration -->
    <bean id="mailSender" class="org.springframework.mail.javamail.JavaMailSenderImpl">
        <property name="host" value="smtp.gmail.com" />
        <property name="port" value="587" />
        <property name="username" value="your-email@gmail.com" />
        <property name="password" value="your-email-password" />
        <property name="javaMailProperties">
            <props>
                <prop key="mail.smtp.auth">true</prop>
                <prop key="mail.smtp.starttls.enable">true</prop>
                <prop key="mail.smtp.debug">true</prop>
            </props>
        </property>
    </bean>

</beans>
