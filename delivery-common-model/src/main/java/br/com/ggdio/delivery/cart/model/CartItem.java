package br.com.ggdio.delivery.cart.model;

import java.io.Serializable;

import br.com.ggdio.delivery.product.model.Product;

public class CartItem implements Serializable {

	private static final long serialVersionUID = -9193000048016728812L;
	
	private Product product;
	private int quantity;
	
	public CartItem() {
		
	}
	
	public CartItem(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null) return false;
		
		CartItem other = (CartItem) obj;
		if(product == null && other.product != null) return false;
		return product.equals(other.product);
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "[product=["+product.toString()+"],quantity="+quantity+"]";
	}
	
}
