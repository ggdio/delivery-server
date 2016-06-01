package br.com.ggdio.delivery.cart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ggdio.delivery.cart.model.Cart;
import br.com.ggdio.delivery.cart.model.CartItem;
import br.com.ggdio.delivery.cart.repository.CartRepository;
import br.com.ggdio.delivery.exception.EntityNotFoundException;

@Service
public class CartService {

	private final CartRepository repository;
	
	@Autowired
	public CartService(CartRepository repository) {
		this.repository = repository;
	}
	
	public void set(Cart cart) {
		if(cart.getId() == null) {
			cart.setId(repository.generateId());
		}
		
		repository.save(cart);
	}
	
	public Cart get(String id) {
		return repository.get(id);
	}

	public void addItem(String id, CartItem item) {
		Cart cart = get(id);
		if(cart == null) throw new EntityNotFoundException("warning.cart.notfound", id);
		
		cart.addItem(item);
		set(cart);
	}
	
}
