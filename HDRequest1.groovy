import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.concurrent.*;


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
import java.sql.*;

import org.xml.sax.InputSource;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.text.*;
import org.apache.commons.lang3.StringUtils;
import oracle.jdbc.driver.OracleDriver;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class RTDRequest {
	
private static Map map1 = Collections.synchronizedMap(new HashMap());

public static Map<String, String> fetchCustomerInfo(String EntityID,Map<String, String> map) throws IOException {
        // Database connection parameters
        String dbUrl = "jdbc:oracle:thin:@//<host>:<port>/<service_name>"; // Replace with your Oracle DB details
        String dbUser = "your_db_username"; // Replace with your database username
        String dbPassword = "your_db_password"; // Replace with your database password

        Connection connection = null;
        ResultSet resultSet = null;

        try {

            // Establish connection to the database
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			Statement stmt1 = connection.createStatement();
            // SQL query to fetch customer details
             String query = "SELECT " +
                "Gender, ResidencyStatus, RegistrationDate, MotherMaidenName, " +
                "FullName, RMName, NationalityDesc, MaritalStatus, " +
                "Category " +
                "FROM customerinfo " +
                "WHERE EntityID = '" + entityID + "'";


            // Prepare the statement

            // Execute the query
            resultSet = stmt1.executeQuery(query);

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
				if (stmt1 != null) stmt1.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                throw new IOException("Error closing database resources", e);
            }
        }

        return map;
    }

