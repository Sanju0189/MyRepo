package com.api.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.base.TestBase;
import com.test.data.Users;
import com.test.data.UsersUpdate;
import com.test.restclient.RestClient;

public class PostAPITest extends TestBase{
	TestBase testBase;
	String baseURL;
	String serviceURL;
	String URL;
	RestClient restClient;
	CloseableHttpResponse closeablehttpresponse;
	
	@BeforeMethod
	public void setup() {
		testBase = new TestBase();
		baseURL=prop.getProperty("URL");
		serviceURL = prop.getProperty("serviceURL");
		URL = baseURL+serviceURL;
	}
	
	@Test
	public void postAPITest() throws JsonGenerationException, JsonMappingException, IOException, JSONException {
		 restClient = new RestClient();
		 HashMap <String, String> headerMap = new HashMap<String, String>();
		 headerMap.put("Content-Type", "application/json");
		 
		 //Jackson API- to convert the java object to json
		 
		 ObjectMapper mapper = new ObjectMapper();
		 Users users = new Users("morpheus", "leader");
		 
		//convert the java user object to json
		 
		 mapper.writeValue(new File("/Users/SANJAY/Documents/workspace/restapi/src/main/java/com/test/data/users.json"), users); 
		 
		 //Object to Json in String
		String usersJsonString= mapper.writeValueAsString(users);
		System.out.println(usersJsonString);
		
		//call post method from restclient class
		
		closeablehttpresponse = restClient.post(URL, usersJsonString, headerMap);
		
		//Fetch the response status code
		int statusResponseCode = closeablehttpresponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusResponseCode, RESPONSE_STATUS_CODE_201);
		
		//Fetch the response json
		String responseString = EntityUtils.toString(closeablehttpresponse.getEntity());
		JSONObject responseBody = new JSONObject(responseString);
		System.out.println("Response Body -->"+responseBody);
		
		
		Users userResObj = mapper.readValue(responseString, Users.class); //actual users object
		System.out.println(users.getName().equals(userResObj));
				
		
	}
	

}
