package com.ims.dao;

import java.util.List;

import com.ims.entity.Purchase;

public interface PurchaseDAO {

	public List<Purchase> findAll();
	
	public Purchase findById(int theId);
	
	public Purchase save(Purchase thePurchase);
	
	public void deleteById(int theId);
	
}
