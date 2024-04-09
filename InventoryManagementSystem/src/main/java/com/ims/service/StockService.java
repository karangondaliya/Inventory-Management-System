package com.ims.service;

import java.util.List;

import com.ims.entity.Stock;

import jakarta.persistence.TypedQuery;

public interface StockService {

	public List<Stock> findAll();
	
	public Stock findById(int theId);
	
	public void save(Stock theStock);
	
	public void deleteById(int theId);
	
	TypedQuery<Stock> findByProductId(int theId);
}
