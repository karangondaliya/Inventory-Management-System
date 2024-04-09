package com.ims.dto;

import java.sql.Date;

public class PurchaseDTO {
	
	private int id;
	private PartiesDTO party;
	private ProductDTO product;
	private int price;
	private int quantity;
	private Date date;
	private int discount;
	private int total;
	
	public PurchaseDTO() {
		
	}

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public PartiesDTO getParty() {
		return party;
	}


	public void setParty(PartiesDTO party) {
		this.party = party;
	}


	public ProductDTO getProduct() {
		return product;
	}


	public void setProduct(ProductDTO product) {
		this.product = product;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public int getDiscount() {
		return discount;
	}


	public void setDiscount(int discount) {
		this.discount = discount;
	}


	public int getTotal() {
		return total;
	}


	public void setTotal(int total) {
		this.total = total;
	}


	@Override
	public String toString() {
		return "PurchaseDTO [id=" + id + ", party=" + party + ", product=" + product + ", price=" + price
				+ ", quantity=" + quantity + ", date=" + date + ", discount=" + discount + ", total=" + total + "]";
	}

	
}
