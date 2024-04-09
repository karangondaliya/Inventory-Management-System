package com.ims.dto;

import java.sql.Date;



public class SellDTO {
	
	private int id;
	
	private String name;

	private ProductDTO product;

	private int price;
	
	private int quantity;
	
	private int discount;
	
	private Date date;
	
	private long phoneNo;
	
	private int total;
	
	public SellDTO() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}

	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "SellDTO [id=" + id + ", name=" + name + ", product=" + product + ", price=" + price + ", quantity="
				+ quantity + ", discount=" + discount + ", date=" + date + ", phoneNo=" + phoneNo + ", total=" + total
				+ "]";
	}
	
	
	
	
}