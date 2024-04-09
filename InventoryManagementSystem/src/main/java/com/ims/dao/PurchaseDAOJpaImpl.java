package com.ims.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ims.entity.Purchase;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Repository
public class PurchaseDAOJpaImpl implements PurchaseDAO{

	private EntityManager entityManager;
	
	@Autowired
	public PurchaseDAOJpaImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	public List<Purchase> findAll() {
		TypedQuery<Purchase> theQuery = entityManager.createQuery("from Purchase", Purchase.class);
		
		List<Purchase> purchase = theQuery.getResultList();
		
		return purchase;
	}

	@Override
	public Purchase findById(int theId) {
		Purchase thePurchase = entityManager.find(Purchase.class, theId);
		
		return thePurchase;
	}

	@Override
	public Purchase save(Purchase thePurchase) {
		System.out.println("Before setting:-"+thePurchase.getId());
		
//		@SuppressWarnings("unused")
		Purchase dbPurchase = entityManager.merge(thePurchase);
		
		System.out.println("After setting:-"+thePurchase.getId());
		return dbPurchase;
		
	}

	@Override
	public void deleteById(int theId) {
		Query theQuery = entityManager.createQuery("delete from Purchase where id=:purchaseId");

		theQuery.setParameter("purchaseId", theId);

		theQuery.executeUpdate();
		
		System.out.println(theId + "Deleted Successfully.");
		
	}

}
