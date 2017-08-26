package br.com.pagandopave.impl;

import javax.ejb.Stateless;

import com.google.gson.Gson;

import br.com.pagandopave.api.PushNotificationManager;
import br.com.pagandopave.model.NotificationRequest;
import br.com.pagandopave.model.RegisterTokenRequest;
import br.com.pagandopave.notification.model.Data;
import br.com.pagandopave.notification.model.Notification;
import br.com.pagandopave.notification.model.NotificationMessage;
import br.com.pagandopave.notification.restclient.NotificationService;

@Stateless
public class PushNotificationManagerBean implements PushNotificationManager {

	@Override
	public boolean notifyParent(String input) {
			
		NotificationMessage notificationMessage = new NotificationMessage();
		notificationMessage.setTo("token");
		
		Notification notification = new Notification();
		
		notification.setBody("Body");
		notification.setTitle("Title");
		
		boolean result = NotificationService.sendNotificationToDevice(notificationMessage);
		
		return result;
	}

	@Override
	public boolean notifyTeen(String input) {

		Gson gson = new Gson();
		
		NotificationRequest request = gson.fromJson(input, NotificationRequest.class);
			
		NotificationMessage notificationMessage = new NotificationMessage();
		notificationMessage.setTo("token");
		
		Notification notification = new Notification();
		Data data = new Data();
		
		data.setParam("id");
		
		notification.setBody("");
		notification.setTitle(request.getTitulo());
		
		boolean result = NotificationService.sendNotificationToDevice(notificationMessage);
		
		return result;
	}

	@Override
	public boolean register(String input) {

		System.out.println("[register]");
		
		Gson gson = new Gson();
		
		RegisterTokenRequest registerReq = gson.fromJson(input, RegisterTokenRequest.class);
				
		System.out.println("[register] deviceId: " + registerReq.getDeviceId());
		System.out.println("[register] token: " + registerReq.getToken());
		
		return true;
	}

	@Override
	public boolean notifyAll(String input) {

		System.out.println("[notifyAll]");
		
		return true;
	}	
}
