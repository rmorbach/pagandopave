package br.com.pagandopave.notification.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

import br.com.pagandopave.notification.rest.model.HttpResponse;

public class HttpHelper {

	
public static HttpResponse sendPost(URL url, String urlParameters, String accept, String contentType, int readTimeout, String authString) throws IOException, ConnectException {

		
		// Create Http connection
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		// Set POST method
		connection.setRequestMethod("POST");

		// Set parameters
		if(accept != null){
			connection.setRequestProperty("Accept", accept);
		}
		if(contentType != null){
			connection.setRequestProperty("Content-Type", contentType);
		}
		
		if(authString != null && !authString.equals(""))
		{			
			connection.setRequestProperty("Authorization", "key="+authString);
		}
		
		connection.setReadTimeout(readTimeout);		
		// Send POST request
		connection.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(connection.getOutputStream());

		if(urlParameters != null){
			wr.write(urlParameters.getBytes(Charset.forName("UTF-8")));			
		}
		wr.flush();
		wr.close();
		
		System.out.println("[sendPost] Post parameters : "+urlParameters);
		

		// Get responseCode
		int responseCode = connection.getResponseCode();

		HttpResponse httpResponse = new HttpResponse();
		httpResponse.setStatus(responseCode);
		
		// If response code is not 200 (success), return null
		if(responseCode != 200){
			return httpResponse;
		}

		System.out.println("[sendPost] ResponseCode: " + responseCode);

		BufferedReader in = new BufferedReader(
				new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));
		String inputLine;
		StringBuffer response = new StringBuffer();

		// Read response line by line
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		httpResponse.setBody(response.toString());
		//return response.toString();
		return httpResponse;
	}
	
	
	public static HttpResponse sendGet(URL url, String urlParameters, String accept, String contentType, int readTimeout, String authorizationToken) throws IOException, ConnectException {

		System.out.println("[sendGet]");

		// Create Http connection
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		// Set POST method
		connection.setRequestMethod("GET");

		// Set parameters
		if(accept != null){
			connection.setRequestProperty("Accept", accept);
		}
		
		if(authorizationToken != null && !authorizationToken.equals(""))
		{
			connection.setRequestProperty("Authorization", "key="+authorizationToken);
		}

		connection.setReadTimeout(readTimeout);

		HttpResponse httpResponse = new HttpResponse();
		// Get responseCode
		int responseCode = connection.getResponseCode();
		httpResponse.setStatus(responseCode);
		// If response code is not 200 (success), return null
		if(responseCode != 200){
			return httpResponse;
		}
							
		System.out.println("[sendGet] ResponseCode: " + responseCode);

		BufferedReader in = new BufferedReader(
				new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));
		String inputLine;
		StringBuffer response = new StringBuffer();

		// Read response line by line
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		httpResponse.setBody(response.toString());
//		return response.toString();
		return httpResponse;
	}

	
}
