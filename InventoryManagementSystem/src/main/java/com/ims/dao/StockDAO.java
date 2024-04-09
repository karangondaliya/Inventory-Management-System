package com.ims.dao;

import java.util.List;

import com.ims.entity.Stock;

import jakarta.persistence.TypedQuery;

public interface StockDAO {

	public List<Stock> findAll();
	
	public Stock findById(int theId);
	
	public void save(Stock theStock);
	
	public void deleteById(int theId);

	public TypedQuery<Stock> findByProductId(int theId);
	
}
