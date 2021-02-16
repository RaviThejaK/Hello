package spring.mongo.demo.service.impl;

import java.util.List;
import java.util.Optional;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import spring.mongo.demo.entity.Customer;
import spring.mongo.demo.repository.CustomerRepository;
import spring.mongo.demo.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

	
	Integer customerId=0;
	public Integer generateCid()
	{
		int cid = (int) customerRepository.count();
		List<Customer> customers = customerRepository.findAll();
		//Customer customerPrev = (Customer) customers.subList(cid, cid);
		//int custId=customerPrev.getCustomerId();
		if(cid==0)
			return 1;
		
		Customer customerPrev = null;
		customerPrev = customers.get(cid-1);
		int custId =customerPrev.getCustomerId();
		return ++custId;
		
		
		
	}
	
	@Override
	public String addCustomer(Customer customer2) {
		//Customer customer = new Customer(customer2);
		//		customerRepository.indexOps(Customer.class).
		//		  ensureIndex(new Index().on("name", Direction.ASC));

		Customer customer = new Customer();
		customer = customer2;
		
		
		if(customer != null)
		{
		customerId=generateCid();
		customer.setCustomerId(customerId);
		String password = customer.getPassword();
		String passwordEncr = bCryptPasswordEncoder.encode(password);
		customer.setPassword(passwordEncr);
		customerRepository.save(customer);
		
		
		return "Customer added Successfully";
		}
		return null;
	}

	@Override
	public Customer getCustomerById(Integer cid) {
		Optional<Customer> customer = customerRepository.findById(cid);
		
		if(customer.isPresent())
		{
			return customer.get();
		}
			
		return null;
	}

	@Override
	public Customer updateCustomerById(Integer cid, Customer customer) {
		Optional<Customer> customerRepo = customerRepository.findById(cid);
		
		if(customerRepo.isPresent())
		{
			Customer customerOld = customerRepo.get();
			customerOld.setFirstName(customer.getFirstName() != null?customer.getFirstName(): customerOld.getFirstName());
			customerOld.setLastName(customer.getLastName() != null?customer.getLastName(): customerOld.getLastName());
			customerOld.setPassword(customer.getPassword() != null?customer.getPassword(): customerOld.getPassword());
			customerOld.setAddress(customer.getAddress() != null?customer.getAddress(): customerOld.getAddress());
			
			String password = customer.getPassword();
			String passwordEncr = bCryptPasswordEncoder.encode(password);
			customer.setPassword(passwordEncr);
			
			customerRepository.save(customerOld);
			return customerOld;
		}
		return null;
	}

	@Override
	public String deleteCustomerById(Integer cid) {
		Optional<Customer> customerRepo = customerRepository.findById(cid);
		
		if(customerRepo.isPresent())
		{
			customerRepository.deleteById(cid);
			return "Customer deleted Successfully";
		}
		return null;
	}

	@Override
	public List<Customer> getAllCustomers() {
		
		return customerRepository.findAll();
	}

}
