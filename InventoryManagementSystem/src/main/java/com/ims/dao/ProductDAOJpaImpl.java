package com.ims.dao;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ims.entity.Product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.Query;

@Repository
public class ProductDAOJpaImpl implements ProductDAO{

	private EntityManager entityManager;
	
	@Autowired
	public ProductDAOJpaImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	public List<Product> findAll() {
		TypedQuery<Product> theQuery = entityManager.createQuery("from Product", Product.class);
		
		List<Product> product = theQuery.getResultList();
		
		return product;
	}

	@Override
	public Product findById(int theId) {
		Product theProduct = entityManager.find(Product.class, theId);
		
		return theProduct;
	}

	@Override
	public Product save(Product theProduct) {
		System.out.println("Before setting:-"+theProduct.getId());
		
		Product dbProduct = entityManager.merge(theProduct);
		
		System.out.println("After setting:-"+theProduct.getId());
		
		return dbProduct;
	}

	@Override
	public void deleteById(int theId) {
		Query theQuery = entityManager.createQuery("delete from Product where id=:productId");

		theQuery.setParameter("productId", theId);

		theQuery.executeUpdate();
		
		System.out.println(theId + "Deleted Successfully.");
	}
	
	
}
