package br.com.pagandopave.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.google.gson.Gson;

import br.com.pagandopave.api.AgillitasApiManager;
import br.com.pagandopave.api.CampanhaManager;
import br.com.pagandopave.api.MainManager;
import br.com.pagandopave.api.PaiManager;
import br.com.pagandopave.api.PushNotificationManager;
import br.com.pagandopave.api.UsuarioManager;
import br.com.pagandopave.entidade.Pai;
import br.com.pagandopave.entidade.Usuario;
import br.com.pagandopave.model.ConsultarPortadorRequest;
import br.com.pagandopave.model.ConsultarPortadorResponse;
import br.com.pagandopave.model.InserirCreditoRequest;
import br.com.pagandopave.model.NotificationParentRequest;
import br.com.pagandopave.model.NotificationRequest;
import br.com.pagandopave.model.PedirDinheiroRequest;
import br.com.pagandopave.model.RegisterTokenPaiRequest;
import br.com.pagandopave.model.RegisterTokenRequest;
import br.com.pagandopave.notification.model.Data;
import br.com.pagandopave.notification.model.Notification;
import br.com.pagandopave.notification.model.NotificationMessage;
import br.com.pagandopave.notification.restclient.NotificationService;

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
	
	@EJB
	PaiManager paiManager;
	
	@Override
	public boolean inserirCredito(String input) {
		
		Gson gson = new Gson();

		InserirCreditoRequest req = gson.fromJson(input, InserirCreditoRequest.class);
		
		Usuario usuario = usuarioManager.consultarPorIdCartao(req.getIdCartao());
		
		if(agillitasManager.inserirCredito(input)){
			notificationManager.notifyTeen(req, usuario.getPushToken());
			return true;
		}		
		
		return false;
	}
	
	public boolean pedirDinheiro(String input){
		
		Gson gson = new Gson();
		
		PedirDinheiroRequest req = gson.fromJson(input, PedirDinheiroRequest.class);
			
		Usuario usuario = usuarioManager.consultarPorIdCartao(req.getIdCartao());
		
		ConsultarPortadorRequest consultarPortadorReq = new ConsultarPortadorRequest();
		consultarPortadorReq.setIdCartao(req.getIdCartao());
		
		String consultarPortadorResp = agillitasManager.consultarPortador(gson.toJson(consultarPortadorReq));
		
		ConsultarPortadorResponse gsonPortadorResponse = gson.fromJson(consultarPortadorResp, ConsultarPortadorResponse.class);
			
		NotificationParentRequest parentNotification = new NotificationParentRequest();
		parentNotification.setFilho(gsonPortadorResponse.getNome());
		parentNotification.setNomeEvento(req.getNomeEvento());
		parentNotification.setNumeroCartao(usuario.getIdCartao());
		parentNotification.setValor(req.getValor());
				
		Pai pai = paiManager.consultarPorIdPai(String.valueOf(usuario.getIdPai()));
		
		parentNotification.setToken(pai.getPushToken());
				
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
		
		Usuario usuario = usuarioManager.consultarPorIdCartao(registerReq.getIdCartao());
		
		System.out.println("[register] usuario: " + usuario.toString());
		
		usuario.setDeviceId(registerReq.getDeviceId());
		usuario.setPushToken(registerReq.getToken());
				
		usuarioManager.alterar(usuario);
		
		return true;
	}

	@Override
	public boolean notifyAll(String input) {
		
		List<Usuario> usuarios = usuarioManager.consultar();
		
		System.out.println("[notifyAll]");
		
		Gson gson = new Gson();		
		
		NotificationRequest req = gson.fromJson(input, NotificationRequest.class);
		
		for(Usuario u : usuarios){
			
			NotificationMessage notificationMessage = new NotificationMessage();
			notificationMessage.setTo(u.getPushToken());
			
			Notification notification = new Notification();
			Data data = new Data();
			
			data.setTitle(req.getTitulo());
			data.setBody(req.getDescricao());
			
			notificationMessage.setData(data);
			
			NotificationService.sendNotificationToDevice(notificationMessage);
		}				
		
		return true;
	}

	@Override
	public boolean registerTokenPai(String input) {

		Gson gson = new Gson();
		
		RegisterTokenPaiRequest registerReq = gson.fromJson(input, RegisterTokenPaiRequest.class);
				
		System.out.println("[registregisterTokenPaier] idPai: " + registerReq.getIdPai());
		System.out.println("[registerTokenPai] token: " + registerReq.getToken());
		
		Pai pai = paiManager.consultarPorIdPai(registerReq.getIdPai());
		
		pai.setPushToken(registerReq.getToken());
		
		paiManager.alterar(pai);
		
		return false;
	}

	@Override
	public String consultarSaldo(String input) {
		return agillitasManager.consultarSaldo(input);
	}

	@Override
	public String consultarExtrato(String input) {
		return agillitasManager.consultarExtrato(input);
	}

}
