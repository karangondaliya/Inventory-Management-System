package com.ims.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ims.dao.ProductDAO;
import com.ims.dao.SellDAO;
import com.ims.dao.StockDAO;
import com.ims.dto.SellDTO;
import com.ims.entity.Product;
import com.ims.entity.Sell;
import com.ims.entity.Stock;

@Service
public class SellServiceImpl implements SellService{

	private SellDAO sellDAO;
	private ProductDAO productDAO;
	private StockDAO stockDAO;
	
	@Autowired
	public SellServiceImpl(@Qualifier("sellDAOJpaImpl") SellDAO theSellDAO,
			@Qualifier("productDAOJpaImpl") ProductDAO theProductDAO,  
			@Qualifier("stockDAOJpaImpl") StockDAO theStockDAO) {
		sellDAO = theSellDAO;
		productDAO = theProductDAO;
		stockDAO = theStockDAO;
	}
	
	@Override
	@Transactional
	public List<Sell> findAll() {
		return sellDAO.findAll();
	}

	@Override
	@Transactional
	public Sell findById(int theId) {
		return sellDAO.findById(theId);
	}

	@Override
	@Transactional
	public void save(Sell theSell) {
		sellDAO.save(theSell);
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		
		// Find the purchase
	    Sell theSell= sellDAO.findById(theId);
	    if (theSell == null) {
	        throw new RuntimeException("Sell id not found - " + theId);
	    }

	    // Assuming each Purchase has a Product, and each Product has a corresponding Stock
	    Product product = theSell.getProduct();
	    if (product != null) {
	        // Find the stock for this product
	        Stock stock = stockDAO.findById(product.getId());
	        if (stock != null) {
	            // Subtract the purchase quantity from the stock quantity
	            long updatedQuantity = stock.getQuantity() + theSell.getQuantity();
	            // Update the stock quantity (ensure that it does not go below zero)
	            stock.setQuantity(Math.max(updatedQuantity, 0));
	            stockDAO.save(stock);
	        } else {
	            throw new RuntimeException("Stock not found for product id - " + product.getId());
	        }
	    } else {
	        throw new RuntimeException("Product not found for Sell id - " + theId);
	    }
		
		sellDAO.deleteById(theId);
	}

	@Override
    @Transactional
    public Sell createSellWithExistingParty(SellDTO sellDTO) {
		Sell sell = new Sell();
		sell.setName(sellDTO.getName());
		sell.setPrice(sellDTO.getPrice());
		sell.setQuantity(sellDTO.getQuantity());
		sell.setDiscount(sellDTO.getDiscount());
		sell.setDate(sellDTO.getDate());
		sell.setPhoneNo(sellDTO.getPhoneNo());
		int discount = (int)(((sellDTO.getPrice() * sellDTO.getQuantity())*sellDTO.getDiscount())/100);
		sell.setTotal(((sellDTO.getPrice() * sellDTO.getQuantity()) - discount));

        // Retrieve Product from database using its ID
        
		Product product = productDAO.findById(sellDTO.getProduct().getId());
        Stock stock = stockDAO.findById(sellDTO.getProduct().getId());
        
        int availableStock = (int) stock.getQuantity();
        int requestedQuantity = sellDTO.getQuantity();
        
        if (requestedQuantity > availableStock) {
            throw new IllegalArgumentException("Requested quantity exceeds available stock for the product");
        }
        else {
        	stock.setQuantity(availableStock - requestedQuantity);
        	stockDAO.save(stock);
        }
        // Set the Party for the Product
       
        sell.setProduct(product);

        // Save the Product
        return sellDAO.save(sell);
    }

}