package com.ims.entity;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Table(name="sell")
public class Sell {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name = "customer_name")
	private String name;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@Column(name = "price")
	private double price;

	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "discount")
	private double discount;
	
	@Column(name="date")
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Column(name="phone_no", length=10)
	private long phoneNo;
	
	@Column(name="total_amt")
	private int total;
	
	public Sell() {
		
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public double getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
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
		return "Sell [id=" + id + ", name=" + name + ", product=" + product + ", price=" + price + ", quantity="
				+ quantity + ", discount=" + discount + ", date=" + date + ", phoneNo=" + phoneNo + ", total=" + total
				+ "]";
	}
	
}