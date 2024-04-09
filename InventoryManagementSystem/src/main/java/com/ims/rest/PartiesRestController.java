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

import com.ims.entity.Parties;
import com.ims.service.PartiesService;

@RestController
@RequestMapping("/ims")
public class PartiesRestController {

	private PartiesService partiesService;
	
	@Autowired
	public PartiesRestController(PartiesService thePartiesService) {
		partiesService = thePartiesService;
	}
	
	@GetMapping("/parties")
	public List<Parties> findAll() {
		return partiesService.findAll();
	}
	
	@GetMapping("/parties/{partiesId}")
	public Parties getparties(@PathVariable int partiesId) {
		
		Parties theParties = partiesService.findById(partiesId);
		
		if (theParties == null) {
			throw new RuntimeException("parties id not found - " + partiesId);
		}
		
		return theParties;
	}
	
	@PostMapping("/parties")
	public Parties addparties(@RequestBody Parties theParties) {
		
		theParties.setId(0);
		
		partiesService.save(theParties);
		
		return theParties;
	}
	
	@PutMapping("/parties")
	public Parties updateparties(@RequestBody Parties theParties) {
		
		partiesService.save(theParties);
		
		return theParties;
	}
	
	@DeleteMapping("/parties/{partiesId}")
	public String deleteparties(@PathVariable int partiesId) {
		
		Parties tempParties = partiesService.findById(partiesId);
		
		if (tempParties == null) {
			throw new RuntimeException("parties id not found - " + partiesId);
		}
		
		partiesService.deleteById(partiesId);
		
		return "Deleted parties id - " + partiesId;
	}
}
