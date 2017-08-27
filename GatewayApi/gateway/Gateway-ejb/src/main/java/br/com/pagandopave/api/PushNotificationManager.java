package br.com.pagandopave.api;

import javax.ejb.Local;

import br.com.pagandopave.model.InserirCreditoRequest;
import br.com.pagandopave.model.NotificationParentRequest;

@Local
public interface PushNotificationManager {

	public boolean notifyParent(NotificationParentRequest input);
	
	public boolean notifyTeen(InserirCreditoRequest input, String token);
	
//	public boolean notifyAll(String input);
	
}
