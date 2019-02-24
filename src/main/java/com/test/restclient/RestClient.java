package com.test.restclient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class RestClient {
	
	//GetMethod without headers
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException, JSONException {
		CloseableHttpClient httpclient = HttpClients.createDefault();//Will create one httpclient
		HttpGet httpget = new HttpGet(url);//http get request
		CloseableHttpResponse closeablehttpresponse = httpclient.execute(httpget);//hit the get url
		return closeablehttpresponse;
		
}
	//GetMethod with headers
	public CloseableHttpResponse get(String url,HashMap<String,String> headerMap) throws ClientProtocolException, IOException, JSONException {
		CloseableHttpClient httpclient = HttpClients.createDefault();//Will create one httpclient
		HttpGet httpget = new HttpGet(url);//http get request
		
		for(Map.Entry<String, String> entry:headerMap.entrySet()) {
			httpget.addHeader(entry.getKey(),entry.getValue());
		}
		CloseableHttpResponse closeablehttpresponse = httpclient.execute(httpget);//hit the get url
		return closeablehttpresponse;
	
	
}
	//PostMethod
	public CloseableHttpResponse post(String url, String entityString, HashMap<String, String> headerMap) throws IOException {
		
		CloseableHttpClient  httpclient = HttpClients.createDefault();// Will create one httpclient
		HttpPost httppost = new HttpPost(url);//for http post request
		httppost.setEntity(new StringEntity(entityString));// for payload
		
		for (Map.Entry<String, String> entry:headerMap.entrySet()) {
			httppost.addHeader(entry.getKey(),entry.getValue());
		}
		
		CloseableHttpResponse closeablehttpresponse = httpclient.execute(httppost);//hit the post url
		return closeablehttpresponse;
		
		}
	
	//PutMethod
	
	 public CloseableHttpResponse put(String url, String entityString, HashMap<String,String> headerMap) throws ClientProtocolException, IOException {
		 CloseableHttpClient httpClient = HttpClients.createDefault(); //will create a http connection
		 HttpPut httpput = new HttpPut(url); // for put request
		 httpput.setEntity(new StringEntity(entityString));// for payload
		 
		 for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			 httpput.addHeader(entry.getKey(),entry.getValue());
		 }
			 
		CloseableHttpResponse closeablehttpresponse = httpClient.execute(httpput);
		return closeablehttpresponse;
			 
	}
	 
	 public CloseableHttpResponse delete(String url, HashMap<String, String> headerMap) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();//will create a http connection
		HttpDelete httpDelete = new HttpDelete(url); // for Delete Request
		
		for (Map.Entry<String, String> entry: headerMap.entrySet()) {
			httpDelete.addHeader(entry.getKey(),entry.getValue()); //For headers
		}
			CloseableHttpResponse closeablehttpresponse = httpClient.execute(httpDelete);
			return closeablehttpresponse;
			
			
		}
	}
	
		

