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

public class PutAPITest extends TestBase{
	
	TestBase testBase;
	String URL;
	String putserviceURL;
	RestClient restClient;
	CloseableHttpResponse closeablehttpresponse;
	
	@BeforeMethod
	public void setup () {
		testBase = new TestBase();
		String baseURL = prop.getProperty("URL");
		String serviceURL = prop.getProperty("putserviceURL");
		URL = baseURL + serviceURL;
				System.out.println("URL is  -->"+URL);
	}
	
	@Test
	public void putAPITest() throws JsonGenerationException, JsonMappingException, IOException, JSONException {
		RestClient restClient = new RestClient();
		
		HashMap <String,String> headerMap = new HashMap<String,String>();
		headerMap.put("Content-Type", "application/json");
		
		//Jackson API to convert Java Object to Json
		
		ObjectMapper mapper = new ObjectMapper();
		UsersUpdate usersUpdate = new UsersUpdate("morpheus","zion resident");
		
		//convert java object to json - Marshalling
		
		mapper.writeValue(new File ("/Users/SANJAY/Documents/workspace/restapi/src/main/java/com/test/data/UsersUpdate.json"), usersUpdate);
		
		//Json in String
		
		String userUdpateResponseString = mapper.writeValueAsString(usersUpdate);
		System.out.println(userUdpateResponseString);
		
		//Call Put Method
		
		closeablehttpresponse = restClient.put(URL, userUdpateResponseString, headerMap);
		
		//get the response status code
		
		int responseStatusCode = closeablehttpresponse.getStatusLine().getStatusCode();
		System.out.println(responseStatusCode);
		Assert.assertEquals(responseStatusCode, RESPONSE_STATUS_CODE_200);
		
		//get the response body
		
		String responseString =  EntityUtils.toString(closeablehttpresponse.getEntity());
		JSONObject jsonResponseString = new JSONObject(responseString);
		System.out.println(jsonResponseString);
		
		UsersUpdate userUpdateResObj = mapper.readValue(responseString, UsersUpdate.class);
		System.out.println(usersUpdate.getName().equals(usersUpdate.getJob()));
		
		//Assert.assertTrue(usersUpdate.getName().equals(usersUpdate.getJob()));
	}

}
