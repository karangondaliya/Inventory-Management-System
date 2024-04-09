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

import com.ims.dto.PartiesDTO;
import com.ims.dto.ProductDTO;
import com.ims.dto.PurchaseDTO;
import com.ims.entity.Parties;
import com.ims.entity.Product;
import com.ims.entity.Purchase;
import com.ims.service.PartiesService;
import com.ims.service.ProductService;
import com.ims.service.PurchaseService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/ims")
public class PurchaseRestController {

	private PurchaseService purchaseService;
	private ProductService productService;
	private PartiesService partiesService;
	
	@Autowired
	public PurchaseRestController(PurchaseService thePurchaseService,ProductService theProductService, PartiesService thePartiesService) {
		purchaseService = thePurchaseService;
		productService = theProductService;
		partiesService = thePartiesService;
	}
	
	@GetMapping("/purchases")
	public List<Purchase> findAll() {
		return purchaseService.findAll();
	}
	
	@GetMapping("/purchases/{purchaseId}")
	public Purchase getpurchase(@PathVariable int purchaseId) {
		
		Purchase thePurchase = purchaseService.findById(purchaseId);
		
		if (thePurchase == null) {
			throw new RuntimeException("purchase id not found - " + purchaseId);
		}
		
		return thePurchase;
	}
	
	@PostMapping("/purchases")
	public Purchase addpurchase(@RequestBody PurchaseDTO thePurchaseDTO) {
		
		Purchase thePurchase = purchaseService.createPurchaseWithExistingParty(thePurchaseDTO);
		
		return thePurchase;
	}
	
	@PutMapping("/purchases")
	public Purchase updatepurchase(@RequestBody PurchaseDTO thePurchaseDTO) {
		
		Purchase existingPurchase  = purchaseService.findById(thePurchaseDTO.getId());
		
		existingPurchase.setDiscount(thePurchaseDTO.getDiscount());
		existingPurchase.setPrice(thePurchaseDTO.getPrice());
		existingPurchase.setQuantity(thePurchaseDTO.getQuantity());
	    existingPurchase.setDate(thePurchaseDTO.getDate());
	    existingPurchase.setTotal((thePurchaseDTO.getPrice() * thePurchaseDTO.getQuantity()));

        // Update or Create Party if PartyDTO is provided
        if (thePurchaseDTO.getParty() != null) {
            PartiesDTO partyDTO = thePurchaseDTO.getParty();
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
            }
            
            party.setName(partyDTO.getName());
            party.setEmail(partyDTO.getEmail());
            party.setPhoneNo(partyDTO.getPhoneNo());
            
            party = partiesService.save(party);
            
            existingPurchase.setParty(party);
        }
        
        if (thePurchaseDTO.getProduct() != null) {
            ProductDTO productDTO = thePurchaseDTO.getProduct();
            Product product;

            // If partyId is provided, update existing Party
            if (productDTO.getId()!= 0) {
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
            
            product = productService.save(product);
            
            existingPurchase.setProduct(product);
        }
        
		purchaseService.save(existingPurchase);
		
		return existingPurchase;
	}
	
	@DeleteMapping("/purchases/{purchaseId}")
	public String deletepurchase(@PathVariable int purchaseId) {
		
		Purchase tempPurchase = purchaseService.findById(purchaseId);
		
		if (tempPurchase == null) {
			throw new RuntimeException("purchase id not found - " + purchaseId);
		}
		
		purchaseService.deleteById(purchaseId);
		
		return "Deleted purchase id - " + purchaseId;
	}
	
}
