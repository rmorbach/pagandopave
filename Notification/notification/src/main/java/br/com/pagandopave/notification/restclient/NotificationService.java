package br.com.pagandopave.notification.restclient;

import java.io.IOException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.Gson;

import br.com.pagandopave.notification.model.NotificationMessage;
import br.com.pagandopave.notification.model.Parameters;
import br.com.pagandopave.notification.rest.model.HttpResponse;
import br.com.pagandopave.notification.util.HttpHelper;

public class NotificationService {

	private static final String FIREBASE_URL = "https://fcm.googleapis.com/fcm/send";
	private static final String KEY = Parameters.API_KEY;
	private static Integer READ_TIMEOUT = 10000;
	
	public static boolean sendNotificationToDevice(NotificationMessage message)
	{
		boolean result = false;
		URL firebaseURL;
		try {
			firebaseURL = new URL(FIREBASE_URL);
			Gson g = new Gson();		
			String parameters = g.toJson(message);
			HttpResponse resp = HttpHelper.sendPost(firebaseURL, parameters, "application/json", "application/json", READ_TIMEOUT, Parameters.API_KEY);
			if(resp != null && resp.getStatus() == 200)
			{
				result = true;
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConnectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return result;
	}
	
	public static void sendNotificationToGroup(String groupId)
	{
		
	}
	
	public static void sendNotificationToTopic(String topic)
	{
		
	}
	
}
