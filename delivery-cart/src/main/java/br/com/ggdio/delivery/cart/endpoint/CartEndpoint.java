package br.com.ggdio.delivery.cart.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.stereotype.Component;

@Component
@Path("/cart")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CartEndpoint {

	@POST
	public Response createCart(@Context UriInfo uriInfo) {
		return Response.created(uriInfo.getRequestUriBuilder().build("xpto").normalize()).build();
	}
	
	@GET
	@Path("/{id}")
	public Response getCart(@PathParam("id") String id) {
		return Response.ok("{\"id\": \""+id+"\", \"name\": \"default-cart\"}").build();
	}
	
}
