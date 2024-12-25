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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class RTDRequest {
	
private static Map map1 = Collections.synchronizedMap(new HashMap());

public static Map<String, String> fetchCustomerInfo(String EntityID,Map<String, String> map) throws IOException {
        // Database connection parameters
        String dbUrl = "fcmcore@//sfmserver.demo.sas.com:1521/SOR.DEMO.SAS.COM"; // Replace with your Oracle DB details
        String dbUser = "fcmcore"; // Replace with your database username
        String dbPassword = "Orion123"; // Replace with your database password

        Connection connection = null;
        ResultSet resultSet = null;

        try {

            // Establish connection to the database
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			Statement stmt1 = connection.createStatement();
            // SQL query to fetch customer details
            String query = "SELECT " +
               "CUSTOMERID, firstName, lastName, addressLine1, city, postalCode, country, " +
               "mobilePhone1, email, maritalStatus, customerBranch, PEP_Flag, Non_Resident_Flag, " +
               "custMonthlyIncome, nationality, gender, addrlstupdt, homephone1lstupdt, " +
               "mobilephone1lstupdt, workPhone1lstupdt, emaillstupdt, dateofbirthlstupdt, occupation " +
               "FROM FCM_CUSTOMER " +
               "WHERE CUSTOMERID = '" + entityID + "'";

// Execute the query
		resultSet = stmt1.executeQuery(query);

		// Populate the map with the result
		if (resultSet.next()) {
			map.put("/CustomerID", resultSet.getString("CUSTOMERID") != null ? resultSet.getString("customerID") : "");
			map.put("/FirstName", resultSet.getString("firstName") != null ? resultSet.getString("firstName") : "");
			//map.put("/MiddleName", resultSet.getString("MiddleName") != null ? resultSet.getString("MiddleName") : "");
			map.put("/LastName", resultSet.getString("lastName") != null ? resultSet.getString("lastName") : "");
			map.put("/AddressLine1", resultSet.getString("addressLine1") != null ? resultSet.getString("addressLine1") : "");
			map.put("/City", resultSet.getString("city") != null ? resultSet.getString("city") : "");
			map.put("/PostalCode", resultSet.getString("postalCode") != null ? resultSet.getString("postalCode") : "");
			map.put("/Country", resultSet.getString("country") != null ? resultSet.getString("country") : "");
			map.put("/MobilePhone1", resultSet.getString("mobilePhone1") != null ? resultSet.getString("mobilePhone1") : "");
			map.put("/Email", resultSet.getString("email") != null ? resultSet.getString("email") : "");
			map.put("/MaritalStatus", resultSet.getString("maritalStatus") != null ? resultSet.getString("maritalStatus") : "");
			map.put("/CustomerBranch", resultSet.getString("customerBranch") != null ? resultSet.getString("customerBranch") : "");
			map.put("/PEP_Flag", resultSet.getString("PEP_Flag") != null ? resultSet.getString("PEP_Flag") : "");
			map.put("/Non_Resident_Flag", resultSet.getString("Non_Resident_Flag") != null ? resultSet.getString("Non_Resident_Flag") : "");
			map.put("/CustMonthlyIncome", resultSet.getString("custMonthlyIncome") != null ? resultSet.getString("custMonthlyIncome") : "");
			map.put("/Nationality", resultSet.getString("nationality") != null ? resultSet.getString("nationality") : "");
			map.put("/Gender", resultSet.getString("gender") != null ? resultSet.getString("gender") : "");
			map.put("/AddrLstUpdt", resultSet.getString("addrlstupdt") != null ? resultSet.getString("addrlstupdt") : "");
			map.put("/HomePhone1LstUpdt", resultSet.getString("homephone1lstupdt") != null ? resultSet.getString("homephone1lstupdt") : "");
			map.put("/MobilePhone1LstUpdt", resultSet.getString("mobilephone1lstupdt") != null ? resultSet.getString("mobilephone1lstupdt") : "");
			map.put("/WorkPhone1LstUpdt", resultSet.getString("workPhone1lstupdt") != null ? resultSet.getString("workPhone1lstupdt") : "");
			map.put("/EmailLstUpdt", resultSet.getString("emaillstupdt") != null ? resultSet.getString("emaillstupdt") : "");
			map.put("/DateOfBirthLstUpdt", resultSet.getString("dateofbirthlstupdt") != null ? resultSet.getString("dateofbirthlstupdt") : "");
			map.put("/Occupation", resultSet.getString("occupation") != null ? resultSet.getString("occupation") : "");
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
	
		String dbUrl = "fcmcore@//sfmserver.demo.sas.com:1521/SOR.DEMO.SAS.COM"; // Replace with your Oracle DB details
        String dbUser = "fcmcore"; // Replace with your database username
        String dbPassword = "Orion123"; // Replace with your database password

        Connection connection = null;
        ResultSet resultSet = null;

        try {

            // Establish connection to the database
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			Statement stmt1 = connection.createStatement();

			// SQL query to fetch account details
			String query = "SELECT " +
               "CUSTOMER_ID, ACCOUNT_TYPE, ACCOUNT_IBAN_NUM, ACCOUNT_STATUS, ACCOUNT_CURRENCY, MATURITY_DATE, " +
               "ACCOUNT_MANAGER, ACCOUNT_AVAILABLE_BALANCE, ACCOUNT_CURRENT_BALANCE, LAST_DEPOSIT_DATE, " +
               "ACCOUNT_LAST_DEPOSIT, LAST_WITHDRAWL_DATE, ACCOUNT_LAST_WITHDRAWL, LINKED_DEBIT_CARD_1 " +
               "FROM FCM_ACCOUNT " +
               "WHERE ACCOUNT_IBAN_NUM = '" + entityID + "'";

// Execute the query
		resultSet = stmt1.executeQuery(query);

		// Populate the map with the result
		if (resultSet.next()) {
			map.put("/CUSTOMER_ID", resultSet.getString("CUSTOMER_ID") != null ? resultSet.getString("CUSTOMER_ID") : "");
			map.put("/ACCOUNT_TYPE", resultSet.getString("ACCOUNT_TYPE") != null ? resultSet.getString("ACCOUNT_TYPE") : "");
			map.put("/ACCOUNT_IBAN_NUM", resultSet.getString("ACCOUNT_IBAN_NUM") != null ? resultSet.getString("ACCOUNT_IBAN_NUM") : "");
			map.put("/ACCOUNT_STATUS", resultSet.getString("ACCOUNT_STATUS") != null ? resultSet.getString("ACCOUNT_STATUS") : "");
			map.put("/ACCOUNT_CURRENCY", resultSet.getString("ACCOUNT_CURRENCY") != null ? resultSet.getString("ACCOUNT_CURRENCY") : "");
			map.put("/MATURITY_DATE", resultSet.getString("MATURITY_DATE") != null ? resultSet.getString("MATURITY_DATE") : "");
			map.put("/ACCOUNT_MANAGER", resultSet.getString("ACCOUNT_MANAGER") != null ? resultSet.getString("ACCOUNT_MANAGER") : "");
			map.put("/ACCOUNT_AVAILABLE_BALANCE", resultSet.getString("ACCOUNT_AVAILABLE_BALANCE") != null ? resultSet.getString("ACCOUNT_AVAILABLE_BALANCE") : "");
			map.put("/ACCOUNT_CURRENT_BALANCE", resultSet.getString("ACCOUNT_CURRENT_BALANCE") != null ? resultSet.getString("ACCOUNT_CURRENT_BALANCE") : "");
			map.put("/LAST_DEPOSIT_DATE", resultSet.getString("LAST_DEPOSIT_DATE") != null ? resultSet.getString("LAST_DEPOSIT_DATE") : "");
			map.put("/ACCOUNT_LAST_DEPOSIT", resultSet.getString("ACCOUNT_LAST_DEPOSIT") != null ? resultSet.getString("ACCOUNT_LAST_DEPOSIT") : "");
			map.put("/LAST_WITHDRAWL_DATE", resultSet.getString("LAST_WITHDRAWL_DATE") != null ? resultSet.getString("LAST_WITHDRAWL_DATE") : "");
			map.put("/ACCOUNT_LAST_WITHDRAWL", resultSet.getString("ACCOUNT_LAST_WITHDRAWL") != null ? resultSet.getString("ACCOUNT_LAST_WITHDRAWL") : "");
			map.put("/LINKED_DEBIT_CARD_1", resultSet.getString("LINKED_DEBIT_CARD_1") != null ? resultSet.getString("LINKED_DEBIT_CARD_1") : "");
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
	    String dbUrl = "fcmcore@//sfmserver.demo.sas.com:1521/SOR.DEMO.SAS.COM"; // Replace with your Oracle DB details
        String dbUser = "fcmcore"; // Replace with your database username
        String dbPassword = "Orion123"; // Replace with your database password

        Connection connection = null;
        ResultSet resultSet = null;

        try {

            // Establish connection to the database
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			Statement stmt1 = connection.createStatement();

            // SQL query to fetch merchant information
            String query = "SELECT " +
               "MERCHANT_NUMBER, COMMERCIAL_NAME, TYPE_OF_BUSINESS, NATURE_OF_BUSINESS, HIGH_RISK_FLAG, " +
               "KEY_ACCOUNT_FLAG_VIP, CONTACT_PERSON, TELEPHONE_NUMBER, CITY_NAME, ACCOUNT_NUMBER, " +
               "ACCOUNT_NAME, MERCHANT_ACCOUNT_CURRENCY, BANK_SWIFT_CODE, MERCHANT_ACCOUNT_PROFILE, BANK_TYPE " +
               "FROM merchantinfo " +
               "WHERE MERCHANT_NUMBER = '" + entityID + "'";

// Execute the query
		resultSet = stmt1.executeQuery(query);

		// Populate the map with the result
		if (resultSet.next()) {
			map.put("/MERCHANT_NUMBER", resultSet.getString("MERCHANT_NUMBER") != null ? resultSet.getString("MERCHANT_NUMBER") : "");
			map.put("/COMMERCIAL_NAME", resultSet.getString("COMMERCIAL_NAME") != null ? resultSet.getString("COMMERCIAL_NAME") : "");
			map.put("/TYPE_OF_BUSINESS", resultSet.getString("TYPE_OF_BUSINESS") != null ? resultSet.getString("TYPE_OF_BUSINESS") : "");
			map.put("/NATURE_OF_BUSINESS", resultSet.getString("NATURE_OF_BUSINESS") != null ? resultSet.getString("NATURE_OF_BUSINESS") : "");
			map.put("/HIGH_RISK_FLAG", resultSet.getString("HIGH_RISK_FLAG") != null ? resultSet.getString("HIGH_RISK_FLAG") : "");
			map.put("/KEY_ACCOUNT_FLAG_VIP", resultSet.getString("KEY_ACCOUNT_FLAG_VIP") != null ? resultSet.getString("KEY_ACCOUNT_FLAG_VIP") : "");
			map.put("/CONTACT_PERSON", resultSet.getString("CONTACT_PERSON") != null ? resultSet.getString("CONTACT_PERSON") : "");
			map.put("/TELEPHONE_NUMBER", resultSet.getString("TELEPHONE_NUMBER") != null ? resultSet.getString("TELEPHONE_NUMBER") : "");
			map.put("/CITY_NAME", resultSet.getString("CITY_NAME") != null ? resultSet.getString("CITY_NAME") : "");
			map.put("/ACCOUNT_NUMBER", resultSet.getString("ACCOUNT_NUMBER") != null ? resultSet.getString("ACCOUNT_NUMBER") : "");
			map.put("/ACCOUNT_NAME", resultSet.getString("ACCOUNT_NAME") != null ? resultSet.getString("ACCOUNT_NAME") : "");
			map.put("/MERCHANT_ACCOUNT_CURRENCY", resultSet.getString("MERCHANT_ACCOUNT_CURRENCY") != null ? resultSet.getString("MERCHANT_ACCOUNT_CURRENCY") : "");
			map.put("/BANK_SWIFT_CODE", resultSet.getString("BANK_SWIFT_CODE") != null ? resultSet.getString("BANK_SWIFT_CODE") : "");
			map.put("/MERCHANT_ACCOUNT_PROFILE", resultSet.getString("MERCHANT_ACCOUNT_PROFILE") != null ? resultSet.getString("MERCHANT_ACCOUNT_PROFILE") : "");
			map.put("/BANK_TYPE", resultSet.getString("BANK_TYPE") != null ? resultSet.getString("BANK_TYPE") : "");
		}
			else {
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
    String dbUrl = "fcmcore@//sfmserver.demo.sas.com:1521/SOR.DEMO.SAS.COM"; // Replace with your Oracle DB details
    String dbUser = "fcmcore"; // Replace with your database username
    String dbPassword = "Orion123"; // Replace with your database password

    Connection connection = null;
    ResultSet resultSet = null;

    try {
        // Establish connection to the database
        connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        Statement stmt2 = connection.createStatement();
        // SQL query to fetch debit card details
        String query = "SELECT " +
               "CUSTOMER_ID, CARD_NUM, CARD_CREDIT_LIMIT, TOTAL_AUTHORIZATION_LIMIT, CASH_AUTHORIZATION_LIMIT, " +
               "CARD_BONUS_TOTAL, BONUS_START_DATE, BONUS_END_DATE, CARD_OPENING_DATE, ONLINE_AUTHORIZATION_CARD_STATUS, " +
               "CUSTOMER_INCOME, TYPE_OF_BUSINESS_ENTITY, CUSTOMER_TYPE, NATIONALITY, CUSTOMER_MOBILE_NUM_DEFAULT_ADDRESS, " +
               "EMAIL_ADDRESS_DEFAULT, DATE_OF_BIRTH, GENDER " +
               "FROM cardinfo " +
               "WHERE CARD_NUM = '" + entityID + "'";

// Execute the query
		resultSet = stmt1.executeQuery(query);

		// Populate the map with the result
		if (resultSet.next()) {
			map.put("/CUSTOMER_ID", resultSet.getString("CUSTOMER_ID") != null ? resultSet.getString("CUSTOMER_ID") : "");
			map.put("/CARD_NUM", resultSet.getString("CARD_NUM") != null ? resultSet.getString("CARD_NUM") : "");
			map.put("/CARD_CREDIT_LIMIT", resultSet.getString("CARD_CREDIT_LIMIT") != null ? resultSet.getString("CARD_CREDIT_LIMIT") : "");
			map.put("/TOTAL_AUTHORIZATION_LIMIT", resultSet.getString("TOTAL_AUTHORIZATION_LIMIT") != null ? resultSet.getString("TOTAL_AUTHORIZATION_LIMIT") : "");
			map.put("/CASH_AUTHORIZATION_LIMIT", resultSet.getString("CASH_AUTHORIZATION_LIMIT") != null ? resultSet.getString("CASH_AUTHORIZATION_LIMIT") : "");
			map.put("/CARD_BONUS_TOTAL", resultSet.getString("CARD_BONUS_TOTAL") != null ? resultSet.getString("CARD_BONUS_TOTAL") : "");
			map.put("/BONUS_START_DATE", resultSet.getString("BONUS_START_DATE") != null ? resultSet.getString("BONUS_START_DATE") : "");
			map.put("/BONUS_END_DATE", resultSet.getString("BONUS_END_DATE") != null ? resultSet.getString("BONUS_END_DATE") : "");
			map.put("/CARD_OPENING_DATE", resultSet.getString("CARD_OPENING_DATE") != null ? resultSet.getString("CARD_OPENING_DATE") : "");
			map.put("/ONLINE_AUTHORIZATION_CARD_STATUS", resultSet.getString("ONLINE_AUTHORIZATION_CARD_STATUS") != null ? resultSet.getString("ONLINE_AUTHORIZATION_CARD_STATUS") : "");
			map.put("/CUSTOMER_INCOME", resultSet.getString("CUSTOMER_INCOME") != null ? resultSet.getString("CUSTOMER_INCOME") : "");
			map.put("/TYPE_OF_BUSINESS_ENTITY", resultSet.getString("TYPE_OF_BUSINESS_ENTITY") != null ? resultSet.getString("TYPE_OF_BUSINESS_ENTITY") : "");
			map.put("/CUSTOMER_TYPE", resultSet.getString("CUSTOMER_TYPE") != null ? resultSet.getString("CUSTOMER_TYPE") : "");
			map.put("/NATIONALITY", resultSet.getString("NATIONALITY") != null ? resultSet.getString("NATIONALITY") : "");
			map.put("/CUSTOMER_MOBILE_NUM_DEFAULT_ADDRESS", resultSet.getString("CUSTOMER_MOBILE_NUM_DEFAULT_ADDRESS") != null ? resultSet.getString("CUSTOMER_MOBILE_NUM_DEFAULT_ADDRESS") : "");
			map.put("/EMAIL_ADDRESS_DEFAULT", resultSet.getString("EMAIL_ADDRESS_DEFAULT") != null ? resultSet.getString("EMAIL_ADDRESS_DEFAULT") : "");
			map.put("/DATE_OF_BIRTH", resultSet.getString("DATE_OF_BIRTH") != null ? resultSet.getString("DATE_OF_BIRTH") : "");
			map.put("/GENDER", resultSet.getString("GENDER") != null ? resultSet.getString("GENDER") : "");
		}
		 else {
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