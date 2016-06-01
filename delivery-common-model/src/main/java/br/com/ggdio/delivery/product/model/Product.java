package br.com.ggdio.delivery.product.model;

import java.io.Serializable;

public class Product implements Serializable {

	private static final long serialVersionUID = -1586942050349006965L;

	private Long id;

	private String name;
	private double price;

	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(Long id, String name, double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		
		Product other = (Product) obj;
		if (id == null && other.id != null) return false;
		return id.equals(other.id);
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "[id="+id+",name="+name+"]";
	}

}