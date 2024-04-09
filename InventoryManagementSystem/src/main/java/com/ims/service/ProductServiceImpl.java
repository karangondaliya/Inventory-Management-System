package com.ims.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ims.dao.PartiesDAO;
import com.ims.dao.ProductDAO;
import com.ims.dao.StockDAO;
import com.ims.dto.ProductDTO;
import com.ims.entity.Parties;
import com.ims.entity.Product;
import com.ims.entity.Stock;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductDAO productDAO;
	private PartiesDAO partiesDAO;
	private StockDAO stockDAO;
	
	@Autowired
	public ProductServiceImpl(@Qualifier("productDAOJpaImpl") ProductDAO theProductDAO, 
			@Qualifier("partiesDAOJpaImpl") PartiesDAO thePartiesDAO,
			@Qualifier("stockDAOJpaImpl") StockDAO theStockDAO) {
		productDAO = theProductDAO;
		partiesDAO = thePartiesDAO;
		stockDAO = theStockDAO;
	}

	@Override
	@Transactional
	public List<Product> findAll() {
		return productDAO.findAll();
	}

	@Override
	@Transactional
	public Product findById(int theId) {
		return productDAO.findById(theId);
	}

	@Override
	@Transactional
	public Product save(Product theProduct) {
		return productDAO.save(theProduct);
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		productDAO.deleteById(theId);
	}

	@Override
    @Transactional
    public Product createProductWithExistingParty(ProductDTO productDTO) {
        Product product = new Product();
        Stock stock = new Stock();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());

        // Retrieve Party from database using its ID
        Parties party = partiesDAO.findById(productDTO.getParty().getId());

        // Set the Party for the Product
        product.setParty(party);

        // Save the Product
        //Product temp = productDAO.save(product);
        
        stock.setProduct(product); 
        stock.setQuantity(0);
        stockDAO.save(stock);
        
        return product;
    }
	
}