public static Map<String, String> fetchAccountInfo(String EntityID,Map<String, String> map) throws IOException {
	
		String dbUrl = "jdbc:oracle:thin:@//<host>:<port>/<service_name>"; // Replace with your Oracle DB details
        String dbUser = "your_db_username"; // Replace with your database username
        String dbPassword = "your_db_password"; // Replace with your database password

        Connection connection = null;
        ResultSet resultSet = null;

        try {

            // Establish connection to the database
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			Statement stmt1 = connection.createStatement();

			// SQL query to fetch account details
			String query = "SELECT " +
        "AccountId, Status, Currency, AccountType, AccountSubType, " +
        "ProductCode, Description, OpeningDate, MaturityDate, LoanAmount, " +
        "Tenor, InterestRate, EMIAmount, NextEMIDate, NumberOfEMIPaid, " +
        "OutstandingBalance, RepaymentFrequency " +
        "FROM accountinfo " +
        "WHERE EntityID = '" + entityID + "'";


			// Prepare the statement
			
			// Execute the query
			resultSet = stmt1.executeQuery(query);

			int i = 1; // Counter for unique map keys
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
			if (i == 1) {
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
				if (stmt1 != null) stmt1.close();
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
        ResultSet resultSet = null;

        try {

            // Establish connection to the database
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			Statement stmt1 = connection.createStatement();

            // SQL query to fetch merchant information
            String query = "SELECT " +
        "ReferenceNum, ReferenceNumConsumer, MerchantNumber, LegalIdentificationType, LegalIdentification, " +
        "CommercialName, Abbreviation, OwnerName, OwnershipStructure, TypeOfBusiness, NatureOfBusiness, " +
        "NumberOfStaff, NumberOfBranches, TradeLicenseNumber, TradeLicenseExpiration, HighRiskFlag, " +
        "KeyAccountFlagVIP, AnnualTurnoverInAED, ContactPerson, TelephoneNumber, Address1, Address2, " +
        "CityName, POBoxNumber, AddressEmail, MobileNumber, WebAddress, AccountNumber, AccountName, " +
        "AccountBank, AccountBranch, MerchantAccountCurrency, BankSwiftCode, MerchantAccountProfile, " +
        "BankType, ReturnCode, ReturnCodeDesc, ReturnCodeProvider, ReturnCodeDescProvider, RequestTime " +
        "FROM merchant_info " +
        "WHERE MerchantID = '" + EntityID + "'";


            // Prepare the statement
           

            // Execute the query
            resultSet = stmt1.executeQuery(query);

            // Populate map with database values
            if (resultSet.next()) {
                map.put("REFERENCE_NUM_M", resultSet.getString("ReferenceNum") == null ? "" : resultSet.getString("ReferenceNum"));
                map.put("REFERENCE_NUM_CONSUMER_M", resultSet.getString("ReferenceNumConsumer") == null ? "" : resultSet.getString("ReferenceNumConsumer"));
                map.put("custType", "B"); // Static value
                map.put("MERCHANT_NUMBER", resultSet.getString("MerchantNumber") == null ? "" : resultSet.getString("MerchantNumber"));
                map.put("LEGAL_IDENTIFICATION_TYPE", resultSet.getString("LegalIdentificationType") == null ? "" : resultSet.getString("LegalIdentificationType"));
                map.put("LEGAL_IDENTIFICATION", resultSet.getString("LegalIdentification") == null ? "" : resultSet.getString("LegalIdentification"));
                map.put("COMMERCIAL_NAME", resultSet.getString("CommercialName") == null ? "" : resultSet.getString("CommercialName"));
                map.put("ABBREVIATION", resultSet.getString("Abbreviation") == null ? "" : resultSet.getString("Abbreviation"));
                map.put("OWNER_NAME", resultSet.getString("OwnerName") == null ? "" : resultSet.getString("OwnerName"));
                map.put("OWNERSHIP_STRUCTURE", resultSet.getString("OwnershipStructure") == null ? "" : resultSet.getString("OwnershipStructure"));
                map.put("TYPE_OF_BUSINESS", resultSet.getString("TypeOfBusiness") == null ? "" : resultSet.getString("TypeOfBusiness"));
                map.put("NATURE_OF_BUSINESS", resultSet.getString("NatureOfBusiness") == null ? "" : resultSet.getString("NatureOfBusiness"));
                map.put("NUMBER_OF_STAFF", resultSet.getString("NumberOfStaff") == null ? "" : resultSet.getString("NumberOfStaff"));
                map.put("NUMBER_OF_BRANCHES", resultSet.getString("NumberOfBranches") == null ? "" : resultSet.getString("NumberOfBranches"));
                map.put("TRADE_LICENSE_NUMBER", resultSet.getString("TradeLicenseNumber") == null ? "" : resultSet.getString("TradeLicenseNumber"));
                map.put("TRADE_LICENSE_EXPIRATION", resultSet.getString("TradeLicenseExpiration") == null ? "" : resultSet.getString("TradeLicenseExpiration"));
                map.put("HIGH_RISK_FLAG", resultSet.getString("HighRiskFlag") == null ? "" : resultSet.getString("HighRiskFlag"));
                map.put("KEY_ACCOUNT_FLAG_VIP", resultSet.getString("KeyAccountFlagVIP") == null ? "" : resultSet.getString("KeyAccountFlagVIP"));
                map.put("ANNUAL_TURNOVER_IN_AED", resultSet.getString("AnnualTurnoverInAED") == null ? "" : resultSet.getString("AnnualTurnoverInAED"));
                map.put("CONTACT_PERSON", resultSet.getString("ContactPerson") == null ? "" : resultSet.getString("ContactPerson"));
                map.put("TELEPHONE_NUMBER", resultSet.getString("TelephoneNumber") == null ? "" : resultSet.getString("TelephoneNumber"));
                map.put("ADDRESS_1", resultSet.getString("Address1") == null ? "" : resultSet.getString("Address1"));
                map.put("ADDRESS_2", resultSet.getString("Address2") == null ? "" : resultSet.getString("Address2"));
                map.put("CITY_NAME", resultSet.getString("CityName") == null ? "" : resultSet.getString("CityName"));
                map.put("PO_BOX_NUMBER", resultSet.getString("POBoxNumber") == null ? "" : resultSet.getString("POBoxNumber"));
                map.put("ADDRESS_EMAIL", resultSet.getString("AddressEmail") == null ? "" : resultSet.getString("AddressEmail"));
                map.put("MOBILE_NUMBER", resultSet.getString("MobileNumber") == null ? "" : resultSet.getString("MobileNumber"));
                map.put("WEB_ADDRESS", resultSet.getString("WebAddress") == null ? "" : resultSet.getString("WebAddress"));
                map.put("ACCOUNT_NUMBER", resultSet.getString("AccountNumber") == null ? "" : resultSet.getString("AccountNumber"));
                map.put("ACCOUNT_NAME", resultSet.getString("AccountName") == null ? "" : resultSet.getString("AccountName"));
                map.put("ACCOUNT_BANK", resultSet.getString("AccountBank") == null ? "" : resultSet.getString("AccountBank"));
                map.put("ACCOUNT_BRANCH", resultSet.getString("AccountBranch") == null ? "" : resultSet.getString("AccountBranch"));
                map.put("MERCHANT_ACCOUNT_CURRENCY", resultSet.getString("MerchantAccountCurrency") == null ? "" : resultSet.getString("MerchantAccountCurrency"));
                map.put("BANK_SWIFT_CODE", resultSet.getString("BankSwiftCode") == null ? "" : resultSet.getString("BankSwiftCode"));
                map.put("MERCHANT_ACCOUNT_PROFILE", resultSet.getString("MerchantAccountProfile") == null ? "" : resultSet.getString("MerchantAccountProfile"));
                map.put("BANK_TYPE", resultSet.getString("BankType") == null ? "" : resultSet.getString("BankType"));
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
                if (stmt1 != null) stmt1.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                throw new IOException("Error closing database resources", e);
            }
        }

        System.out.println("----Merchant - Map------" + map);
        return map;
	
}


public static Map<String, String> fetchCardFields(String EntityID,Map<String, String> map) throws IOException {
	
	 // Database connection parameters
    String dbUrl = "jdbc:oracle:thin:@//<host>:<port>/<service_name>"; // Replace with your Oracle DB details
    String dbUser = "your_db_username"; // Replace with your database username
    String dbPassword = "your_db_password"; // Replace with your database password

    Connection connection = null;
    ResultSet resultSet = null;

    try {
        // Establish connection to the database
        connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        Statement stmt2 = connection.createStatement();
        // SQL query to fetch debit card details
        String query = "SELECT " +
        "CardNumber, PrimaryAccountNumber, CardStatus, " +
        "Description, CardExpiryDate, CardHolderName " +
        "FROM debitcardinfo " +
        "WHERE CardNumber = '" + EntityID + "'";


        // Execute the query
        resultSet = stmt2.executeQuery(query);

        // Populate the map with the result
        if (resultSet.next()) {
            map.put("CARD_NUMBER", resultSet.getString("CardNumber") != null ? resultSet.getString("CardNumber") : "");
            map.put("PRIMARY_ACCOUNT_NUMBER", resultSet.getString("PrimaryAccountNumber") != null ? resultSet.getString("PrimaryAccountNumber") : "");
            map.put("CARD_STATUS", resultSet.getString("CardStatus") != null ? resultSet.getString("CardStatus") : "");
            map.put("DESCRIPTION", resultSet.getString("Description") != null ? resultSet.getString("Description") : "");
            map.put("CARD_EXPIRY_DATE", resultSet.getString("CardExpiryDate") != null ? resultSet.getString("CardExpiryDate") : "");
            map.put("CARD_HOLDER_NAME", resultSet.getString("CardHolderName") != null ? resultSet.getString("CardHolderName") : "");
        } else {
            throw new IOException("No debit card information found for CardNumber: " + cardNumber);
        }
    } catch (ClassNotFoundException e) {
        throw new IOException("Oracle JDBC Driver not found", e);
    } catch (SQLException e) {
        throw new IOException("Database error occurred", e);
    } finally {
        // Close resources
        try {
            if (resultSet != null) resultSet.close();
            if (stmt2 != null) stmt2.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            throw new IOException("Error closing database resources", e);
        }
    }

    return map;
}
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
				
			case "C" : 
				try {
					TimeLimitedCodeBlock.runWithTimeout(new Runnable() {
						@Override
						public void run() {
							try {
								MultiDebitCardDetails m2= new MultiDebitCardDetails(entityID);
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
		}
	
		map.putAll(map1);
		
			
		map.put("/entityRequest/entityType", entityType);
		map.put("/entityRequest/entityID", entityID);
		map.put("/entityRequest/multiOrg", multiOrg);
		map.put("/entityRequest/contactID", contactID);
		map.put("/entityRequest/contactType", contactType);

		exchange.getIn().setBody(map)
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

class MultiDebitCardDetails implements Runnable {
    
    private String entityID;
    
    // Constructor to initialize entityID
    public MultiDebitCardDetails(String entityID) {
        this.entityID = entityID;
    }
    
    @Override
    public void run() {
        
        RTDRequest.fetchCardFields(entityID, RTDRequest.map1);
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