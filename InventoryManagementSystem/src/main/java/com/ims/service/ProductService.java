package com.ims.service;

import java.util.List;

import com.ims.dto.ProductDTO;
import com.ims.entity.Product;

public interface ProductService {

	public List<Product> findAll();
	
	public Product findById(int theId);
	
	public Product save(Product theProduct);
	
	public void deleteById(int theId);

	public Product createProductWithExistingParty(ProductDTO productDTO);
	
}
