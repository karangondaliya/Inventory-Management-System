package com.ims.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ims.dao.PartiesDAO;
import com.ims.entity.Parties;

@Service
public class PartiesServiceImpl implements PartiesService{

	private PartiesDAO partiesDAO;
	
	@Autowired
	public PartiesServiceImpl(@Qualifier("partiesDAOJpaImpl") PartiesDAO thePartiesDAO) {
		partiesDAO = thePartiesDAO;
	}
	
	@Override
	@Transactional
	public List<Parties> findAll() {
		return partiesDAO.findAll();
	}

	@Override
	@Transactional
	public Parties findById(int theId) {
		return partiesDAO.findById(theId);
	}

	@Override
	@Transactional
	public Parties save(Parties theParties) {
		partiesDAO.save(theParties);
		return theParties;
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		partiesDAO.deleteById(theId);;	
	}

}
