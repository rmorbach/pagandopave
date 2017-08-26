package br.com.pagandopave.restful;

import java.io.IOException;

import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;

@Named
@Path("/test")
public class TestRESTFul {
	
	
	@GET
	@Path("/echo")
	@Produces(MediaType.APPLICATION_JSON)
	public Response echo() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return Response.status(200).entity("REST API OK!").build();		
	}
	
}
