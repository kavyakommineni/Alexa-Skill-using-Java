package com.alexa.ask.helloworld.model;

/**
 * @author Kavya Kommineni
 * This class defines the Orders object with the corresponding fields
 */
public class Orders {
	private String id;
	private double cost;
	private String status;
	private Customer customer;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Orders(String id, double cost, String status, Customer customer) {
		super();
		this.id = id;
		this.cost = cost;
		this.status = status;
		this.customer = customer;
	}
	public Orders() {
		
	}
	@Override
	public String toString() {
		return "Orders [id=" + id + ", cost=" + cost + ", status=" + status + "]";
	}
}