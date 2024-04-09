package com.ims.dao;

import java.util.List;

import com.ims.entity.Sell;

public interface SellDAO {

	public List<Sell> findAll();
	
	public Sell findById(int theId);
	
	public Sell save(Sell theSell);
	
	public void deleteById(int theId);
	
}
