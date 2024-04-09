package com.ims.dao;

import java.util.List;

import com.ims.entity.Product;

public interface ProductDAO{
	
	public List<Product> findAll();
	
	public Product findById(int theId);
	
	public Product save(Product theProduct);
	
	public void deleteById(int theId);
	
}