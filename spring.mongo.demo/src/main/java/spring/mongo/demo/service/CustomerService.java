package spring.mongo.demo.service;

import java.util.List;

import spring.mongo.demo.entity.Customer;

public interface CustomerService {

	String addCustomer(Customer customer);

	Customer getCustomerById(Integer cid);

	Customer updateCustomerById(Integer cid, Customer customer);

	String deleteCustomerById(Integer cid);

	List<Customer> getAllCustomers();

}
