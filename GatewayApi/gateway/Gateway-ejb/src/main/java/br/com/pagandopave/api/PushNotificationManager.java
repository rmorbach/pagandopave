package br.com.pagandopave.api;

import javax.ejb.Local;

@Local
public interface PushNotificationManager {

	public boolean notifyParent(String input);
	
	public boolean notifyTeen(String input);
	
	public boolean notifyAll(String input);
	
	public boolean register(String input);
}
