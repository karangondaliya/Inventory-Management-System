package com.ims.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ims.entity.Sell;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Repository
public class SellDAOJpaImpl implements SellDAO{

	private EntityManager entityManager;
	
	@Autowired
	public SellDAOJpaImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	public List<Sell> findAll() {
		TypedQuery<Sell> theQuery = entityManager.createQuery("from Sell", Sell.class);
		
		List<Sell> sell = theQuery.getResultList();
		
		return sell;
	}

	@Override
	public Sell findById(int theId) {
		Sell theSell = entityManager.find(Sell.class, theId);
		
		return theSell;
	}

	@Override
	public Sell save(Sell theSell) {
		System.out.println("Before setting:-"+theSell.getId());
		
		@SuppressWarnings("unused")
		Sell dbSell = entityManager.merge(theSell);
		
		System.out.println("After setting:-"+theSell.getId());
		return dbSell;
		
	}

	@Override
	public void deleteById(int theId) {
		Query theQuery = entityManager.createQuery("delete from Sell where id=:sellId");

		theQuery.setParameter("sellId", theId);

		theQuery.executeUpdate();
		
		System.out.println(theId + "Deleted Successfully.");
		
	}

}
