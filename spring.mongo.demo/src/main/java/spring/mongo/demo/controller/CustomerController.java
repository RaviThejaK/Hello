package spring.mongo.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



import spring.mongo.demo.entity.Customer;
import spring.mongo.demo.exception.CustomerException;
import spring.mongo.demo.service.CustomerService;

@RestController
@RequestMapping("api/customer")
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	private Logger log = LoggerFactory.getLogger(CustomerController.class);
	
	@PostMapping
	public ResponseEntity<Object> addCustomer(@RequestBody @Valid Customer customer) throws CustomerException
	{
		if(customer !=null)
		{
//			if(customer.getCustomerId()<=0)
//			{
//				throw new CustomerException("Enter Customer Details");
//			}
			if(!StringUtils.isNumeric(customer.getPhoneNum()))
			{
				log.error("Phone Number has to be numeric");
				throw new CustomerException("Phone Number has to be numeric");
			}
			String response = customerService.addCustomer(customer);
			
			log.info(response);
			return ResponseEntity.ok(customer);	
						
		
		}
		else
		{
			log.error("Enter Customer Details");
			throw new CustomerException("Enter Customer Details");
		}

			
	}
	
	@GetMapping("/id/{cid}")
	public ResponseEntity<Object> getCustomer(@PathVariable Integer cid)
	{
		Customer customer = customerService.getCustomerById(cid);
		if(customer!=null)
		{
		log.info("Customer Details Obtained");
		return ResponseEntity.ok(customer);
		}
		else
		{
			log.error("No such Customer available");
			return ResponseEntity.ok("No such Customer available");
		}
	}
	
	@PutMapping("/{cid}")
	public ResponseEntity<Object> updateCustomer(@PathVariable Integer cid,@RequestBody Customer customer) throws CustomerException
	{
		if(customer !=null)
		{
		if(customer.getCustomerId() !=null)
		{
			throw new CustomerException("Dont' have accesss to change Customer Id");
		}
		if(customer.getEmailId()!=null || customer.getPhoneNum() !=null)
		{
			throw new CustomerException("Can't change Email Id or Phone Number");
		}
		Customer customerResponse = customerService.updateCustomerById(cid,customer);
		log.info("Customer Details Changed");
		return ResponseEntity.ok(customerResponse);
		}
		else
			throw new CustomerException("Enter Customer Details You wanted to modify");
	}
	
	@DeleteMapping("/{cid}")
	public ResponseEntity<String> deleteCustomer(@PathVariable Integer cid) throws CustomerException
	{
		String response = customerService.deleteCustomerById(cid);
		if(response != null)
		{
		log.info("Customer Details Deleted");
		return ResponseEntity.ok(response);
		}
		else
			throw new CustomerException("No such Customer to delete");
	}
	
	@GetMapping
	public ResponseEntity<Object> getCustomer()
	{
		List<Customer> customerList = customerService.getAllCustomers();
		log.info("All Customer Details Displayed in Json");
		return ResponseEntity.ok(customerList);
	}
}










