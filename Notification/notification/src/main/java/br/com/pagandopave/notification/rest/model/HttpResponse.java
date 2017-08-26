package br.com.pagandopave.notification.rest.model;

public class HttpResponse {

	private String body;
	private Integer status;
	
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
