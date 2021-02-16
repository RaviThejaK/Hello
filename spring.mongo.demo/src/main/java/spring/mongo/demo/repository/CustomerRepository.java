package spring.mongo.demo.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import spring.mongo.demo.entity.Customer;

public interface CustomerRepository extends MongoRepository<Customer,Integer>{
	
	

}
