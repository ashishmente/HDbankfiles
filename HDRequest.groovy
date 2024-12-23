import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.concurrent.*;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import org.apache.camel.Exchange;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import java.io.IOException






public class RTDRequest {
	
private static Map map1 = Collections.synchronizedMap(new HashMap());

public static Map<String, String> fetchCustomerInfo(String EntityID,Map<String, String> map) throws IOException {
        
	//	 Properties props = new Properties();
     //   props.load(new FileInputStream("")); // camel-context.properties path

        // SSL Configuration
     /*  String keyPath = ""; 
        String keyPass = "changeit";
        String keyType = "JKS";
        System.setProperty("javax.net.ssl.keyStore", keyPath);
        System.setProperty("javax.net.ssl.keyStorePassword", keyPass);
        System.setProperty("javax.net.ssl.keyStoreType", keyType);

        // OAuth 2.0 Token Retrieval
       // String clientId = props.getProperty("client_id");
      //  String clientSecret = props.getProperty("client_secret");
	    String clientId ="";
		String clientSecret = "";
        String tokenUrl = "https://devmag.adcb.com/auth/oauth/v2/token";
        
		
System.out.println("Preparing to hit the token URL: " + tokenUrl);
		
        URL tokenUrlObj = new URL(tokenUrl);
        HttpsURLConnection tokenConnection = (HttpsURLConnection) tokenUrlObj.openConnection();
        tokenConnection.setRequestMethod("POST");
        tokenConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        tokenConnection.setDoOutput(true);

        // Construct the token request body
        String requestBody = "grant_type=client_credentials&client_id=" + clientId +
                             "&client_secret=" + clientSecret +
                             "&scope=Accounts";

System.out.println("Token request body prepared: " + requestBody);


        // Send token request
        try (OutputStream os = tokenConnection.getOutputStream()) {
            System.out.println("Sending token request...");
			os.write(requestBody.getBytes("utf-8"));
        }

        // Parse the token response
		System.out.println("Waiting for token response...");
        BufferedReader tokenReader = new BufferedReader(new InputStreamReader(tokenConnection.getInputStream()));
        StringBuilder tokenContent = new StringBuilder();
        String inputLine;
        while ((inputLine = tokenReader.readLine()) != null) {
            tokenContent.append(inputLine);
        }
        tokenReader.close();

System.out.println("Token response received: " + tokenContent.toString());
        // Extract the access token from the response JSON
        String accessToken = new JSONObject(tokenContent.toString()).getString("access_token");
        tokenConnection.disconnect();

        if (accessToken == null || accessToken.isEmpty()) {
            throw new IOException("Failed to obtain access token");
        }
      
	  System.out.println("Access token obtained: " + accessToken);
	  
	  
	   // Make the API call to fetch customer_info
	   
	    String customerId = EntityID;
        String apiUrl = "https://devmag.adcb.com/v2/customer_info?CustomerId=" + customerId;
		
		System.out.println("Preparing to hit the customer info API URL: " + apiUrl);


        URL apiURL = new URL(apiUrl);
        HttpsURLConnection apiConnection = (HttpsURLConnection) apiURL.openConnection();
        apiConnection.setRequestProperty("Content-Type", "application/json");
        apiConnection.setRequestProperty("Authorization", "Bearer " + accessToken);
        apiConnection.setRequestProperty("x-fapi-interaction-id", UUID.randomUUID().toString());
        apiConnection.setRequestMethod("GET");

        System.out.println("Connection established to " + apiUrl);
		
  

        // Reading JSON response
		System.out.println("Reading response from customer info API...");

		
        InputStreamReader reader = new InputStreamReader(apiConnection.getInputStream());
        StringBuilder buf = new StringBuilder();
        char[] cbuf = new char[2048];
        int num;
        while (-1 != (num = reader.read(cbuf))) {
            buf.append(cbuf, 0, num);
        }
        String jsonResponse = buf.toString();
        System.out.println("Response received: " + jsonResponse);

        // Parse JSON response
        // Parse JSON response
JSONObject jsonObject = new JSONObject(jsonResponse);
JSONObject data = jsonObject.optJSONObject("Data").optJSONObject("Customer");



// Parse EmployerInfo
JSONObject employerInfo = data.optJSONObject("CustomerPersonalDetails").optJSONObject("EmployerInfo");
map.put("DESIGNATION", employerInfo.optString("Designation", ""));


// Parse PersonalInfo
JSONObject personalInfo = data.optJSONObject("CustomerPersonalDetails").optJSONObject("PersonalInfo");
map.put("GENDER", personalInfo.optString("Gender", ""));
map.put("NON_RESIDENT_FLAG", personalInfo.optString("ResidencyStatus", ""));
map.put("CUSTOMER_SINCE", personalInfo.optString("RegistrationDate", ""));
map.put("MOTHER_MAIDEN_NAME", personalInfo.optString("MotherMaidenName", ""));
map.put("FIRST_NAME", personalInfo.optString("FullName", ""));
map.put("MIDDLE_NAME", personalInfo.optString("FullName", ""));
map.put("LAST_NAME", personalInfo.optString("FullName", ""));
map.put("CUST_RM_NAME", personalInfo.optString("RMName", ""));
map.put("NATIONALITY", personalInfo.optString("NationalityDesc", ""));
map.put("MARITIAL_STATUS", personalInfo.optString("MaritalStatus", ""));
map.put("CUSTOMER_TYPE", personalInfo.optString("Category", ""));
map.put("ROYAL_FLAG", personalInfo.optString("Category", ""));
map.put("VIP_FLAG", personalInfo.optString("Category", ""));
map.put("PEP_FLAG", personalInfo.optString("Category", ""));
map.put("CUSTOMER_SEGMENT", personalInfo.optString("Category", ""));


// Parse KYCInfo
JSONObject kycInfo = data.optJSONObject("CustomerPersonalDetails").optJSONObject("KYCInfo");
map.put("PASSPORT_NUMBER", kycInfo.optString("PassportNumber", ""));
map.put("PASSPORT_EXPIRY_DT", kycInfo.optString("ExpiryDate", ""));
map.put("VISA_EXPIRY_DT", kycInfo.optString("VisaExpiryDate", ""));
map.put("EMIRATES_ID_EXPIRY_DT", kycInfo.optString("UAENationalIdExpiryDate", ""));
map.put("VISA_NUMBER", kycInfo.optString("VisaNumber", ""));
map.put("TRADE_LICENSE_NUM", kycInfo.optString("TradeLicenseNumber", ""));


// Close the connection and return the map
apiConnection.disconnect();
return map;
*/
        // Database connection parameters
        String dbUrl = "jdbc:oracle:thin:@//<host>:<port>/<service_name>"; // Replace with your Oracle DB details
        String dbUser = "your_db_username"; // Replace with your database username
        String dbPassword = "your_db_password"; // Replace with your database password

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Load Oracle JDBC driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Establish connection to the database
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

            // SQL query to fetch customer details
            String query = """
                SELECT 
                    Gender, ResidencyStatus, RegistrationDate, MotherMaidenName, 
                    FullName, RMName, NationalityDesc, MaritalStatus, 
                    Category 
                FROM customerinfo 
                WHERE EntityID = ?
            """;

            // Prepare the statement
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, entityID);

