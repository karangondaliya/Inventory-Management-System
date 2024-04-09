package com.ims.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ims.entity.Parties;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Repository
public class PartiesDAOJpaImpl implements PartiesDAO{

	private EntityManager entityManager;
	
	@Autowired
	public PartiesDAOJpaImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	public List<Parties> findAll() {
		TypedQuery<Parties> theQuery = entityManager.createQuery("from Parties", Parties.class);
		
		List<Parties> parties = theQuery.getResultList();
		
		return parties;
	}

	@Override
	public Parties findById(int theId) {
		Parties theParties = entityManager.find(Parties.class, theId);
		
		return theParties;
	}

	@Override
	public void save(Parties theParties) {
		System.out.println("Before setting:-"+theParties.getId());
		
		@SuppressWarnings("unused")
		Parties dbParties = entityManager.merge(theParties);
		
		System.out.println("After setting:-"+theParties.getId());
		
	}

	@Override
	public void deleteById(int theId) {
		Query theQuery = entityManager.createQuery("delete from Parties where id=:partiesId");

		theQuery.setParameter("partiesId", theId);

		theQuery.executeUpdate();
		
		System.out.println(theId + "Deleted Successfully.");
		
	}

}
