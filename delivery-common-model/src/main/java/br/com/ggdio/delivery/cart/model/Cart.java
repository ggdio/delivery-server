package br.com.ggdio.delivery.cart.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart implements Serializable {

	private static final long serialVersionUID = 6048647681149831955L;

	private String id;
	
	private List<CartItem> items;
	
	public Cart() {
		this(null, new ArrayList<>());
	}
	
	public Cart(String id, List<CartItem> items) {
		this.id = id;
		this.items = items;
	}

	public String getId() {
		return id;
	}
	
	public List<CartItem> getItems() {
		return Collections.unmodifiableList(items);
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public CartItem getItem(Long id) {
		return items.stream().filter(i -> i.getProduct().getId().equals(id)).findFirst().get();
	}
	
	public void removeItem(Long id) {
		items.removeIf(i -> i.getProduct().getId().equals(id));
	}
	
	public void addItem(CartItem item) {
		items.add(item);
	}
	
}