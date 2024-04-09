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

import com.ims.service.PartiesService;
import com.ims.service.ProductService;

import jakarta.persistence.EntityNotFoundException;

import com.ims.dto.PartiesDTO;
import com.ims.dto.ProductDTO;
import com.ims.entity.Parties;
import com.ims.entity.Product;

@RestController
@RequestMapping("/ims")
public class ProductRestController {
	
	private ProductService productService;
	private PartiesService partiesService;
	
	@Autowired
	public ProductRestController(ProductService theProductService, PartiesService thePartiesService) {
		productService = theProductService;
		partiesService = thePartiesService;
	}
	
	@GetMapping("/products")
	public List<Product> findAll() {
		return productService.findAll();
	}
	
	@GetMapping("/products/{productId}")
	public Product getProduct(@PathVariable int productId) {
		
		Product theProduct = productService.findById(productId);
		
		if (theProduct == null) {
			throw new RuntimeException("Product id not found - " + productId);
		}
		
		return theProduct;
	}
	
	@PostMapping("/products")
	public Product addProduct(@RequestBody ProductDTO theProductDTO) {
		
		Product theProduct = productService.createProductWithExistingParty(theProductDTO);
		
		return theProduct;
	}
	
	@PutMapping("/products")
	public Product updateProduct(@RequestBody ProductDTO theProductDTO) {
		
		Product theProduct = productService.findById(theProductDTO.getId());
		
		theProduct.setName(theProductDTO.getName());
        theProduct.setDescription(theProductDTO.getDescription());

        // Update or Create Party if PartyDTO is provided
        if (theProductDTO.getParty() != null) {
            PartiesDTO partyDTO = theProductDTO.getParty();
            Parties party;

            // If partyId is provided, update existing Party
            if (partyDTO.getId()!= 0) {
                party = partiesService.findById(partyDTO.getId());
                if (party == null) {
                	throw new EntityNotFoundException("Party not found with id: " + partyDTO.getId());
                    
                }
            } else {
                // If partyId is null, create a new Party
                party = new Parties();
                
                party.setName(partyDTO.getName());
                party.setEmail(partyDTO.getEmail());
                party.setPhoneNo(partyDTO.getPhoneNo());

                // Save or Update Party
                party = partiesService.save(party);

                // Associate Party with Product
                theProduct.setParty(party);
                
                productService.save(theProduct);

            }

         }
		
		return theProduct;
	}
	
	@DeleteMapping("/products/{productId}")
	public String deleteProduct(@PathVariable int productId) {
		
		Product tempProduct = productService.findById(productId);
		
		if (tempProduct == null) {
			throw new RuntimeException("Product id not found - " + productId);
		}
		
		productService.deleteById(productId);
		
		return "Deleted product id - " + productId;
	}
	
}
