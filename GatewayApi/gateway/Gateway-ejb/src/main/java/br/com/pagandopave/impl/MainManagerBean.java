package br.com.pagandopave.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.google.gson.Gson;

import br.com.pagandopave.api.AgillitasApiManager;
import br.com.pagandopave.api.CampanhaManager;
import br.com.pagandopave.api.MainManager;
import br.com.pagandopave.api.PushNotificationManager;
import br.com.pagandopave.api.UsuarioManager;
import br.com.pagandopave.entidade.Usuario;
import br.com.pagandopave.model.InserirCreditoRequest;
import br.com.pagandopave.model.NotificationParentRequest;
import br.com.pagandopave.model.PedirDinheiroRequest;
import br.com.pagandopave.model.RegisterTokenRequest;

@Stateless
public class MainManagerBean implements MainManager {

	@EJB
	PushNotificationManager notificationManager;
	
	@EJB
	AgillitasApiManager agillitasManager;
	
	@EJB
	UsuarioManager usuarioManager;
	
	@EJB 
	CampanhaManager campanhaManager;
	
	@Override
	public boolean inserirCredito(String input) {
		
		Gson gson = new Gson();

		InserirCreditoRequest req = gson.fromJson(input, InserirCreditoRequest.class);

		
//		if(agillitasManager.inserirCredito(input)){
//			notificationManager.notifyParent(input);
//			return true;
//		}		
		
		
		
		return false;
	}
	
	public boolean pedirDinheiro(String input){
		
		Gson gson = new Gson();
		
		PedirDinheiroRequest req = gson.fromJson(input, PedirDinheiroRequest.class);
		
		Usuario usuario = usuarioManager.consultarPorDeviceId(req.getDeviceId());
		
		NotificationParentRequest parentNotification = new NotificationParentRequest();
		parentNotification.setFilho("Rodrigo");
		parentNotification.setNomeEvento("Cineminha");
		parentNotification.setNumeroCartao(usuario.getIdCartao());
		parentNotification.setValor("R$ 10,00");
		parentNotification.setToken("dsgue97D1WU:APA91bF_WpQELwKODxQgX0M3-qvAgiUObkfa8JhgKopR6q0cwwDDk2xllm672q11dRGNzZzyc11r8-t8Gds7A-sBew1HM48IO1l0Jzbzay_-oGMwMMvNqIQw6GBpjYjWMrRF95BXj41E");
				
		notificationManager.notifyParent(parentNotification);
		
		return false;
	}

	@Override
	public String buscarCampanhas() {
			
		return campanhaManager.buscarCampanhas();
	}

	@Override
	public boolean registerToken(String input) {
		
		Gson gson = new Gson();
		
		RegisterTokenRequest registerReq = gson.fromJson(input, RegisterTokenRequest.class);
				
		System.out.println("[register] deviceId: " + registerReq.getDeviceId());
		System.out.println("[register] token: " + registerReq.getToken());
		
		Usuario usuario = new Usuario();
		usuario.setDeviceId(registerReq.getDeviceId());
		usuario.setPushToken(registerReq.getToken());
		
		usuarioManager.cadastrar(usuario);
		
		return true;
	}

}
