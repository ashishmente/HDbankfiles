import org.apache.camel.Exchange;
import org.json.JSONObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.*;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;


import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

public class sample_json {
    public String jsonMapping (Exchange exchange) {
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").format(new java.util.Date());
        System.out.println("Message received from ATB Acquiring : " + timestamp);

        
        String EFMSChannelReferenceNumber = "", 
               ChannelCode = "", 
               TransferType = "", 
               ActionDateTime = "", 
               CustomerId = "", 
               AccountId = "", 
               TransactionAmountInTransactionCurrencyAmount = "", 
               TransactionAmountInTransactionCurrencyCurrency = "", 
               BeneficiaryAccountId = "", 
               BeneficiaryName = "", 
               POSMode = "", 
               MaskedCardNumber = "", 
               CardPresent = "", 
               MerchantId = "", 
               PaymentPurpose = "", 
               MerchantCategoryCode = "", 
               OnlineBankingUserID = "", tranDate = "", tranTime = "";

       
        
String textJson = exchange.getIn().getBody(String.class);
// Only proceed if the incoming JSON is not empty
		if (textJson != null && !textJson.isEmpty()) {
			// Parse the JSON string
			JSONObject jsonObject1 = new JSONObject(textJson);
			JSONObject jsonObject = jsonObject1.getJSONObject("Data");

			// Map the fields from the JSON to the class fields
			EFMSChannelReferenceNumber = jsonObject.optString("EFMSChannelReferenceNumber", "");
			ChannelCode = jsonObject.optString("ChannelCode", "");
			TransferType = jsonObject.optString("TransferType", "");
			ActionDateTime = jsonObject.optString("ActionDateTime", "");
			CustomerId = jsonObject.optString("CustomerId", "");
			AccountId = jsonObject.optString("AccountId", "");
			BeneficiaryAccountId = jsonObject.optString("BeneficiaryAccountId", "");
			BeneficiaryName = jsonObject.optString("BeneficiaryName", "");
			POSMode = jsonObject.optString("POSMode", "");
			MaskedCardNumber = jsonObject.optString("MaskedCardNumber", "");
			CardPresent = jsonObject.optString("CardPresent", "");
			MerchantId = jsonObject.optString("MerchantId", "");
			PaymentPurpose = jsonObject.optString("PaymentPurpose", "");
			//MerchantCategoryCode = jsonObject.optString("MerchantCategoryCode", "");
			OnlineBankingUserID = jsonObject.optString("OnlineBankingUserID", "");
			
			// fetch merchantCategorycode
			if ((MerchantId.trim()) != null && !(MerchantId.trim()).isEmpty()) {
                // Database connection parameters
                String dbUrl = "jdbc:oracle:thin:@//sfmserver.demo.sas.com:1521/SOR.DEMO.SAS.COM"; // Replace with your Oracle DB details
                String dbUser = "fcmcore"; // Replace with your database username
                String dbPassword = "Orion123"; // Replace with your database password

                // SQL query to fetch MerchantCategoryCode based on MerchantId
                String query = "SELECT MerchantCategoryCode FROM FCM_MERCHANT WHERE MERCHANT_NUMBER = '" + MerchantId + "'";

                try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
                     Statement statement = connection.createStatement()) {
                    ResultSet resultSet = statement.executeQuery(query);

                    if (resultSet.next()) {
                        MerchantCategoryCode = resultSet.getString("MerchantCategoryCode");  // Return the MerchantCategoryCode
                    }
                    resultSet.close();
                } catch (SQLException e) {
                    System.err.println("Error fetching MerchantCategoryCode from DB: " + e.getMessage());
                    throw e; // Rethrow exception to handle it in the calling method
                }
            }
			
			
			
			// Handle the nested "TransactionAmountInTransactionCurrency" object
			if (jsonObject.optJSONObject("TransactionAmountInTransactionCurrency") != null) {
				TransactionAmountInTransactionCurrencyAmount = jsonObject.optJSONObject("TransactionAmountInTransactionCurrency").optString("Amount", "");
				TransactionAmountInTransactionCurrencyCurrency = jsonObject.optJSONObject("TransactionAmountInTransactionCurrency").optString("Currency", "");
			}
		
				
			if (ActionDateTime != null && !ActionDateTime.isEmpty()) {
						// Parse the ActionDateTime field to extract date and time
					   SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
					Date date = inputDateFormat.parse(ActionDateTime);

					// Format the date part (YYMMDD format)
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
					tranDate = dateFormat.format(date); // Result: "241016" (for 2024-10-16)

					// Format the time part (HH:mm:ss.SS format)
					SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss.SSS");
					tranTime = timeFormat.format(date); // Result: "15:57:59.246"

					// You can format the time part further to get two digits for milliseconds
					tranTime = tranTime.substring(0, 8) + "." + tranTime.substring(9, 11); // Result: "15:57:59.24"
            }

                // Constructing the final result as a CSV-style string
                String JSONString1 = EFMSChannelReferenceNumber + "," + 
                                     ChannelCode + "," + 
                                     TransferType + "," + 
                                     ActionDateTime + "," + 
                                     CustomerId + "," + 
                                     AccountId + "," + 
                                     TransactionAmountInTransactionCurrencyAmount + "," + 
                                     TransactionAmountInTransactionCurrencyCurrency + "," + 
                                     BeneficiaryAccountId + "," + 
                                     BeneficiaryName + "," + 
                                     POSMode + "," + 
                                     MaskedCardNumber + "," + 
                                     CardPresent + "," + 
                                     MerchantId + "," + 
                                     PaymentPurpose + "," + 
                                     MerchantCategoryCode + "," + 
                                     OnlineBankingUserID + "," + tranDate + "," +tranTime;
                
                return JSONString1;

            } else {
                System.out.println("JSON data is empty or null.--------------------");
                return null;
            }

    }
}
