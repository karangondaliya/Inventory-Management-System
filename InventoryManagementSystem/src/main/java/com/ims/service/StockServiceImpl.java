package com.ims.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ims.dao.StockDAO;
import com.ims.entity.Stock;

import jakarta.persistence.TypedQuery;

@Service
public class StockServiceImpl implements StockService{

	private StockDAO stockDAO;
	
	@Autowired
	public StockServiceImpl(@Qualifier("stockDAOJpaImpl") StockDAO theStockDAO) {
		stockDAO = theStockDAO;
	}
	
	@Override
	@Transactional
	public List<Stock> findAll() {
		return stockDAO.findAll();
	}

	@Override
	@Transactional
	public Stock findById(int theId) {
		return stockDAO.findById(theId);
	}

	@Override
	@Transactional
	public void save(Stock theStock) {
		stockDAO.save(theStock);
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		stockDAO.deleteById(theId);;
	}

	@Override
	public TypedQuery<Stock> findByProductId(int theId) {
		return stockDAO.findByProductId(theId);
	}

}
