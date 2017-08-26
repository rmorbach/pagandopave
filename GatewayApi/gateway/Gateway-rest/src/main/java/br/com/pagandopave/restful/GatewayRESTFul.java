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
	
	@GET
	@Path("/saldo")
	@Produces(MediaType.APPLICATION_JSON)
	public Response consultarSaldo(String idCartao) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return Response.status(200).entity(agillitasManager.consultarSaldo(idCartao)).build();		
	}
	
	@POST
	@Path("/notify")
	@Produces(MediaType.APPLICATION_JSON)
	public Response sendNotification(String input) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return Response.status(200).entity(notificationManager.notifyParent(input)).build();		
	}
}
