package spring.mongo.demo.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import spring.mongo.demo.entity.Customer;
import spring.mongo.demo.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

	@InjectMocks
	CustomerServiceImpl customerService;
	
	@Mock
	CustomerRepository customerRepository;
	
	@Mock
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Nested
	class GenerateCidTest
	{
		@Test
		void addCustomerTest_Success()
		{
			Customer customer= new Customer();
			customer.setCustomerId(1);
			customer.setFirstName("ravi");
			customer.setLastName("theja");
			customer.setAddress("Hyd");
			customer.setEmailId("ravi@gmail.com");
			customer.setPassword("pass");
			customer.setPhoneNum("8888555565");
			int cid=2;
			List<Customer> customers = new ArrayList<Customer>();;
			customers.add(customer);
			
			Customer customerPrev = null;
			Mockito.lenient().when(customerRepository.count()).thenReturn((long) 1);
			Mockito.lenient().when(customerRepository.findAll()).thenReturn(customers);
			//Mockito.lenient().when(customers.get(Mockito.anyInt())).thenReturn(customer);
			
			Assertions.assertDoesNotThrow(()->customerService.generateCid());
		}

	
	}
	
	@Nested
	class AddCustomerTest{
		
		@Test
		void addCustomerTest_Success()
		{
			Customer customer= new Customer();
			customer.setCustomerId(1);
			customer.setFirstName("ravi");
			customer.setLastName("theja");
			customer.setAddress("Hyd");
			customer.setEmailId("ravi@gmail.com");
			customer.setPassword("pass");
			customer.setPhoneNum("8888555565");
			
		//	Mockito.doNothing().when(customerRepository).save(Mockito.anyObject());
			
			Mockito.lenient().when(bCryptPasswordEncoder.encode(Mockito.anyString())).thenReturn("");
			
			String response = customerService.addCustomer(customer);
			
			Assertions.assertEquals("Customer added Successfully", response);
			Assertions.assertNotNull(response);
			
		}
		
		@Test
		void addCustomerTest_Failed()
		{
			Customer customer= null;
			String response = customerService.addCustomer(null);
			Assertions.assertNull(response);
			Assertions.assertDoesNotThrow(()->customerService.addCustomer(null));
		}
	}
	
	
	@Nested
	class GetCustomerByIdTest{
		
		@Test
		void testGetCustomerById_Success()
		{
			Customer customer= new Customer();
			customer.setCustomerId(1);
			customer.setFirstName("ravi");
			customer.setLastName("theja");
			customer.setAddress("Hyd");
			customer.setEmailId("ravi@gmail.com");
			customer.setPassword("pass");
			customer.setPhoneNum("8888555565");
			Optional<Customer> customer2 = Optional.of(customer);
			
			Mockito.when(customerRepository.findById(Mockito.anyInt())).thenReturn(customer2);
			
			Customer response = customerService.getCustomerById(1);
			Assertions.assertNotNull(response);
		}
		
		@Test
		void testGetCustomerById_Failed()
		{
			
		//	Mockito.when(customerRepository.findById(Mockito.anyInt())).thenReturn(null);
			
			Customer response = customerService.getCustomerById(10);
			Assertions.assertNull(response);
		}
	}
	
	@Nested 
	class UpdateCustomerByIdTest
	{
		@Test
		void testUpdateCustomerById_Success()
		{
			Customer customer= new Customer();
			customer.setCustomerId(1);
			customer.setFirstName("ravi");
			customer.setLastName("theja");
			customer.setAddress("Hyd");
			customer.setEmailId("ravi@gmail.com");
			customer.setPassword("pass");
			customer.setPhoneNum("8888555565");
			Optional<Customer> customer2 = Optional.of(customer);
			
			Customer customer3= new Customer();
			customer3.setFirstName("ravi");
			customer3.setLastName("theja");
			customer3.setAddress("Hyd");
			customer3.setPassword("pass");
			
			
			
			Mockito.when(customerRepository.findById(Mockito.anyInt())).thenReturn(customer2);
			Customer response = customerService.updateCustomerById(1, customer3);
			Assertions.assertNotNull(customer);
			
		}

		@Test
		void testUpdateCustomerById_Success2()
		{
			Customer customer= new Customer();
			customer.setCustomerId(1);
			customer.setFirstName("ravi");
			customer.setLastName("theja");
			customer.setAddress("Hyd");
			customer.setEmailId("ravi@gmail.com");
			customer.setPassword("pass");
			customer.setPhoneNum("8888555565");
			Optional<Customer> customer2 = Optional.of(customer);
			
			Customer customer3= new Customer();
			customer3.setFirstName(null);
			customer3.setLastName(null);
			customer3.setAddress(null);
			customer3.setPassword(null);
			
			
			
			Mockito.when(customerRepository.findById(Mockito.anyInt())).thenReturn(customer2);
			Customer response = customerService.updateCustomerById(1, customer3);
			Assertions.assertNotNull(response);
			
		}
		
		@Test
		void testUpdateCustomerById_Failed()
		{
			Customer response = customerService.updateCustomerById(1, null);
			Assertions.assertNull(response);
			
		}
		
	}
	
	@Nested
	class DeleteCustomerByIdTest{
		
		@Test
		void testDeleteCustomerById_Success()
		{
			Customer customer= new Customer();
			customer.setCustomerId(1);
			customer.setFirstName("ravi");
			customer.setLastName("theja");
			customer.setAddress("Hyd");
			customer.setEmailId("ravi@gmail.com");
			customer.setPassword("pass");
			customer.setPhoneNum("8888555565");
			Optional<Customer> customer2 = Optional.of(customer);
			
			Mockito.when(customerRepository.findById(Mockito.anyInt())).thenReturn(customer2);
			
			String response = customerService.deleteCustomerById(1);
			Assertions.assertEquals("Customer deleted Successfully", response);
			Assertions.assertNotNull(response);
		}
		
		@Test
		void testDeleteCustomerById_Failed()
		{
			
			String response = customerService.deleteCustomerById(10);
			Assertions.assertNull(response);
		}
	}
	
	@Nested
	class GetAllCustomersTest
	{
		
		@Test
		void testGetAllCustomers()
		{
			//Mockito.when(customerRepository.findAll());
			Assertions.assertDoesNotThrow(()->customerService.getAllCustomers());
			
		}
	}
	
}