            // Execute the query
            resultSet = preparedStatement.executeQuery();

            // Populate the map with the result
            if (resultSet.next()) {
                map.put("GENDER", resultSet.getString("Gender") != null ? resultSet.getString("Gender") : "");
                map.put("NON_RESIDENT_FLAG", resultSet.getString("ResidencyStatus") != null ? resultSet.getString("ResidencyStatus") : "");
                map.put("CUSTOMER_SINCE", resultSet.getString("RegistrationDate") != null ? resultSet.getString("RegistrationDate") : "");
                map.put("MOTHER_MAIDEN_NAME", resultSet.getString("MotherMaidenName") != null ? resultSet.getString("MotherMaidenName") : "");
                map.put("FIRST_NAME", resultSet.getString("FullName") != null ? resultSet.getString("FullName") : "");
                map.put("MIDDLE_NAME", resultSet.getString("FullName") != null ? resultSet.getString("FullName") : "");
                map.put("LAST_NAME", resultSet.getString("FullName") != null ? resultSet.getString("FullName") : "");
                map.put("CUST_RM_NAME", resultSet.getString("RMName") != null ? resultSet.getString("RMName") : "");
                map.put("NATIONALITY", resultSet.getString("NationalityDesc") != null ? resultSet.getString("NationalityDesc") : "");
                map.put("MARITIAL_STATUS", resultSet.getString("MaritalStatus") != null ? resultSet.getString("MaritalStatus") : "");
                map.put("CUSTOMER_TYPE", resultSet.getString("Category") != null ? resultSet.getString("Category") : "");
                map.put("ROYAL_FLAG", resultSet.getString("Category") != null ? resultSet.getString("Category") : "");
                map.put("VIP_FLAG", resultSet.getString("Category") != null ? resultSet.getString("Category") : "");
                map.put("PEP_FLAG", resultSet.getString("Category") != null ? resultSet.getString("Category") : "");
                map.put("CUSTOMER_SEGMENT", resultSet.getString("Category") != null ? resultSet.getString("Category") : "");
            } else {
                throw new IOException("No customer information found for EntityID: " + entityID);
            }
        } catch (ClassNotFoundException e) {
            throw new IOException("Oracle JDBC Driver not found", e);
        } catch (SQLException e) {
            throw new IOException("Database error occurred", e);
        } finally {
            // Close resources
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                throw new IOException("Error closing database resources", e);
            }
        }

        return map;
    }

