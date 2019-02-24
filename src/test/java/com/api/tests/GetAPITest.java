package com.api.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.test.base.TestBase;
import com.test.restclient.RestClient;
import com.test.util.TestUtil;

public class GetAPITest extends TestBase {
	TestBase testBase;
	CloseableHttpResponse closeablehttpresponse;
	String baseURL;
	String endPointURL;
	String URL;
	RestClient restclient;
	
	@BeforeMethod
	public void setup() {
		testBase = new TestBase();
		baseURL = prop.getProperty("URL");
		endPointURL = prop.getProperty("serviceURL");
		URL = baseURL+endPointURL;
	}
	
	@Test(priority=1)
	public void getAPITestWithoutHeaders() throws ClientProtocolException, IOException, JSONException {
		restclient = new RestClient();
		closeablehttpresponse = restclient.get(URL);
		
		//StatusCode
		int responseStausCode = closeablehttpresponse.getStatusLine().getStatusCode();
		System.out.println("StatusCode is "+responseStausCode);
		Assert.assertEquals(responseStausCode, RESPONSE_STATUS_CODE_200,"Status code is not 200");
		
		//StatusResponse
		String responseString = EntityUtils.toString(closeablehttpresponse.getEntity());
		JSONObject responsebody = new JSONObject(responseString);
		System.out.println("Responsebody"+responsebody);
		
		//Fetching Perpage value and asserting
		String perPageValue = TestUtil.getValueByJPath(responsebody, "/per_page");
		System.out.print("Value of Per Page" + perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue),3);
		
		//Fetching total value and asserting
		String totalValue = TestUtil.getValueByJPath(responsebody, "/total");
		System.out.println("Value of Total"+ totalValue);
		Assert.assertEquals(Integer.parseInt(totalValue), 12);
		
		
		//Fetching data from JSONArray
		String id = TestUtil.getValueByJPath(responsebody, "/data[0]/id");
		String firstName = TestUtil.getValueByJPath(responsebody, "/data[0]/first_name");
		String lastName = TestUtil.getValueByJPath(responsebody, "/data[1]/last_name");
		
		System.out.println("Value of id is "+ id);
		System.out.println("Value of FirstName is "+ firstName);
		System.out.println("Value of id is "+ lastName);
		
		//Asserting the JSON Array values
		Assert.assertEquals(Integer.parseInt(id), 4);
		Assert.assertEquals(firstName, "Eve");
		Assert.assertEquals(lastName, "Morris");
		
		
		//ResposneHeaders
		Header[] headerArray=closeablehttpresponse.getAllHeaders();
		HashMap <String,String> responseHeaders = new HashMap <String,String>();
		for (Header header :headerArray ) {
		responseHeaders.put(header.getName(), header.getValue());
	}
	
	System.out.println("ResponseHeaders" + responseHeaders);
	}
	
	@Test(priority=2)
	public void getAPITestWithHeaders() throws ClientProtocolException, IOException, JSONException {
		restclient = new RestClient();
		
		
		HashMap<String,String> headerMap = new HashMap<String,String>();
		headerMap.put("Content-Type", "application/json");
		headerMap.put("session_id", "123");
		
		closeablehttpresponse = restclient.get(URL,headerMap);
		
		//StatusCode
		int responseStausCode = closeablehttpresponse.getStatusLine().getStatusCode();
		System.out.println("StatusCode is "+responseStausCode);
		Assert.assertEquals(responseStausCode, RESPONSE_STATUS_CODE_200,"Status code is not 200");
		
		//StatusResponse
		String responseString = EntityUtils.toString(closeablehttpresponse.getEntity());
		JSONObject responsebody = new JSONObject(responseString);
		System.out.println("Responsebody"+responsebody);
		
		//Fetching Perpage value and asserting
		String perPageValue = TestUtil.getValueByJPath(responsebody, "/per_page");
		System.out.print("Value of Per Page" + perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue),3);
		
		//Fetching total value and asserting
		String totalValue = TestUtil.getValueByJPath(responsebody, "/total");
		System.out.println("Value of Total"+ totalValue);
		Assert.assertEquals(Integer.parseInt(totalValue), 12);
		
		
		//Fetching data from JSONArray
		String id = TestUtil.getValueByJPath(responsebody, "/data[0]/id");
		String firstName = TestUtil.getValueByJPath(responsebody, "/data[0]/first_name");
		String lastName = TestUtil.getValueByJPath(responsebody, "/data[1]/last_name");
		
		System.out.println("Value of id is "+ id);
		System.out.println("Value of FirstName is "+ firstName);
		System.out.println("Value of id is "+ lastName);
		
		//Asserting the JSON Array values
		Assert.assertEquals(Integer.parseInt(id), 4);
		Assert.assertEquals(firstName, "Eve");
		Assert.assertEquals(lastName, "Morris");
		
		
		//ResposneHeaders
		Header[] headerArray=closeablehttpresponse.getAllHeaders();
		HashMap <String,String> responseHeaders = new HashMap <String,String>();
		for (Header header :headerArray ) {
		responseHeaders.put(header.getName(), header.getValue());
	}
	
	System.out.println("ResponseHeaders" + responseHeaders);
	}
	

}
