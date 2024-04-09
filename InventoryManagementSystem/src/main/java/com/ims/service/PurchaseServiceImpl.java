package com.ims.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ims.dao.PartiesDAO;
import com.ims.dao.ProductDAO;
import com.ims.dao.PurchaseDAO;
import com.ims.dao.StockDAO;
import com.ims.dto.PurchaseDTO;
import com.ims.entity.Parties;
import com.ims.entity.Product;
import com.ims.entity.Purchase;
import com.ims.entity.Stock;

@Service
public class PurchaseServiceImpl implements PurchaseService{

	private PurchaseDAO purchaseDAO;
	private ProductDAO productDAO;
	private PartiesDAO partiesDAO;
	private StockDAO stockDAO;
	
	@Autowired
	public PurchaseServiceImpl(@Qualifier("purchaseDAOJpaImpl") PurchaseDAO thePurchaseDAO,
			@Qualifier("productDAOJpaImpl") ProductDAO theProductDAO, 
			@Qualifier("partiesDAOJpaImpl") PartiesDAO thePartiesDAO, 
			@Qualifier("stockDAOJpaImpl") StockDAO theStockDAO) {
		purchaseDAO = thePurchaseDAO;
		productDAO = theProductDAO;
		partiesDAO = thePartiesDAO;
		stockDAO = theStockDAO;
	}
	
	@Override
	@Transactional
	public List<Purchase> findAll() {
		return purchaseDAO.findAll();
	}

	@Override
	@Transactional
	public Purchase findById(int theId) {
		return purchaseDAO.findById(theId);
	}

	@Override
	@Transactional
	public void save(Purchase thePurchase) {
		purchaseDAO.save(thePurchase);
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
	    // Find the purchase
	    Purchase thePurchase = purchaseDAO.findById(theId);
	    if (thePurchase == null) {
	        throw new RuntimeException("Purchase id not found - " + theId);
	    }

	    // Assuming each Purchase has a Product, and each Product has a corresponding Stock
	    Product product = thePurchase.getProduct();
	    if (product != null) {
	        // Find the stock for this product
	        Stock stock = stockDAO.findById(product.getId());
	        if (stock != null) {
	            // Subtract the purchase quantity from the stock quantity
	            long updatedQuantity = stock.getQuantity() - thePurchase.getQuantity();
	            // Update the stock quantity (ensure that it does not go below zero)
	            stock.setQuantity(Math.max(updatedQuantity, 0));
	            stockDAO.save(stock);
	        } else {
	            throw new RuntimeException("Stock not found for product id - " + product.getId());
	        }
	    } else {
	        throw new RuntimeException("Product not found for purchase id - " + theId);
	    }

	    // Now, delete the purchase
	    purchaseDAO.deleteById(theId);
	}


	@Override
    @Transactional
    public Purchase createPurchaseWithExistingParty(PurchaseDTO purchaseDTO) {
		Purchase purchase = new Purchase();
		purchase.setPrice(purchaseDTO.getPrice());
		purchase.setQuantity(purchaseDTO.getQuantity());
		purchase.setDate(purchaseDTO.getDate());
		purchase.setDiscount(purchaseDTO.getDiscount());
		int discount = (int)(((purchaseDTO.getPrice() * purchaseDTO.getQuantity())*purchaseDTO.getDiscount())/100);
		purchase.setTotal(((purchaseDTO.getPrice() * purchaseDTO.getQuantity()) - discount));

        // Retrieve Party from database using its ID
        Parties party = partiesDAO.findById(purchaseDTO.getParty().getId());
        
        Product product = productDAO.findById(purchaseDTO.getProduct().getId());
        
        Stock stock = stockDAO.findById(purchaseDTO.getProduct().getId());
        stock.setQuantity((stock.getQuantity()+purchaseDTO.getQuantity()));
        stockDAO.save(stock);
        
        // Set the Party for the Product
        purchase.setParty(party);
        purchase.setProduct(product);

        // Save the Product
        return purchaseDAO.save(purchase);
    }

}
