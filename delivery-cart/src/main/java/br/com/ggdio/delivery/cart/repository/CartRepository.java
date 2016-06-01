package br.com.ggdio.delivery.cart.repository;

import br.com.ggdio.delivery.cart.model.Cart;

public interface CartRepository {

	public void save(Cart cart);
	
	public Cart get(String id);
	
	public void delete(String id);
	
	public String generateId();
	
}
