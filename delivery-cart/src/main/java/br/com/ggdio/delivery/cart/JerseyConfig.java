package br.com.ggdio.delivery.cart;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import br.com.ggdio.delivery.cart.endpoint.CartEndpoint;

@Component
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() throws ClassNotFoundException {
		registerEndpoints();
	}

	private void registerEndpoints() {
		register(CartEndpoint.class);
	}
	
}