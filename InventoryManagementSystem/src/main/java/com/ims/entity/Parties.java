package com.ims.entity;

import jakarta.persistence.*;

@Entity
@Table(name="parties")
public class Parties {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="email")
	private String email;
	
	@Column(name="phone_no", length=15)
	private long phoneNo;
	
	@Column(name="address")
	private String Address;

	
	public Parties() {
		
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}
    
	@Override
	public String toString() {
		return "Parties [id=" + id + ", name=" + name + ", email=" + email + ", phoneNo=" + phoneNo + ", Address=" + Address + "]";
	}
	
}