public static Map<String, String> fetchAccountInfo(String EntityID,Map<String, String> map) throws IOException {
       /* Properties props = new Properties();
        props.load(new FileInputStream(""));

        // SSL Configuration
        String keyPath = "";
        String keyPass = "changeit";
        String keyType = "JKS";
        System.setProperty("javax.net.ssl.keyStore", keyPath);
        System.setProperty("javax.net.ssl.keyStorePassword", keyPass);
        System.setProperty("javax.net.ssl.keyStoreType", keyType);

        // OAuth 2.0 Token Retrieval
       // String clientId = props.getProperty("client_id");
      //  String clientSecret = props.getProperty("client_secret");
	    String clientId ="";
		String clientSecret ="";
        String tokenUrl = "https://devmag.adcb.com/auth/oauth/v2/token";
		
		System.out.println("Preparing to hit the token URL: " + tokenUrl);


        URL tokenUrlObj = new URL(tokenUrl);
        HttpsURLConnection tokenConnection = (HttpsURLConnection) tokenUrlObj.openConnection();
        tokenConnection.setRequestMethod("POST");
        tokenConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        tokenConnection.setDoOutput(true);

        // Construct the token request body
        String requestBody = "grant_type=client_credentials&client_id=" + clientId +
                             "&client_secret=" + clientSecret+
                             "&scope=Accounts";
        
		System.out.println("Token request body prepared: " + requestBody);

		
        // Send token request
        try (OutputStream os = tokenConnection.getOutputStream()) {
            System.out.println("Sending token request...");
            os.write(requestBody.getBytes("utf-8"));
        }

        // Parse the token response
        BufferedReader tokenReader = new BufferedReader(new InputStreamReader(tokenConnection.getInputStream()));
        StringBuilder tokenContent = new StringBuilder();
        String inputLine;
        while ((inputLine = tokenReader.readLine()) != null) {
            tokenContent.append(inputLine);
        }
        tokenReader.close();
        
		System.out.println("Token response received: " + tokenContent.toString());

        // Extract the access token from the response JSON
        String accessToken = new JSONObject(tokenContent.toString()).getString("access_token");
        tokenConnection.disconnect();

        if (accessToken == null || accessToken.isEmpty()) {
            throw new IOException("Failed to obtain access token");
        }
          
		  System.out.println("Access token obtained: " + accessToken);

        // Make the API call to fetch account info
		String customerId =  EntityID ;
        String apiUrl = "https://devmag.adcb.com/v2/accounts/inquiry?CustomerId=" + customerId;
        
		System.out.println("Preparing to hit the customer info API URL: " + apiUrl);

		
        URL apiURL = new URL(apiUrl);
        HttpsURLConnection apiConnection = (HttpsURLConnection) apiURL.openConnection();
        apiConnection.setRequestProperty("Content-Type", "application/json");
        apiConnection.setRequestProperty("Authorization", "Bearer " + accessToken);
        apiConnection.setRequestProperty("x-fapi-interaction-id", UUID.randomUUID().toString());
        apiConnection.setRequestMethod("GET");

        System.out.println("Connection established to " + apiUrl);

        // Reading JSON response
		System.out.println("Reading response from customer info API...");

        InputStreamReader reader = new InputStreamReader(apiConnection.getInputStream());
        StringBuilder buf = new StringBuilder();
        char[] cbuf = new char[2048];
        int num;
        while (-1 != (num = reader.read(cbuf))) {
            buf.append(cbuf, 0, num);
        }
        String jsonResponse = buf.toString();
        System.out.println("Response received: " + jsonResponse);

         // Parse JSON response
        JSONObject jsonObject = new JSONObject(jsonResponse);
		JSONObject data = jsonObject.optJSONObject("Data");
        JSONArray accountArray = data.getJSONArray("Account");
		
		   Map<String, Number> map2 = new HashMap<>(); // this map is used to store  all the account id for a customer .
         // Loop through each account object in the array
        for (int i = 0; i < accountArray.length(); i++) {
            JSONObject account = accountArray.optJSONObject(i);
            
			
            // Create variables for each field in the account
            String AccountId = account.optString("AccountId", "");
            String Status = account.optString("Status", "");
            String Currency = account.optString("Currency", "");
            String AccountType = account.optString("AccountType", "");
            String accountSubType = account.optString("AccountSubType", "");
            String productCode = account.optString("ProductCode", "");
            String Description = account.optString("Description", "");
            String OpeningDate = account.optString("OpeningDate", "");
            String maturityDate = account.optString("MaturityDate", "");
            String loanAmount = account.optString("LoanAmount", "");
            String tenor = account.optString("Tenor", "");
            String interestRate = account.optString("InterestRate", "");
            String emiAmount = account.optString("EMIAmount", "");
            String nextEmiDate = account.optString("NextEMIDate", "");
            String numberOfEmiPaid = account.optString("NumberOfEMIPaid", "");
            String outstandingBalance = account.optString("OutstandingBalance", "");
            String repaymentFrequency = account.optString("RepaymentFrequency", "");

            // Store variables in map with unique keys for each account entry
            map.put("Account_" + i + "_AccountId", AccountId);
            map.put("Account_" + i + "_Status", Status);
            map.put("Account_" + i + "_AccountType", AccountType);
            map.put("Account_" + i + "_Description", Description);
            map.put("Account_" + i + "_OpeningDate", OpeningDate);
			map.put("Account_" + i + "_Currency", Currency);
			
			map2.put(AccountId, i);
			
        }
		
		

        apiConnection.disconnect();
        return fetchBalanceInfo(customerId,map,map2);*/
		
		
		
		String dbUrl = "jdbc:oracle:thin:@//<host>:<port>/<service_name>"; // Replace with your Oracle DB details
		String dbUser = "your_db_username"; // Replace with your database username
		String dbPassword = "your_db_password"; // Replace with your database password

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// Load Oracle JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// Establish connection to the database
			connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

			// SQL query to fetch account details
			String query = """
				SELECT 
					AccountId, Status, Currency, AccountType, AccountSubType, 
					ProductCode, Description, OpeningDate, MaturityDate, LoanAmount, 
					Tenor, InterestRate, EMIAmount, NextEMIDate, NumberOfEMIPaid, 
					OutstandingBalance, RepaymentFrequency 
				FROM accountinfo 
				WHERE EntityID = ?
			""";

			// Prepare the statement
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, entityID);

			// Execute the query
			resultSet = preparedStatement.executeQuery();

			int i = 0; // Counter for unique map keys
			while (resultSet.next()) {
				// Fetch and store account details
				String accountId = resultSet.getString("AccountId") != null ? resultSet.getString("AccountId") : "";
				String status = resultSet.getString("Status") != null ? resultSet.getString("Status") : "";
				String currency = resultSet.getString("Currency") != null ? resultSet.getString("Currency") : "";
				String accountType = resultSet.getString("AccountType") != null ? resultSet.getString("AccountType") : "";
				String accountSubType = resultSet.getString("AccountSubType") != null ? resultSet.getString("AccountSubType") : "";
				String productCode = resultSet.getString("ProductCode") != null ? resultSet.getString("ProductCode") : "";
				String description = resultSet.getString("Description") != null ? resultSet.getString("Description") : "";
				String openingDate = resultSet.getString("OpeningDate") != null ? resultSet.getString("OpeningDate") : "";
				String maturityDate = resultSet.getString("MaturityDate") != null ? resultSet.getString("MaturityDate") : "";
				String loanAmount = resultSet.getString("LoanAmount") != null ? resultSet.getString("LoanAmount") : "";
				String tenor = resultSet.getString("Tenor") != null ? resultSet.getString("Tenor") : "";
				String interestRate = resultSet.getString("InterestRate") != null ? resultSet.getString("InterestRate") : "";
				String emiAmount = resultSet.getString("EMIAmount") != null ? resultSet.getString("EMIAmount") : "";
				String nextEmiDate = resultSet.getString("NextEMIDate") != null ? resultSet.getString("NextEMIDate") : "";
				String numberOfEmiPaid = resultSet.getString("NumberOfEMIPaid") != null ? resultSet.getString("NumberOfEMIPaid") : "";
				String outstandingBalance = resultSet.getString("OutstandingBalance") != null ? resultSet.getString("OutstandingBalance") : "";
				String repaymentFrequency = resultSet.getString("RepaymentFrequency") != null ? resultSet.getString("RepaymentFrequency") : "";

				// Store account details in map with unique keys
				map.put("Account_" + i + "_AccountId", accountId);
				map.put("Account_" + i + "_Status", status);
				map.put("Account_" + i + "_Currency", currency);
				map.put("Account_" + i + "_AccountType", accountType);
				map.put("Account_" + i + "_AccountSubType", accountSubType);
				map.put("Account_" + i + "_ProductCode", productCode);
				map.put("Account_" + i + "_Description", description);
				map.put("Account_" + i + "_OpeningDate", openingDate);
				map.put("Account_" + i + "_MaturityDate", maturityDate);
				map.put("Account_" + i + "_LoanAmount", loanAmount);
				map.put("Account_" + i + "_Tenor", tenor);
				map.put("Account_" + i + "_InterestRate", interestRate);
				map.put("Account_" + i + "_EMIAmount", emiAmount);
				map.put("Account_" + i + "_NextEMIDate", nextEmiDate);
				map.put("Account_" + i + "_NumberOfEMIPaid", numberOfEmiPaid);
				map.put("Account_" + i + "_OutstandingBalance", outstandingBalance);
				map.put("Account_" + i + "_RepaymentFrequency", repaymentFrequency);

				i++; // Increment the counter
			}

			// Check if no accounts were found
			if (i == 0) {
				throw new IOException("No accounts found for EntityID: " + entityID);
			}
		} catch (ClassNotFoundException e) {
			throw new IOException("Oracle JDBC Driver not found", e);
		} catch (SQLException e) {
			throw new IOException("Database error occurred", e);
		} finally {
			// Close resources
			try {
				if (resultSet != null) resultSet.close();
				if (preparedStatement != null) preparedStatement.close();
				if (connection != null) connection.close();
			} catch (SQLException e) {
				throw new IOException("Error closing database resources", e);
			}
		}

		return map;
}



