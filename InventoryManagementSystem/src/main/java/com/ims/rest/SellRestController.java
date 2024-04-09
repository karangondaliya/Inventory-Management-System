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

import com.ims.dto.ProductDTO;
import com.ims.dto.SellDTO;
import com.ims.entity.Product;
import com.ims.entity.Sell;
import com.ims.service.ProductService;
import com.ims.service.SellService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/ims")
public class SellRestController {

	private SellService sellService;
	private ProductService productService;
	
	@Autowired
	public SellRestController(SellService theSellService,ProductService theProductService) {
		sellService = theSellService;
		productService = theProductService;
	}
	
	@GetMapping("/sells")
	public List<Sell> findAll() {
		return sellService.findAll();
	}
	
	@GetMapping("/sells/{sellId}")
	public Sell getsell(@PathVariable int sellId) {
		
		Sell theSell = sellService.findById(sellId);
		
		if (theSell == null) {
			throw new RuntimeException("sell id not found - " + sellId);
		}
		
		return theSell;
	}
	
	@PostMapping("/sells")
	public Sell addsell(@RequestBody SellDTO theSellDTO) {
		
		Sell theSell = sellService.createSellWithExistingParty(theSellDTO);
		
		return theSell;
	}
	
	@PutMapping("/sells")
	public Sell updatesell(@RequestBody SellDTO theSellDTO) {
		
		Sell existingSell = sellService.findById(theSellDTO.getId());

		existingSell.setName(theSellDTO.getName());
		existingSell.setPrice(theSellDTO.getPrice());
		existingSell.setQuantity(theSellDTO.getQuantity());
		existingSell.setDiscount(theSellDTO.getDiscount());
		existingSell.setDate(theSellDTO.getDate());
		existingSell.setPhoneNo(theSellDTO.getPhoneNo());
		existingSell.setTotal(theSellDTO.getTotal());

		// Update or Create Party if PartyDTO is provided
		if (theSellDTO.getProduct() != null) {
			ProductDTO productDTO = theSellDTO.getProduct();
			Product product;

			// If partyId is provided, update existing Party
			if (productDTO.getId() != 0) {
				product = productService.findById(productDTO.getId());
				if (product == null) {
					throw new EntityNotFoundException("Party not found with id: " + productDTO.getId());
				}
			} else {
				// If partyId is null, create a new Party
				product = new Product();
			}

			product.setName(productDTO.getName());
			product.setDescription(productDTO.getDescription());

			sellService.save(existingSell);		
		}
		
		
		return existingSell;
	}
	
	@DeleteMapping("/sells/{sellId}")
	public String deletesell(@PathVariable int sellId) {
		
		Sell tempSell = sellService.findById(sellId);
		
		if (tempSell == null) {
			throw new RuntimeException("sell id not found - " + sellId);
		}
		
		sellService.deleteById(sellId);
		
		return "Deleted sell id - " + sellId;
	}
	
}