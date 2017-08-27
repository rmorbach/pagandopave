package br.com.pagandopave.api;

import javax.ejb.Local;

import br.com.pagandopave.model.NotificationParentRequest;

@Local
public interface PushNotificationManager {

	public boolean notifyParent(NotificationParentRequest input);
	
	public boolean notifyTeen(String input);
	
	public boolean notifyAll(String input);
	
}
