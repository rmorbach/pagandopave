package br.com.pagandopave.restful;

import java.io.IOException;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.pagandopave.api.AgillitasApiManager;
import br.com.pagandopave.api.PushNotificationManager;

@Named
@Path("/gateway")
public class GatewayRESTFul {

	@EJB
	AgillitasApiManager agillitasManager;
	
	@EJB
	PushNotificationManager notificationManager;
	
	@POST
	@Path("/saldo")
	@Produces(MediaType.APPLICATION_JSON)
	public Response consultarSaldo(String idCartao) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return Response.status(200).entity(agillitasManager.consultarSaldo(idCartao)).build();		
	}
	
	@POST
	@Path("/notifyAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Response notifyAll(String input) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return Response.status(200).entity(notificationManager.notifyAll(input)).build();		
	}
	
	@POST
	@Path("/notifyParent")
	@Produces(MediaType.APPLICATION_JSON)
	public Response notifyParent(String input) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return Response.status(200).entity(notificationManager.notifyParent(input)).build();		
	}
	
	@POST
	@Path("/notifyTeen")
	@Produces(MediaType.APPLICATION_JSON)
	public Response notifyTeen(String input) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return Response.status(200).entity(notificationManager.notifyTeen(input)).build();		
	}
	
	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON)
	public Response registerToken(String input) throws IOException {
		
		System.out.println("[registerToken]");
		
		ObjectMapper mapper = new ObjectMapper();
		return Response.status(200).entity(notificationManager.register(input)).build();		
	}
	
	@POST
	@Path("/consultarPortador")
	@Produces(MediaType.APPLICATION_JSON)
	public Response consultarPortador(String input) throws IOException {
		
		System.out.println("[consultarPortador]");
		
		ObjectMapper mapper = new ObjectMapper();
		return Response.status(200).entity(agillitasManager.consultarPortador(input)).build();		
	}
	@POST
	@Path("/ativarCartao")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ativarCartao(String input) throws IOException {
		
		System.out.println("[ativarCartao]");
		
		ObjectMapper mapper = new ObjectMapper();
		return Response.status(200).entity(agillitasManager.ativarCartao(input)).build();		
	}
	
	@POST
	@Path("/inserirCredito")
	@Produces(MediaType.APPLICATION_JSON)
	public Response inserirCredito(String input) throws IOException {
		
		System.out.println("[inserirCredito]");
		
		ObjectMapper mapper = new ObjectMapper();
		return Response.status(200).entity(agillitasManager.inserirCredito(input)).build();		
	}
}
