package com.ims.service;

import java.util.List;

import com.ims.dto.SellDTO;
import com.ims.entity.Sell;

public interface SellService {

	public List<Sell> findAll();
	
	public Sell findById(int theId);
	
	public void save(Sell theSell);
	
	public void deleteById(int theId);

	Sell createSellWithExistingParty(SellDTO sellDTO);
	
}
