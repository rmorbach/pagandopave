package br.com.pagandopave.restful;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.pagandopave.api.UsuarioManager;
import br.com.pagandopave.entidade.Usuario;

@Named
@Path("/user")
public class UsuarioRESTFul {
	
	@EJB
	private UsuarioManager usuarioManager;
	
	@POST
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(@Context HttpServletRequest request, Usuario usuario) {
		usuarioManager.cadastrar(usuario);
		return Response.status(Response.Status.OK).build();
	}
	
	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@Context HttpServletRequest request, Usuario usuario) {
		Usuario user = usuarioManager.alterar(usuario);
		return Response.status(Response.Status.OK).entity(user).build();
	}

	
	@DELETE
	@Path("/remove/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response remove(@Context HttpServletRequest request, Usuario usuario) {
		usuarioManager.deletar(usuario);
		return Response.status(Response.Status.OK).build();
	}
	
	@GET
	@Path("/find/{deviceId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response find(@PathParam("deviceId") String deviceId) {
		Usuario out = usuarioManager.consultarPorDeviceId(deviceId);
		return Response.status(Response.Status.OK).entity(out).build();
	}

}