public static Map<String, String> fetchMerchantInfo(String EntityID,Map<String, String> map) throws IOException {
	 String dbUrl = "jdbc:oracle:thin:@//<host>:<port>/<service_name>"; // Replace with your Oracle DB details
        String dbUser = "your_db_username"; // Replace with your database username
        String dbPassword = "your_db_password"; // Replace with your database password

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Load Oracle JDBC driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Establish connection to the database
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

            // SQL query to fetch merchant information
            String query = """
                SELECT 
                    ReferenceNum, ReferenceNumConsumer, MerchantNumber, LegalIdentificationType, LegalIdentification, 
                    CommercialName, Abbreviation, OwnerName, OwnershipStructure, TypeOfBusiness, NatureOfBusiness, 
                    NumberOfStaff, NumberOfBranches, TradeLicenseNumber, TradeLicenseExpiration, HighRiskFlag, 
                    KeyAccountFlagVIP, AnnualTurnoverInAED, ContactPerson, TelephoneNumber, Address1, Address2, 
                    CityName, POBoxNumber, AddressEmail, MobileNumber, WebAddress, AccountNumber, AccountName, 
                    AccountBank, AccountBranch, MerchantAccountCurrency, BankSwiftCode, MerchantAccountProfile, 
                    BankType, ReturnCode, ReturnCodeDesc, ReturnCodeProvider, ReturnCodeDescProvider, RequestTime 
                FROM merchant_info 
                WHERE MerchantID = ?
            """;

            // Prepare the statement
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, merchantId);

            // Execute the query
            resultSet = preparedStatement.executeQuery();

            // Populate map with database values
            if (resultSet.next()) {
                map.put("/REFERENCE_NUM_M", resultSet.getString("ReferenceNum") == null ? "" : resultSet.getString("ReferenceNum"));
                map.put("/REFERENCE_NUM_CONSUMER_M", resultSet.getString("ReferenceNumConsumer") == null ? "" : resultSet.getString("ReferenceNumConsumer"));
                map.put("/custType", "B"); // Static value
                map.put("/MERCHANT_NUMBER", resultSet.getString("MerchantNumber") == null ? "" : resultSet.getString("MerchantNumber"));
                map.put("/LEGAL_IDENTIFICATION_TYPE", resultSet.getString("LegalIdentificationType") == null ? "" : resultSet.getString("LegalIdentificationType"));
                map.put("/LEGAL_IDENTIFICATION", resultSet.getString("LegalIdentification") == null ? "" : resultSet.getString("LegalIdentification"));
                map.put("/COMMERCIAL_NAME", resultSet.getString("CommercialName") == null ? "" : resultSet.getString("CommercialName"));
                map.put("/ABBREVIATION", resultSet.getString("Abbreviation") == null ? "" : resultSet.getString("Abbreviation"));
                map.put("/OWNER_NAME", resultSet.getString("OwnerName") == null ? "" : resultSet.getString("OwnerName"));
                map.put("/OWNERSHIP_STRUCTURE", resultSet.getString("OwnershipStructure") == null ? "" : resultSet.getString("OwnershipStructure"));
                map.put("/TYPE_OF_BUSINESS", resultSet.getString("TypeOfBusiness") == null ? "" : resultSet.getString("TypeOfBusiness"));
                map.put("/NATURE_OF_BUSINESS", resultSet.getString("NatureOfBusiness") == null ? "" : resultSet.getString("NatureOfBusiness"));
                map.put("/NUMBER_OF_STAFF", resultSet.getString("NumberOfStaff") == null ? "" : resultSet.getString("NumberOfStaff"));
                map.put("/NUMBER_OF_BRANCHES", resultSet.getString("NumberOfBranches") == null ? "" : resultSet.getString("NumberOfBranches"));
                map.put("/TRADE_LICENSE_NUMBER", resultSet.getString("TradeLicenseNumber") == null ? "" : resultSet.getString("TradeLicenseNumber"));
                map.put("/TRADE_LICENSE_EXPIRATION", resultSet.getString("TradeLicenseExpiration") == null ? "" : resultSet.getString("TradeLicenseExpiration"));
                map.put("/HIGH_RISK_FLAG", resultSet.getString("HighRiskFlag") == null ? "" : resultSet.getString("HighRiskFlag"));
                map.put("/KEY_ACCOUNT_FLAG_VIP", resultSet.getString("KeyAccountFlagVIP") == null ? "" : resultSet.getString("KeyAccountFlagVIP"));
                map.put("/ANNUAL_TURNOVER_IN_AED", resultSet.getString("AnnualTurnoverInAED") == null ? "" : resultSet.getString("AnnualTurnoverInAED"));
                map.put("/CONTACT_PERSON", resultSet.getString("ContactPerson") == null ? "" : resultSet.getString("ContactPerson"));
                map.put("/TELEPHONE_NUMBER", resultSet.getString("TelephoneNumber") == null ? "" : resultSet.getString("TelephoneNumber"));
                map.put("/ADDRESS_1", resultSet.getString("Address1") == null ? "" : resultSet.getString("Address1"));
                map.put("/ADDRESS_2", resultSet.getString("Address2") == null ? "" : resultSet.getString("Address2"));
                map.put("/CITY_NAME", resultSet.getString("CityName") == null ? "" : resultSet.getString("CityName"));
                map.put("/PO_BOX_NUMBER", resultSet.getString("POBoxNumber") == null ? "" : resultSet.getString("POBoxNumber"));
                map.put("/ADDRESS_EMAIL", resultSet.getString("AddressEmail") == null ? "" : resultSet.getString("AddressEmail"));
                map.put("/MOBILE_NUMBER", resultSet.getString("MobileNumber") == null ? "" : resultSet.getString("MobileNumber"));
                map.put("/WEB_ADDRESS", resultSet.getString("WebAddress") == null ? "" : resultSet.getString("WebAddress"));
                map.put("/ACCOUNT_NUMBER", resultSet.getString("AccountNumber") == null ? "" : resultSet.getString("AccountNumber"));
                map.put("/ACCOUNT_NAME", resultSet.getString("AccountName") == null ? "" : resultSet.getString("AccountName"));
                map.put("/ACCOUNT_BANK", resultSet.getString("AccountBank") == null ? "" : resultSet.getString("AccountBank"));
                map.put("/ACCOUNT_BRANCH", resultSet.getString("AccountBranch") == null ? "" : resultSet.getString("AccountBranch"));
                map.put("/MERCHANT_ACCOUNT_CURRENCY", resultSet.getString("MerchantAccountCurrency") == null ? "" : resultSet.getString("MerchantAccountCurrency"));
                map.put("/BANK_SWIFT_CODE", resultSet.getString("BankSwiftCode") == null ? "" : resultSet.getString("BankSwiftCode"));
                map.put("/MERCHANT_ACCOUNT_PROFILE", resultSet.getString("MerchantAccountProfile") == null ? "" : resultSet.getString("MerchantAccountProfile"));
                map.put("/BANK_TYPE", resultSet.getString("BankType") == null ? "" : resultSet.getString("BankType"));
            } else {
                throw new IOException("No merchant found with MerchantID: " + merchantId);
            }

        } catch (ClassNotFoundException e) {
            throw new IOException("Oracle JDBC Driver not found", e);
        } catch (SQLException e) {
            throw new IOException("Database error occurred", e);
        } finally {
            // Close resources
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                throw new IOException("Error closing database resources", e);
            }
        }

        System.out.println("----Merchant - Map------" + map);
        return map;
	
	
}

