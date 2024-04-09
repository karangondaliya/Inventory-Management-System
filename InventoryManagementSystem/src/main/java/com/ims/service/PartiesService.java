package com.ims.service;

import java.util.List;

import com.ims.entity.Parties;

public interface PartiesService {

	public List<Parties> findAll();
	
	public Parties findById(int theId);
	
	public Parties save(Parties theParties);
	
	public void deleteById(int theId);
	
}
