package br.com.ggdio.delivery.cart.resource;

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
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ggdio.delivery.cart.model.Cart;
import br.com.ggdio.delivery.cart.model.CartItem;
import br.com.ggdio.delivery.cart.service.CartService;
import br.com.ggdio.delivery.exception.EntityNotFoundException;

@Component
@Path("/cart")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CartResource {
	
	@Autowired
	private CartService service;

	@POST
	public Response createCart(@Context UriInfo uriInfo, Cart cart) {
		service.set(cart);
		
		return Response.created(uriInfo.getAbsolutePathBuilder().path(cart.getId()).build()).build();
	}
	
	@GET
	@Path("/{id}")
	public Response getCart(@PathParam("id") String id) {
		Cart cart = service.get(id);
		if(cart != null) {
			return Response.ok(cart).build();
		}
		
		return Response.status(Status.NOT_FOUND).build();
	}
	
	@POST
	@Path("/{id}")
	public Response addCartItem(@Context UriInfo uriInfo, @PathParam("id") String id, CartItem item) {
		try {
			service.addItem(id, item);
			
		} catch(EntityNotFoundException e){
			return Response.status(Status.NOT_FOUND).build();
			
		}
		
		return Response.created(uriInfo.getAbsolutePathBuilder().path(item.getProduct().getId().toString()).build()).build();
	}
	
}
