package br.com.pagandopave.notification.model;

public class NotificationMessage {

	private String to;
	private Notification notification;
	private Data data;
	
	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}




	public Notification getNotification() {
		return notification;
	}




	public void setNotification(Notification notification) {
		this.notification = notification;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}
	
	
}
