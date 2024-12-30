import java.util.List;
import java.util.ArrayList;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.io.*;
import java.security.*;
import java.security.cert.X509Certificate;
import java.util.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.nio.charset.*;
import java.net.*;
import javax.net.ssl.*;
import java.nio.file.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.apache.camel.Exchange;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import java.util.Map;
import java.util.HashMap;
import java.io.File;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.FileReader;
import java.util.Iterator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.camel.Exchange;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.*;
import java.io.IOException;
import java.util.Date;

import java.io.FileReader;
import java.util.Iterator;
import java.text.*;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.Charset;
import java.util.Base64.*;
import java.nio.charset.StandardCharsets;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import org.apache.camel.Exchange;
import org.json.simple.parser.JSONParser;
import org.json.JSONObject;
import org.json.JSONArray;
import org.apache.log4j.Logger
 
import java.util.*;
import java.io.*;
 
public class sample_json_response {
 
	private static final Logger log = Logger.getLogger("groovy");
	
	public static void AccountBlock(String entityID, String EmailFlag) throws IOException {
		// Database connection parameters
		String dbUrl = "jdbc:oracle:thin:@//sfmserver.demo.sas.com:1521/SOR.DEMO.SAS.COM"; // Replace with your Oracle DB details
		String dbUser = "fcmcore"; // Replace with your database username
		String dbPassword = "Orion123"; // Replace with your database password

		// Validate entityID
		if (entityID == null || entityID.isEmpty()) {
			throw new IllegalArgumentException("Entity ID cannot be null or empty");
		}

		String updateQuery = "UPDATE FCM_ACCOUNT SET ACCOUNT_STATUS = 'Inactive' WHERE ACCOUNT_IBAN_NUM = '" + entityID + "'";
		String updateEmailSentQuery = "UPDATE FCM_ACCOUNT SET EMAIL_SENT = 'true' WHERE ACCOUNT_IBAN_NUM = '" + entityID + "'";
		

		Connection connection = null;
		Statement statement = null;

		try {
			// Establish database connection
			connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

			// Create statement
			statement = connection.createStatement();

			// Execute the query
			int rowsUpdated = statement.executeUpdate(updateQuery);
			if(EmailFlag.equals("1"))
			{
			statement.executeUpdate(updateEmailSentQuery);
			}

			// Check if any row was updated
			if (rowsUpdated > 0) {
				System.out.println("Successfully updated ACCOUNT_STATUS to Inactive for ACCOUNT_IBAN_NUM: " + entityID);
			} else {
				throw new RuntimeException("No account found for ACCOUNT_IBAN_NUM: " + entityID);
			}
		} catch (SQLException e) {
			throw new IOException("Database error occurred", e);
		} catch (Exception e) {
			throw new IOException("An error occurred while processing the AccountBlock method", e);
		} finally {
			// Close resources in the finally block
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					System.err.println("Failed to close statement: " + e.getMessage());
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					System.err.println("Failed to close connection: " + e.getMessage());
				}
			}
		}
	}


	
		public static void CardBlock(String entityID,String EmailFlag) throws IOException {
		// Database connection parameters
		String dbUrl = "jdbc:oracle:thin:@//sfmserver.demo.sas.com:1521/SOR.DEMO.SAS.COM"; // Replace with your Oracle DB details
		String dbUser = "fcmcore"; // Replace with your database username
		String dbPassword = "Orion123"; // Replace with your database password

		// Validate entityID
		if (entityID == null || entityID.isEmpty()) {
			throw new IllegalArgumentException("Entity ID cannot be null or empty");
		}

		String updateQuery = "UPDATE FCM_CARD SET ONLINE_AUTHORIZATION_CARD_STATUS = 'INACTIVE' WHERE CARD_NUM = '" + entityID + "'";
		String updateQueryEmail = "UPDATE FCM_CARD SET EMAIL_SENT = 'TRUE' WHERE CARD_NUM = '" + entityID + "'";

		Connection connection = null;
		Statement statement = null;

		try {
			// Establish database connection
			connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

			// Create statement
			statement = connection.createStatement();

			// Execute the query
			int rowsUpdated = statement.executeUpdate(updateQuery);
			
			if(EmailFlag.equals("1"))
			{
			statement.executeUpdate(updateQueryEmail);
			}
			
			// Check if any row was updated
			if (rowsUpdated > 0) {
				System.out.println("Successfully updated ONLINE_AUTHORIZATION_CARD_STATUS to Inactive for CARD_NUM: " + entityID);
			} else {
				throw new RuntimeException("No card found for CARD_NUM: " + entityID);
			}
		} catch (SQLException e) {
			throw new IOException("Database error occurred", e);
		} catch (Exception e) {
			throw new IOException("An error occurred while processing the CardBlock method", e);
		} finally {
			// Close resources in the finally block
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					System.err.println("Failed to close statement: " + e.getMessage());
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					System.err.println("Failed to close connection: " + e.getMessage());
				}
			}
		}
	}
	
	
	
		public String process(Exchange exchange) {
        String textJson = exchange.getIn().getBody(String.class);
		// System.out.println("-----Sample JSON Response Start-----");
		// System.out.println(textJson);
		JSONParser parser = new JSONParser();
        try {
            if (textJson != null && !textJson.isEmpty()) {
                JSONObject jsonObject = (JSONObject) parser.parse(textJson);
				String odeDecision = (String) jsonObject.get("rur_8byte_string_001") != null ? (String) jsonObject.get("rur_8byte_string_001"):"";
				String BlockingType = (String) jsonObject.get("rur_8byte_string_002") != null ? (String) jsonObject.get("rur_8byte_string_002"):"";
				String EntityID = (String) jsonObject.get("rur_30byte_string_001")!= null ? (String) jsonObject.get("rur_30byte_string_001"):"";
				String EmailFlag = (String) jsonObject.get("rur_ind_001")!= null ? (String) jsonObject.get("rur_ind_001"):"";
				
				if(BlockingType.equals("C_BLOCK"))  // Block account
				{
					sample_json_response.CardBlock(EntityID,EmailFlag);
				}
				
				if(BlockingType.equals("A_BLOCK"))  // Block account
				{
					sample_json_response.AccountBlock(EntityID,EmailFlag);
				}
				

				JSONObject resultJson = new JSONObject();
					resultJson.put("EFMSResponse", odeDecision);

 
				// Convert the result to a string and return it
				String resultString = resultJson.toString();
				//System.out.println(resultString);
				log.info("Result string: " + resultString);
				exchange.getIn().setBody(resultString);
				return resultString;
				
			}
            else {
				log.info("JSON data is empty or null.--------------------");
				return null;
            }
        } catch (Exception e) {
			log.error("Error processing JSON response: " + e.getMessage(), e);
            e.printStackTrace();
        }
    }
}