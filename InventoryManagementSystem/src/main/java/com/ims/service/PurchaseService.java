package com.ims.service;

import java.util.List;

import com.ims.dto.PurchaseDTO;
import com.ims.entity.Purchase;

public interface PurchaseService {

	public List<Purchase> findAll();
	
	public Purchase findById(int theId);
	
	public void save(Purchase thePurchase);
	
	public void deleteById(int theId);

	Purchase createPurchaseWithExistingParty(PurchaseDTO purchaseDTO);
	
}
