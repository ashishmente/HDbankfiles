import java.util.List;
import java.util.ArrayList;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.*;
import java.io.IOException;
import java.util.Date;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import org.apache.camel.Exchange;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.MailException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;


public class actionSFM
{
	
	private JavaMailSender mailSender;

    // Inject JavaMailSender through setter injection
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
	
	private void sendEmail(String toEmail, String subject, String body) throws MailException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        try {
            helper.setFrom(""); // Sender email address
            helper.setTo(toEmail); // Recipient email address
            helper.setSubject(subject); // Subject of the email
            helper.setText(body); // Body of the email

            mailSender.send(message); // Send the email
            System.out.println("Email sent successfully to: " + toEmail);
        } catch (MessagingException e) {
            System.err.println("Error sending email: " + e.getMessage());
        }
    }
	
	
	public static void AccountBlock(String entityID) throws IOException {
    // Database connection parameters
    String dbUrl = "jdbc:oracle:thin:@//<host>:<port>/<service_name>"; // Replace with your Oracle DB details
    String dbUser = "your_db_username"; // Replace with your database username
    String dbPassword = "your_db_password"; // Replace with your database password

    // Validate entityID
    if (entityID == null || entityID.isEmpty()) {
        throw new IllegalArgumentException("Entity ID cannot be null or empty");
    }

    String updateQuery = "UPDATE FCM_ACCOUNT SET ACCOUNT_STATUS = 'false' WHERE ACCOUNT_IBAN_NUM = '" + entityID + "'";

    try (
        Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        Statement statement = connection.createStatement()
    ) {
        // Execute the query
        int rowsUpdated = statement.executeUpdate(updateQuery);

        // Check if any row was updated
        if (rowsUpdated > 0) {
            System.out.println("Successfully updated ACCOUNT_STATUS to false for ACCOUNT_IBAN_NUM: " + entityID);
        } else {
            throw new RuntimeException("No account found for ACCOUNT_IBAN_NUM: " + entityID);
        }
    } catch (SQLException e) {
        throw new IOException("Database error occurred", e);
    } catch (Exception e) {
        throw new IOException("An error occurred while processing the AccountBlock method", e);
    }
}

	
	public static void CardBlock(String entityID) throws IOException {
    // Database connection parameters
    String dbUrl = "jdbc:oracle:thin:@//<host>:<port>/<service_name>"; // Replace with your Oracle DB details
    String dbUser = "your_db_username"; // Replace with your database username
    String dbPassword = "your_db_password"; // Replace with your database password

    // Validate entityID
    if (entityID == null || entityID.isEmpty()) {
        throw new IllegalArgumentException("Entity ID cannot be null or empty");
    }

    String updateQuery = "UPDATE FCM_CARD SET ONLINE_AUTHORIZATION_CARD_STATUS = 'false' WHERE CARD_NUM = '" + entityID + "'";

    try (
        Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        Statement statement = connection.createStatement()
    ) {
        // Execute the query
        int rowsUpdated = statement.executeUpdate(updateQuery);

        // Check if any row was updated
        if (rowsUpdated > 0) {
            System.out.println("Successfully updated ONLINE_AUTHORIZATION_CARD_STATUS to false for CARD_NUM: " + entityID);
        } else {
            throw new RuntimeException("No card found for CARD_NUM: " + entityID);
        }
    } catch (SQLException e) {
        throw new IOException("Database error occurred", e);
    } catch (Exception e) {
        throw new IOException("An error occurred while processing the CardBlock method", e);
    }
}

	

    public void actionSend (Exchange exchange) throws IndexOutOfBoundsException
	{
	
	String actionResponse = "";
		String textXml = exchange.getIn().getBody();
		String[] splitXml = textXml.split("urn:SASFraudManagement:ECMI_1_3\\\">", 2);
		String xmlPayload = "<ecmi>";
		xmlPayload = xmlPayload+splitXml[1];


				System.out.println(xmlPayload);

		Document doc = convertStringToXMLDocument(xmlPayload);

		//Verify XML document is build correctly
		System.out.println(doc.getFirstChild().getNodeName());

		String entityID="";
		String systemBlockCode="";

		//DateFormat sas_dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//DateFormat sas_d = new SimpleDateFormat("yyyy-MM-dd");
		//DateFormat sas_t = new SimpleDateFormat("HH:mm:ss.S");
		//sas_dt.setTimeZone(TimeZone.getTimeZone("Asia/Dubai"));
		try
		{

			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("ecmi");
			Node nNode = nList.item(0);

			System.out.println(nNode);
			Element eElement = (Element) nNode;
			/*username = (eElement.getElementsByTagName("username")).item(0).getTextContent();
			event = (eElement.getElementsByTagName("event")).item(0).getTextContent();
			id = (eElement.getElementsByTagName("id")).item(0).getTextContent();
			type = (eElement.getElementsByTagName("type")).item(0).getTextContent();

			createTimestamp = (eElement.getElementsByTagName("createTimestamp")).item(0).getTextContent();
			entityID = (eElement.getElementsByTagName("entityID")).item(0).getTextContent();
			contactID = (eElement.getElementsByTagName("contactID")).item(0).getTextContent();
			contactType = (eElement.getElementsByTagName("contactType")).item(0).getTextContent();
			multiOrg = (eElement.getElementsByTagName("multiOrg")).item(0).getTextContent();
			strategyName = (eElement.getElementsByTagName("strategyName")).item(0).getTextContent();
			queueID = (eElement.getElementsByTagName("queueID")).item(0).getTextContent();
			queueName = (eElement.getElementsByTagName("queueName")).item(0).getTextContent();
			//ruleID = (eElement.getElementsByTagName("ruleID")).item(0).getTextContent();
			//ruleName = (eElement.getElementsByTagName("ruleName")).item(0).getTextContent();
			//alertReason = (eElement.getElementsByTagName("alertReason")).item(0).getTextContent();
			actionDate = (eElement.getElementsByTagName("actionDate")).item(0).getTextContent();
			actionTime = (eElement.getElementsByTagName("actionTime")).item(0).getTextContent();
			alertActionID = (eElement.getElementsByTagName("alertActionID")).item(0).getTextContent();
			callResult = (eElement.getElementsByTagName("callResult")).item(0).getTextContent();
			//fraudType = (eElement.getElementsByTagName("fraudType")).item(0).getTextContent();
			memoText = (eElement.getElementsByTagName("memoText")).item(0).getTextContent();
			//merchantCountryNames = (eElement.getElementsByTagName("merchantCountryNames")).item(0).getTextContent();
			//notes = (eElement.getElementsByTagName("notes")).item(0).getTextContent();
			*/
			entityID = (eElement.getElementsByTagName("entityID")).item(0).getTextContent();
			systemBlockCode = (eElement.getElementsByTagName("systemBlockCode")).item(0).getTextContent();
			System.out.println(systemBlockCode);
			

			//System.out.println("Inside SFM Action Integration");
			//System.out.println(event);

		} catch (Exception e) {
			e.printStackTrace();
		}

			System.out.println("Before SFM SMS Integration "+systemBlockCode);

			//System.out.println("Before SFM SMS Integration "+callResult);
		
		/* Calling sms Integration method to get more details to send SMS communication */
		//actionSFM.smsIntegration(contactID, systemBlockCode, callResult, id, alertActionID, username, createTimestamp); 
		
		// 1 case
		if(systemBlockCode.equals("BACC"))  // Block account
		{
			actionSFM.AccountBlock(entityID);
		}
		
		// 2 case
		if(systemBlockCode.equals("BACCE")) { // Block account and send email
		actionSFM.AccountBlock(entityID);
		
		// Database connection parameters
		String dbUrl = "jdbc:oracle:thin:@//<host>:<port>/<service_name>"; // Replace with your Oracle DB details
		String dbUser = "your_db_username"; // Replace with your database username
		String dbPassword = "your_db_password"; // Replace with your database password

		// Initialize variables
		String email = null;
		String subject = null;
		String message = null;

		// Establish connection and fetch email in try-with-resources block
		try (
			Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT PRIMARY_EMAIL FROM FCM_ACCOUNT WHERE ACCOUNT_IBAN_NUM = '" + entityID + "'")
		) {
			// Retrieve the customer email from the result set
			if (resultSet.next()) {
				email = resultSet.getString("PRIMARY_EMAIL");
				System.out.println("Customer Email: " + email);
			} else {
				throw new RuntimeException("No customer email found for ACCOUNT_IBAN_NUM: " + entityID);
			}

			// Compose email subject and message
			subject = "Important Notice: Your HD Bank Account Access Restricted";
			message = "Your account " + entityID + " has been temporarily blocked due to security concerns. " +
					  "To ensure the security of your account and to assist you further, we kindly request you to contact our support team at your earliest convenience.";

			// Send email
			actionSFM.SendEmail(email, subject, message);

		} catch (SQLException e) {
			throw new IOException("Database error occurred", e);
		} catch (Exception e) {
			throw new IOException("An error occurred while processing the account block and email send operation", e);
		}
	}

			
			
			
		// 3 case	
		if(systemBlockCode.equals("BCA"))   // Block card
		{
			actionSFM.CardBlock(entityID);
		}
			
			
		// 4 case
		if(systemBlockCode.equals("BCAE")) {  // Block card and send email
		actionSFM.CardBlock(entityID);
		
		// Database connection parameters
		String dbUrl = "jdbc:oracle:thin:@//<host>:<port>/<service_name>"; // Replace with your Oracle DB details
		String dbUser = "your_db_username"; // Replace with your database username
		String dbPassword = "your_db_password"; // Replace with your database password

		// Initialize variables
		String email = null;
		String subject = null;
		String message = null;

		// Establish connection and fetch email in try-with-resources block
		try (
			Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT EMAIL_ADDRESS_DEFAULT FROM card_table WHERE CARD_NUM = '" + entityID + "'")
		) {
			// Retrieve the customer email from the result set
			if (resultSet.next()) {
				email = resultSet.getString("EMAIL_ADDRESS_DEFAULT");
				System.out.println("Cardholder Email: " + email);
			} else {
				throw new RuntimeException("No email found for CARD_NUM: " + entityID);
			}

			// Compose email subject and message
			subject = "Important Notice: Your HD Bank Card Access Restricted";
			message = "Your Card " + entityID + " has been temporarily blocked due to security concerns. " +
					  "To ensure the security of your account and to assist you further, we kindly request you to contact our support team at your earliest convenience.";

			// Send email
			actionSFM.SendEmail(email, subject, message);

		} catch (SQLException e) {
			throw new IOException("Database error occurred", e);
		} catch (Exception e) {
			throw new IOException("An error occurred while processing the card block and email send operation", e);
		}
	}

	
	
	
	private static Document convertStringToXMLDocument(String xmlString)
	{
		//Parser that produces DOM object trees from XML content
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		//API to obtain DOM Document instance
		DocumentBuilder builder = null;
		try
		{
			//Create DocumentBuilder with default configuration
			builder = factory.newDocumentBuilder();

			//Parse the content to Document object
			Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
			return doc;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

}