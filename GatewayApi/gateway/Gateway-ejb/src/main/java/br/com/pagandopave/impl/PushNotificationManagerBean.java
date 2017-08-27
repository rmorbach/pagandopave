package br.com.pagandopave.impl;

import javax.ejb.Stateless;

import com.google.gson.Gson;

import br.com.pagandopave.api.PushNotificationManager;
import br.com.pagandopave.model.NotificationParentRequest;
import br.com.pagandopave.model.NotificationRequest;
import br.com.pagandopave.model.RegisterTokenRequest;
import br.com.pagandopave.notification.model.Data;
import br.com.pagandopave.notification.model.Notification;
import br.com.pagandopave.notification.model.NotificationMessage;
import br.com.pagandopave.notification.restclient.NotificationService;

@Stateless
public class PushNotificationManagerBean implements PushNotificationManager {

	private static String MESSAGE_PARENT = 
			"Olá! O cartão do %s está ficando sem crédito. Não quer fazer uma recarga?";
	
	private static String MESSAGE_PROMOTION = 
			"Nova promoção no ar! %s. Traga %s amigos e ganhe um desconto!";
	
	private static String MESSAGE_TEEN = 
			"Olá, %s! Seu cartão foi recarregado! Agora seu saldo é de %s. Use com responsabilidade, hein!";
	
	@Override
	public boolean notifyParent(NotificationParentRequest input) {
			
		NotificationMessage notificationMessage = new NotificationMessage();
		notificationMessage.setTo(input.getToken());
				
		Data data = new Data();
		data.setFilho(input.getFilho());
		data.setNomeEvento(input.getNomeEvento());
		data.setValor(input.getValor());
		data.setNumeroCartao(input.getNumeroCartao());
		
		Notification notification = new Notification();
		notification.setTitle("Nova promoção no ar!");
		notification.setBody("Body");
		
		notificationMessage.setNotification(notification);
		notificationMessage.setData(data);
		
		boolean result = NotificationService.sendNotificationToDevice(notificationMessage);
		
		return result;
	}

	@Override
	public boolean notifyTeen(String input) {

		Gson gson = new Gson();		
		
		NotificationMessage notificationMessage = new NotificationMessage();
		notificationMessage.setTo("fobvU3KbiDo:APA91bGlRN87CqSXLHdS8Z7t8oS38hH0onVr0u-jStPiCmqcA7nlX009zR1GgceylUSlJ3QW2J8DrKW0egq2W8JR3mCTZ0ZD5McQLbLUcfJ_ZSUjnfhlqNSdwOBAob12iCwklFldwTPB");
		
		Notification notification = new Notification();
		Data data = new Data();
		
		data.setTitle("Promocao!!");
		data.setBody("Confira a promocao da semana. Pague seis leve meia duzia.");
		
		notificationMessage.setData(data);
		
		//notification.setBody("");
		//notification.setTitle(request.getTitulo());
		
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
		
		Gson gson = new Gson();		
		
		NotificationMessage notificationMessage = new NotificationMessage();
		notificationMessage.setTo("fobvU3KbiDo:APA91bGlRN87CqSXLHdS8Z7t8oS38hH0onVr0u-jStPiCmqcA7nlX009zR1GgceylUSlJ3QW2J8DrKW0egq2W8JR3mCTZ0ZD5McQLbLUcfJ_ZSUjnfhlqNSdwOBAob12iCwklFldwTPB");
		
		Notification notification = new Notification();
		Data data = new Data();
		
		data.setTitle("Promocao!!");
		data.setBody("Confira a promocao da semana. Pague seis leve meia duzia.");
		
		notificationMessage.setData(data);
		
		//notification.setBody("");
		//notification.setTitle(request.getTitulo());
		
		boolean result = NotificationService.sendNotificationToDevice(notificationMessage);		
		
		return result;
	}	
}
