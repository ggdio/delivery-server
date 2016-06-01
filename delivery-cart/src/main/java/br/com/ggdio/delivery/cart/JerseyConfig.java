package br.com.ggdio.delivery.cart;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import br.com.ggdio.delivery.cart.resource.CartResource;

@Configuration
@ApplicationPath("/api/v1")
public class JerseyConfig extends ResourceConfig {
	
	@Value("${server.port}")
	private String serverPort;

	public JerseyConfig() throws ClassNotFoundException {
		registerEndpoints();
	}

	private void registerEndpoints() {
		register(CartResource.class);
	}
	
}