/*public static Map<String, String> fetchBalanceInfo(String EntityID,Map<String, String> map,Map<String, Number> map2) throws IOException {
         Properties props = new Properties();
            props.load(new FileInputStream(" "));  //

            // SSL Configuration
            String keyPath = "";
           String keyPass = "changeit";
           String keyType = "JKS";
           System.setProperty("javax.net.ssl.keyStore", keyPath);
           System.setProperty("javax.net.ssl.keyStorePassword", keyPass);
           System.setProperty("javax.net.ssl.keyStoreType", keyType);

            // OAuth 2.0 Token Retrieval
           // String clientId = props.getProperty("client_id");
          //  String clientSecret = props.getProperty("client_secret");
		  String clientId = "";
		  String clientSecret = "";
            String tokenUrl = "https://devmag.adcb.com/auth/oauth/v2/token";
            System.out.println("Preparing to hit the token URL: " + tokenUrl);
			
            URL tokenUrlObj = new URL(tokenUrl);
            HttpsURLConnection tokenConnection = (HttpsURLConnection) tokenUrlObj.openConnection();
            tokenConnection.setRequestMethod("POST");
            tokenConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            tokenConnection.setDoOutput(true);

            // Construct the token request body
            String requestBody = "grant_type=client_credentials&client_id=" + URLEncoder.encode(clientId) +
                                 "&client_secret=" + URLEncoder.encode(clientSecret) +
                                 "&scope=" + URLEncoder.encode("AccountBalancesDetails", "UTF-8");
                    System.out.println("Token request body prepared: " + requestBody);

            // Send token request
            try (OutputStream os = tokenConnection.getOutputStream()) {
                System.out.println("Sending token request...");
				os.write(requestBody.getBytes("utf-8"));
            }

            // Parse the token response
            BufferedReader tokenReader = new BufferedReader(new InputStreamReader(tokenConnection.getInputStream()));
            StringBuilder tokenContent = new StringBuilder();
            String inputLine;
            while ((inputLine = tokenReader.readLine()) != null) {
                tokenContent.append(inputLine);
            }
            tokenReader.close();
            System.out.println("Token response received: " + tokenContent.toString());

            // Extract the access token from the response JSON
            String accessToken = new JSONObject(tokenContent.toString()).getString("access_token");
            tokenConnection.disconnect();

            if (accessToken == null || accessToken.isEmpty()) {
                throw new IOException("Failed to obtain access token");
            }
            System.out.println("Access token obtained: " + accessToken);
            // Make the API call to fetch balance info
            String customerId = EntityID ; // Example Customer ID for successful response
            String apiUrl = "https://devmag.adcb.com/v2/balances?CustomerId=" + customerId;
               System.out.println("Preparing to hit the customer info API URL: " + apiUrl);

            URL apiURL = new URL(apiUrl);
            HttpsURLConnection apiConnection = (HttpsURLConnection) apiURL.openConnection();
            apiConnection.setRequestProperty("Content-Type", "application/json");
            apiConnection.setRequestProperty("Authorization", "Bearer " + accessToken);
            apiConnection.setRequestProperty("x-fapi-interaction-id", UUID.randomUUID().toString());
            apiConnection.setRequestMethod("GET");

            System.out.println("Connection established to " + apiUrl);

       // Reading JSON response
	   System.out.println("Reading response from customer info API...");
InputStreamReader reader = new InputStreamReader(apiConnection.getInputStream());
StringBuilder buf = new StringBuilder();
char[] cbuf = new char[2048];
int num;
while (-1 != (num = reader.read(cbuf))) {
    buf.append(cbuf, 0, num);
}
String jsonResponse = buf.toString();
System.out.println("Response received: " + jsonResponse);

// Parse JSON response
JSONObject jsonObject = new JSONObject(jsonResponse);
//JSONObject example = jsonObject.optJSONObject("example");
JSONObject data = jsonObject.optJSONObject("Data");
JSONArray balanceArray = data.getJSONArray("Balance");


// Loop through each balance object in the array
for (int i = 0; i < balanceArray.length(); i++) {
    JSONObject balance = balanceArray.optJSONObject(i);

    // Extracting balance fields
    String accountId = balance.optString("AccountId", "");
    String Amount = "";
    String currency = "";

    // Extract "Amount" and its nested fields
    if (balance.has("Amount") && balance.get("Amount") instanceof JSONObject) {
        JSONObject amountObject = balance.optJSONObject("Amount");
        if (amountObject.has("Amount")) {
            Amount = amountObject.getString("Amount");
        }
        if (amountObject.has("Currency")) {
            currency = amountObject.getString("Currency");
        }
    }
	
	String creditDebitIndicator = balance.optString("CreditDebitIndicator", "");
    String type = balance.optString("Type", "");
    String dateTime = balance.optString("DateTime", "");
    String amountOnHold = balance.optString("AmountOnHold", "");
    String AmountAvailable = balance.optString("AmountAvailable", "");
     

	// Map these values with unique keys
    map.put("Acct_" + map2.get(accountId) + "_Balance_Amount", Amount);
    map.put("Acct_" + map2.get(accountId) + "_Balance_AmountAvailable", AmountAvailable);

}
        apiConnection.disconnect();
        return map;
    }
  
*/

