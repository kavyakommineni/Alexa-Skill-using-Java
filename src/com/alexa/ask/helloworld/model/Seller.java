package com.alexa.ask.helloworld.model;

/**
 * @author Kavya Kommineni
 * This class defines the Seller object with the corresponding fields
 *
 */
public class Seller {

	private String id;
	private String name;
	private String address;
	private String number;
	private double rating;
	
	private Product product;
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public Seller(String id, String name, String address, String number, double rating,Product product) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.number = number;
		this.rating = rating;
		this.product = product;
	}
	public Seller() {
		super();
	}
	@Override
	public String toString() {
		return "Seller [id=" + id + ", name=" + name + ", address=" + address + ", number=" + number + ", rating="
				+ rating + "]";
	}
}
