import org.apache.camel.Exchange;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.JSONArray;
import org.apache.log4j.Logger
 
import java.util.*;
import java.io.*;
 
public class sample_json_response {
 
	private static final Logger log = Logger.getLogger("groovy");
    public String jsonMappingResponse (Exchange exchange) {
        String textJson = exchange.getIn().getBody(String.class);
		// System.out.println("-----Sample JSON Response Start-----");
		// System.out.println(textJson);
		JSONParser parser = new JSONParser();
        try {
            if (textJson != null && !textJson.isEmpty()) {
                JSONObject jsonObject = (JSONObject) parser.parse(textJson);
				String odeDecision = (String) jsonObject.get("rur_8byte_string_001");
				String CustomerId = (String) jsonObject.get("rur_30byte_string_002");
				String EFMSChannelReferenceNumber = (String) jsonObject.get("rur_30byte_string_001");

				JSONObject resultJson = new JSONObject();
					resultJson.put("EFMSChannelReferenceNumber", EFMSChannelReferenceNumber);
					resultJson.put("Customer Id", CustomerId);
					resultJson.put("EFMSResponse", odeDecision);

 
				// Convert the result to a string and return it
				String resultString = resultJson.toJSONString();
				//System.out.println(resultString);
				log.info("Result string: " + resultString);
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