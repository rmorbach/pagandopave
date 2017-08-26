package br.com.pagandopave.impl;

import com.google.gson.Gson;

import br.com.pagandopave.api.PushNotificationManager;
import br.com.pagandopave.notification.NotificationModel;
import br.com.pagandopave.notification.model.Notification;
import br.com.pagandopave.notification.model.NotificationMessage;
import br.com.pagandopave.notification.restclient.NotificationService;

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
		
		NotificationModel model = gson.fromJson(input, NotificationModel.class);
			
		NotificationMessage notificationMessage = new NotificationMessage();
		notificationMessage.setTo("token");
		
		Notification notification = new Notification();
		
		notification.setBody("Body");
		notification.setTitle("Title");
		
		boolean result = NotificationService.sendNotificationToDevice(notificationMessage);
		
		return result;
	}

	
}
