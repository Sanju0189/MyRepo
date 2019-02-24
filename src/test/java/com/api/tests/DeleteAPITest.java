package com.api.tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.test.base.TestBase;
import com.test.restclient.RestClient;

public class DeleteAPITest extends TestBase {
	
	TestBase testBase;
	String URL;
	String deleteserviceURL;
	RestClient restClient;
	CloseableHttpResponse closeablehttpresponse;

	@BeforeMethod
	public void setup() {
		testBase = new TestBase();
		String baseURL = prop.getProperty("URL");
		String serviceURL = prop.getProperty("deleteserviceURL");
		URL = baseURL+serviceURL;
		System.out.println(URL);
	}
	
	@Test
	public void deleteAPITest() throws ClientProtocolException, IOException {
		RestClient restClient = new RestClient();
		HashMap <String,String> headerMap = new HashMap<String,String>();
		headerMap.put("Content-Type", "application/json");
		
		closeablehttpresponse = restClient.delete(URL, headerMap);
		
		//fetch the response code
		int statusResponseCode = closeablehttpresponse.getStatusLine().getStatusCode();
		System.out.println("Response code is -->"+statusResponseCode);
		Assert.assertEquals(statusResponseCode, RESPONSE_STATUS_CODE_204);
		
	}
}
