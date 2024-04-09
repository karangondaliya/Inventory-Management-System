package com.ims.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ims.entity.Stock;
import com.ims.service.StockService;

@RestController
@RequestMapping("/ims")
public class StockRestController {

	private StockService stockService;
	
	@Autowired
	public StockRestController(StockService theStockService) {
		stockService = theStockService;
	}

	@GetMapping("/stocks")
	public List<Stock> findAll() {
		return stockService.findAll();
	}
	
	@GetMapping("/stocks/{productId}")
	public Stock getstock(@PathVariable int productId) {
		
		Stock theStock = stockService.findById(productId);
		
		if (theStock == null) {
			throw new RuntimeException("stock id not found - " + productId);
		}
		
		return theStock;
	}
	
	@PostMapping("/stocks")
	public Stock addstock(@RequestBody Stock theStock) {
		
		theStock.setId(0);
		
		stockService.save(theStock);
		
		return theStock;
	}
	
	@PutMapping("/stocks")
	public Stock updatestock(@RequestBody Stock theStock) {
		
		stockService.save(theStock);
		
		return theStock;
	}
	
	@DeleteMapping("/stocks/{stockId}")
	public String deletestock(@PathVariable int stockId) {
		
		Stock tempStock = stockService.findById(stockId);
		
		if (tempStock == null) {
			throw new RuntimeException("stock id not found - " + stockId);
		}
		
		stockService.deleteById(stockId);
		
		return "Deleted stock id - " + stockId;
	}
	
}