public void prepareHDResponse(Exchange exchange) {
   String textXml = exchange.getIn().getBody();
		//System.out.println(textXml);
		String textXml1 = textXml.replace('"','/');
		String[] splitXml = textXml1.split("entityType=/", 2);
		String newXml1 = splitXml[1].replaceFirst("/>", "</entityType>");
		String textXml2 = "<entityRequest><entityType>" + newXml1;

		Document doc = convertStringToXMLDocument(textXml2);
		Map map = new HashMap();

		String entityType = null;
		String entityID = null;
		String multiOrg = null;
		String username = null;
		String contactID = null;
		String contactType = null;
		try 
			{
				doc.getDocumentElement().normalize();
					NodeList nList = doc.getElementsByTagName("entityRequest");
					Node nNode = nList.item(0);
					
					Element eElement = (Element) nNode;
					entityType = (eElement.getElementsByTagName("entityType")).item(0).getTextContent();
			        entityID = (eElement.getElementsByTagName("entityID")).item(0).getTextContent();
					multiOrg = (eElement.getElementsByTagName("multiOrg")).item(0).getTextContent();
					username = (eElement.getElementsByTagName("username")).item(0).getTextContent(); /* Release 2 Addition*/
					if((eElement.getElementsByTagName("contactID").item(0)) != null)
					{	contactID = (eElement.getElementsByTagName("contactID")).item(0).getTextContent(); }
					if((eElement.getElementsByTagName("contactType")).item(0) != null)
					{	contactType = (eElement.getElementsByTagName("contactType")).item(0).getTextContent(); }
						
			} catch (Exception e) {
				e.printStackTrace();
		}
		
		
		
		//we are fetching the customertype from the database using entityID
		String SORDBUrl= ;
		String sorDBUsername= ;
		String sorDBPassword= ;
	
		Connection conn1 = DriverManager.getConnection(SORDBUrl, sorDBUsername, sorDBPassword);
		Statement stmt1 = conn1.createStatement();
		String query = "SELECT SMH_CUST_TYPE FROM Table name where entityID ='"+entityID+"';
		ResultSet resultSet= stmt1.executeQuery(query);
		if (resultSet.next()) {
                String customer_type = resultSet.getString("SMH_CUST_TYPE");
		}
		
		
		
		
		if(customer_type=="I")
		{
			map.put("/custType", "I");
		switch (entityType)
			{
			case "A" : 
				try {
					TimeLimitedCodeBlock.runWithTimeout(new Runnable() {
						@Override
						public void run() {
							try {
								MultifetchAccountInfo m1= new MultifetchAccountInfo(entityID);
								Thread t1= new Thread(m1);
								
								t1.start(); 
								

								t1.join();
							
								//mapThread.putAll(map1);

							}
							catch (InterruptedException e) {
								System.out.println("was interuupted! 1");
							}
						}
					}, 3, TimeUnit.SECONDS);
				}
				catch (TimeoutException e) {
					System.out.println("Got timeout! 2");
				}
				break;    
			case "X" : 
				try {
					TimeLimitedCodeBlock.runWithTimeout(new Runnable() {
						@Override
						public void run() {
							try {
								MultifetchCustomerInfo m2= new MultifetchCustomerInfo(entityID);
								Thread t2= new Thread(m2);
								
								
				
								t2.start(); 
								t2.join();
								
							
							}
							catch (InterruptedException e) {
								System.out.println("was interuupted! 3");
							}
						}
					}, 3, TimeUnit.SECONDS);
				}
				catch (TimeoutException e) {
					System.out.println("Got timeout! 4");
				}
				break;
				
			/*	case "B" : 
				try {
					TimeLimitedCodeBlock.runWithTimeout(new Runnable() {
						@Override
						public void run() {
							try {
								MultifetchBalanceInfo m3= new MultifetchBalanceInfo();
								Thread t3= new Thread(m3);
								
								
				
								t3.start(); 
								t3.join();
								
							
							}
							catch (InterruptedException e) {
								System.out.println("was interuupted! 5");
							}
						}
					}, 3, TimeUnit.SECONDS);
				}
				catch (TimeoutException e) {
					System.out.println("Got timeout! 6");
				}
				break;
		                  */
}
		}
		
		
		
		
		if(customer_type=="B")
		{
			map.put("/custType", "B");
		switch (entityType)
			{
			case "A" : 
				try {
					TimeLimitedCodeBlock.runWithTimeout(new Runnable() {
						@Override
						public void run() {
							try {
								MultifetchAccountInfo m1= new MultifetchAccountInfo(entityID);
								Thread t1= new Thread(m1);
								
								t1.start(); 
								

								t1.join();
							
								//mapThread.putAll(map1);

							}
							catch (InterruptedException e) {
								System.out.println("was interuupted! 1");
							}
						}
					}, 3, TimeUnit.SECONDS);
				}
				catch (TimeoutException e) {
					System.out.println("Got timeout! 2");
				}
				break;    
			case "X" : 
				try {
					TimeLimitedCodeBlock.runWithTimeout(new Runnable() {
						@Override
						public void run() {
							try {
								MultifetchCustomerInfo m2= new MultifetchCustomerInfo(entityID);
								Thread t2= new Thread(m2);
								
								
				
								t2.start(); 
								t2.join();
								
							
							}
							catch (InterruptedException e) {
								System.out.println("was interuupted! 3");
							}
						}
					}, 3, TimeUnit.SECONDS);
				}
				catch (TimeoutException e) {
					System.out.println("Got timeout! 4");
				}
				break;
				
				case "M" : 
				try {
					TimeLimitedCodeBlock.runWithTimeout(new Runnable() {
						@Override
						public void run() {
							try {
								MultiMerchantRequest m2= new MultiMerchantRequest(entityID);
								Thread t2= new Thread(m2);
								
								
				
								t2.start(); 
								t2.join();
								
							
							}
							catch (InterruptedException e) {
								System.out.println("was interuupted! 3");
							}
						}
					}, 3, TimeUnit.SECONDS);
				}
				catch (TimeoutException e) {
					System.out.println("Got timeout! 4");
				}
				break;
				
			/*	case "B" : 
				try {
					TimeLimitedCodeBlock.runWithTimeout(new Runnable() {
						@Override
						public void run() {
							try {
								MultifetchBalanceInfo m3= new MultifetchBalanceInfo();
								Thread t3= new Thread(m3);
								
								
				
								t3.start(); 
								t3.join();
								
							
							}
							catch (InterruptedException e) {
								System.out.println("was interuupted! 5");
							}
						}
					}, 3, TimeUnit.SECONDS);
				}
				catch (TimeoutException e) {
					System.out.println("Got timeout! 6");
				}
				break;
		                  */
}
		}
		
		
		
map.putAll(map1);
		


	if (contactType == null)
			contactType = "Customer Id Missing";
		if (contactID == null)
			contactID = "Customer Id Missing";

		if (entityType == 'X')
		{
			contactType = entityType;
			contactID = entityID;	
		}
			
		map.put("/entityRequest/entityType", entityType);
		map.put("/entityRequest/entityID", entityID);
		map.put("/entityRequest/multiOrg", multiOrg);
		map.put("/entityRequest/contactID", contactID);
		map.put("/entityRequest/contactType", contactType);
		
		if(responseStatus == "SUCCESS")
			exchange.getIn().setBody(map);

    
}

}




