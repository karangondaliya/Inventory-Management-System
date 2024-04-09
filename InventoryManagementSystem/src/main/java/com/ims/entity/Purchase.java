package com.ims.entity;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Table(name="purchase")
public class Purchase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@ManyToOne
	@JoinColumn(name = "party_id")
	private Parties party;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@Column(name = "purchase_price")
	private int price;
	
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="date")
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Column(name="discount")
	private int discount;
	
	@Column(name="total_amt")
	private int total;
	
	public Purchase() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Parties getParty() {
		return party;
	}

	public void setParty(Parties party) {
		this.party = party;
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

	public void setPrice(int price) {
		this.price = price;
	}

	public long getQuantity() {
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

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Purchase [id=" + id + ", party=" + party + ", product=" + product + ", price=" + price + ", quantity="
				+ quantity + ", date=" + date + ", discount=" + discount + ", total=" + total + "]";
	}

}
