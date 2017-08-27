package br.com.pagandopave.impl;

import javax.ejb.Stateless;

import br.com.pagandopave.api.PushNotificationManager;
import br.com.pagandopave.model.InserirCreditoRequest;
import br.com.pagandopave.model.NotificationParentRequest;
import br.com.pagandopave.notification.model.Data;
import br.com.pagandopave.notification.model.Notification;
import br.com.pagandopave.notification.model.NotificationMessage;
import br.com.pagandopave.notification.restclient.NotificationService;

@Stateless
public class PushNotificationManagerBean implements PushNotificationManager {

	private static String MESSAGE_PARENT = 
			"Seu filho %s está solicitando %s reais para %s";
	
	
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
		notification.setTitle("Pedido de recarga");
		notification.setBody(String.format(MESSAGE_PARENT, input.getFilho(), input.getValor(), input.getNomeEvento()));
		
		notificationMessage.setNotification(notification);
		notificationMessage.setData(data);
		
		boolean result = NotificationService.sendNotificationToDevice(notificationMessage);
		
		return result;
	}

	@Override
	public boolean notifyTeen(InserirCreditoRequest input, String token) {
	
		NotificationMessage notificationMessage = new NotificationMessage();
		notificationMessage.setTo(token);		
		
		Data data = new Data();		
		
		data.setTitle("Recarga realizada!");
		data.setBody("Você recebeu uma recarga de R$ " + input.getCredito()+ ". Responsabilidade, hein?!");
		
		notificationMessage.setData(data);
		
		boolean result = NotificationService.sendNotificationToDevice(notificationMessage);
		
		return result;
	}	

}
