package com.alexa.ask.helloworld.model;

import java.util.Date;

/**
 * @author Kavya Kommineni
 * This class defines the Shipment object with the corresponding fields
 *
 */
public class Shipment {
	
	private String id;
	private Date date;
	private Seller seller;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Seller getSeller() {
		return seller;
	}
	public void setSeller(Seller seller) {
		this.seller = seller;
	}
	public Shipment(String id, Date date, Seller seller) {
		super();
		this.id = id;
		this.date = date;
		this.seller = seller;
	}
	public Shipment() {
		
	}
	@Override
	public String toString() {
		return "Shipment [id=" + id + ", date=" + date + "]";
	}
}
