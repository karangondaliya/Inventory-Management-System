package com.ims.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ims.entity.Stock;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Repository
public class StockDAOJpaImpl implements StockDAO{

	private EntityManager entityManager;
	
	@Autowired
	public StockDAOJpaImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	public List<Stock> findAll() {
		TypedQuery<Stock> theQuery = entityManager.createQuery("from Stock", Stock.class);
		
		List<Stock> stock = theQuery.getResultList();
		
		return stock;
	}

	@Override
	public Stock findById(int theId) {
		Stock theStock = entityManager.find(Stock.class, theId);
		
		return theStock;
	}

	@Override
	public void save(Stock theStock) {
		System.out.println("Before setting:-"+theStock.getId());
		
		@SuppressWarnings("unused")
		Stock dbStock = entityManager.merge(theStock);
		
		System.out.println("After setting:-"+theStock.getId());
	}

	@Override
	public void deleteById(int theId) {
		Query theQuery = entityManager.createQuery("delete from Stock where id=:stockId");

		theQuery.setParameter("stockId", theId);

		theQuery.executeUpdate();
		
		System.out.println(theId + "Deleted Successfully.");
		
	}

	@Override
	public TypedQuery<Stock> findByProductId(int theId) {
		
		TypedQuery<Stock> query = entityManager.createQuery(
		        "SELECT s FROM Stock s WHERE s.product.id = :productId", Stock.class);
		    query.setParameter("productId", theId);
		    return query;
	}

}
