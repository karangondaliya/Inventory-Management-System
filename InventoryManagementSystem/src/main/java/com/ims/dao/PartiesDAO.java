package com.ims.dao;

import java.util.List;

import com.ims.entity.Parties;

public interface PartiesDAO {

	public List<Parties> findAll();
	
	public Parties findById(int theId);
	
	public void save(Parties theParties);
	
	public void deleteById(int theId);
	
}
