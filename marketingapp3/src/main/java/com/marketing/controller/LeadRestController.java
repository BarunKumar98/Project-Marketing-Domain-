package com.marketing.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marketing.entities.Lead;
import com.marketing.repositories.LeadRepository;

@RestController
@RequestMapping("/api/leads")//calls the method getAllLeads() when we put url:localhost:8080/api/leads in browser
public class LeadRestController {
	@Autowired
private LeadRepository leadRepo;
	@GetMapping //it will go to the DB get the content and convert java object in to jason object
	public List<Lead>getAllLeads(){
	List<Lead> leads=leadRepo.findAll();
	return leads;
}
	  
	//RequestBody will copy jason object and it put that into the Lead(java) object
	@PostMapping  //use to store the data in db
	public void SaveOneLead(@RequestBody Lead lead) {
		System.out.println(lead.getId());
		System.out.println(lead.getFirstName());
		//Saving the java object
		leadRepo.save(lead);
	}
	
	//update the record
	@PutMapping
	public void UpdateSOneLead(@RequestBody Lead lead) {
		//Updating by saving updates the java object
		leadRepo.save(lead);
	}
	//Delete the record
	//http://localhost:8080/api/leads/delete/14
	@DeleteMapping("/delete/{id}")
	public void deleteOneRecord(@PathVariable ("id") long id) {
		leadRepo.deleteById(id);
		
	}
	//Exposing web services
	//http://localhost:8080/api/leads/leadinfo/5
	@RequestMapping("/leadinfo/{id}")
	public Lead getOneLead(@PathVariable("id") long id) {//path variable is used for read the id from url
		Optional<Lead> findById = leadRepo.findById(id);
		Lead lead=findById.get();
		return lead;
	}
}