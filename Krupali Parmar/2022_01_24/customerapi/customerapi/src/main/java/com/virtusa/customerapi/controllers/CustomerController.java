package com.virtusa.customerapi.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bohnman.squiggly.Squiggly;
import com.github.bohnman.squiggly.util.SquigglyUtils;
import com.virtusa.customerapi.models.Customer;
import com.virtusa.customerapi.services.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	//post
	@PostMapping(value="/{bankId}",params = "version=1.0")
	public ResponseEntity<?> addCustomer(@PathVariable("bankId") long bankId,@RequestBody Customer customer){
		Customer customerObj=this.customerService.addCustomer(bankId, customer);
		if(customerObj!=null)
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(customerObj);
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer Not Created");
		
	}
	
	//get
	@GetMapping(value="/",params = "version=1.0")
	public List<Customer> getAllCustomer(){
		return this.customerService.getAllCustomers();
	}
	
	
	@PutMapping(value="/{bankId}",params = "version=1.0")
	public List<Customer> updateCustomerWithFKNull(@PathVariable("bankId") long bankId){
		
		return this.customerService.updateBank(bankId);
		
	}
	
	//get with id
	@GetMapping(value="/{customerId}",params = "version=1.0")
	public ResponseEntity<?> getCustomerById(@PathVariable("customerId") long customerId){
		Customer customerObj=this.customerService.getCustomerById(customerId);
		if(customerObj!=null)
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(customerObj);
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer Not Found");
		
	}
	
	//put
	@PutMapping(value="/",params = "version=1.0")
	public ResponseEntity<?> updateCustomer(@RequestBody Customer customer){

		Customer customerObj=this.customerService.updateCustomer(customer);
		if(customerObj!=null)
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(customerObj);
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer Not Updated");
		
	}
	
	//delete
	
	@DeleteMapping(value="/{customerId}",params = "version=1.0")
	public ResponseEntity<?> deleteCustomerById(@PathVariable("customerId") long customerId){
	
		if(this.customerService.deleteCustomerById(customerId))
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Customer with "+customerId+"Deleted");
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer Not Found");
		
	}


	//get
	@GetMapping(value="/filters/{customerId}",params = "version=1.0")
	public ResponseEntity<?> getCustomerByFields(@PathVariable("customerId") long customerId,
			@RequestParam(name = "fields", required = false) String fields){
		
		Map<Object,Object> model=new HashMap<>();
    	
    	Customer customer = this.customerService.getCustomerById(customerId);
    	
    	if(customer!=null)
    	{
    		//fields refers to runtime selection
    		ObjectMapper mapper = Squiggly.init(new ObjectMapper(), fields);  		
			return ResponseEntity.ok(SquigglyUtils.stringify(mapper, customer));

    	}
    	else
    	{
	         model.put("message", "customer not existing");
	         return ResponseEntity.ok(model);
    	}	
	}
}