class MultifetchCustomerInfo implements Runnable{  
    
	private String entityID;

	public MultifetchCustomerInfo(String _entityID) {
		this.entityID = _entityID;
	}
	
    @Override
	public void run(){  
		RTDRequest.fetchCustomerInfo(entityID,RTDRequest.map1); 
	}
}

class MultifetchAccountInfo implements Runnable{  
	
	private String entityID;

	public MultifetchAccountInfo(String _entityID) {
		this.entityID = _entityID;
	}
	
    @Override
	public void run(){  
		RTDRequest.fetchAccountInfo(entityID,RTDRequest.map1); 
	}
}


class MultiMerchantRequest implements Runnable{
	private String entityID;

	public MultifetchAccountInfo(String _entityID) {
		this.entityID = _entityID;
	}
	
    @Override
	public void run(){  
		RTDRequest.fetchMerchantInfo(entityID,RTDRequest.map1); 
	}
	
	
}


class TimeLimitedCodeBlock {

	public static void runWithTimeout(final Runnable runnable, long timeout, TimeUnit timeUnit) throws Exception {
		runWithTimeout(new Callable<Object>() {
			@Override
			public Object call() throws Exception {
				runnable.run();
				return null;
			}
		}, timeout, timeUnit);
	}

	public static <T> T runWithTimeout(Callable<T> callable, long timeout, TimeUnit timeUnit) throws Exception {
		final ExecutorService executor = Executors.newSingleThreadExecutor();
		final Future<T> future = executor.submit(callable);
		executor.shutdown(); // This does not cancel the already-scheduled task.
		try {
			return future.get(timeout, timeUnit);
		}
		catch (TimeoutException e) {
			future.cancel(true);
			throw e;
		}
		catch (ExecutionException e) {
			//unwrap the root cause
			Throwable t = e.getCause();
			if (t instanceof Error) {
				throw (Error) t;
			} else if (t instanceof Exception) {
				throw (Exception) t;
			} else {
				throw new IllegalStateException(t);
			}
		}
	}

}