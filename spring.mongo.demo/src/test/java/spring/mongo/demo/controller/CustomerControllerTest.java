package spring.mongo.demo.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import spring.mongo.demo.entity.Customer;
import spring.mongo.demo.exception.CustomerException;
import spring.mongo.demo.service.CustomerService;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

	@InjectMocks
	CustomerController customerController;
	
	@Mock
	CustomerService customerService; 
	
	
	
	@Nested
	class AddCustomerTest{
	
		@Test
		void testAddCustomer_Success() throws CustomerException{
			Customer customer= new Customer();
			customer.setCustomerId(1);
			customer.setFirstName("ravi");
			customer.setLastName("theja");
			customer.setAddress("Hyd");
			customer.setEmailId("ravi@gmail.com");
			customer.setPassword("pass");
			customer.setPhoneNum("8888555565");
			
			Mockito.when(customerService.addCustomer(Mockito.anyObject())).thenReturn("Customer added Successfully");
			String response2 = customerService.addCustomer(customer);
			ResponseEntity<Object> response = customerController.addCustomer(customer);
					
			Assertions.assertEquals("Customer added Successfully",response2);
			Assertions.assertNotNull(response);
			
		}
		
//		@Test
//		void testAddCustomer_IdZero() throws CustomerException{
//			Customer customer= new Customer();
//			customer.setCustomerId(0);
//			customer.setFirstName("ravi");
//			customer.setLastName("theja");
//			customer.setAddress("Hyd");
//			customer.setEmailId("ravi@gmail.com");
//			customer.setPassword("pass");
//			customer.setPhoneNum("8888555565");
//					
//			Assertions.assertThrows(CustomerException.class,()-> customerController.addCustomer(customer));
//		}
		
		@Test
		void testAddCustomer_PhoneNum() throws CustomerException{
			Customer customer= new Customer();
			customer.setCustomerId(1);
			customer.setFirstName("ravi");
			customer.setLastName("theja");
			customer.setAddress("Hyd");
			customer.setEmailId("ravi@gmail.com");
			customer.setPassword("pass");
			customer.setPhoneNum("88885555xy");
					
			Assertions.assertThrows(CustomerException.class,()-> customerController.addCustomer(customer));
		}
		
		@Test
		void testAddCustomer_Null() throws CustomerException{
					
			Assertions.assertThrows(CustomerException.class,()-> customerController.addCustomer(null));
		}
	}




@Nested
class GetCustomerTest{

	@Test
	void testGetCustomer_Success() throws CustomerException{
		Customer customer= new Customer();
		customer.setCustomerId(1);
		customer.setFirstName("ravi");
		customer.setLastName("theja");
		customer.setAddress("Hyd");
		customer.setEmailId("ravi@gmail.com");
		customer.setPassword("pass");
		customer.setPhoneNum("8888555565");
		
		Mockito.when(customerService.getCustomerById(Mockito.anyInt())).thenReturn(customer);
		Customer response2 = customerService.getCustomerById(1);
		ResponseEntity<Object> response = customerController.getCustomer(1);
				
		Assertions.assertNotNull(response2);
		Assertions.assertNotNull(response);
		Assertions.assertDoesNotThrow(()->customerController.getCustomer(1));
		
	}
	
	@Test
	void testAddCustomer_Null() throws CustomerException{
		Mockito.when(customerService.getCustomerById(Mockito.anyInt())).thenReturn(null);
		Customer response2 = customerService.getCustomerById(10);
		ResponseEntity<Object> response = customerController.getCustomer(10);
				
		Assertions.assertNull(response2);
		Assertions.assertNotNull(response);
		Assertions.assertThrows(CustomerException.class,()-> customerController.addCustomer(null));
	}
}


@Nested
class UpdateCustomerTest{

	@Test
	void testUpdateCustomer_Success() throws CustomerException{
		Customer customer= new Customer();
		customer.setFirstName("ravi");
		customer.setLastName("theja");
		customer.setAddress("Hyd");
		customer.setPassword("pass");
		
		Mockito.when(customerService.updateCustomerById(Mockito.anyInt(),Mockito.anyObject())).thenReturn(customer);
		Customer response2 = customerService.updateCustomerById(1,customer);
		ResponseEntity<Object> response = customerController.updateCustomer(1,customer);
				
		Assertions.assertNotNull(response);
		
	}
	
	@Test
	void testUpdateCustomer_Id() throws CustomerException{
		Customer customer= new Customer();
		customer.setCustomerId(1);
		customer.setFirstName("ravi");
		customer.setLastName("theja");
		customer.setAddress("Hyd");
		customer.setPassword("pass");
				
		Assertions.assertThrows(CustomerException.class,()-> customerController.updateCustomer(1,customer));
	}
	
	@Test
	void testUpdateCustomer_Email() throws CustomerException{
		Customer customer= new Customer();
		customer.setFirstName("ravi");
		customer.setLastName("theja");
		customer.setAddress("Hyd");
		customer.setEmailId("ravi@gmail.com");
		customer.setPassword("pass");
				
		Assertions.assertThrows(CustomerException.class,()-> customerController.updateCustomer(1,customer));
	}
	
	@Test
	void testUpdateCustomer_PhoneNum() throws CustomerException{
		Customer customer= new Customer();
		customer.setFirstName("ravi");
		customer.setLastName("theja");
		customer.setAddress("Hyd");
		customer.setPhoneNum("8888555565");
		customer.setPassword("pass");
				
		Assertions.assertThrows(CustomerException.class,()-> customerController.updateCustomer(1,customer));
	}
	
	
	@Test
	void testUpdateCustomer_Null() throws CustomerException{
				
		Assertions.assertThrows(CustomerException.class,()-> customerController.updateCustomer(1,null));
	}
}


@Nested
class DeleteCustomerTest
{
	@Test
	void testDeleteCustomer_Success() {
		Customer customer= new Customer();
		customer.setCustomerId(1);
		customer.setFirstName("ravi");
		customer.setLastName("theja");
		customer.setAddress("Hyd");
		customer.setEmailId("ravi@gmail.com");
		customer.setPassword("pass");
		customer.setPhoneNum("8888555565");
		
		Mockito.when(customerService.deleteCustomerById(Mockito.anyInt())).thenReturn("Customer deleted Successfully");
		String response = customerService.deleteCustomerById(1);
		Assertions.assertNotNull(response);
		Assertions.assertEquals("Customer deleted Successfully", response);
		Assertions.assertDoesNotThrow(()->customerController.deleteCustomer(1));
	}
	
	@Test
	void testDeleteCustomer_Failed() {
		Mockito.when(customerService.deleteCustomerById(Mockito.anyInt())).thenReturn(null);
		String response = customerService.deleteCustomerById(10);
		Assertions.assertNull(response);
		Assertions.assertThrows(CustomerException.class,()-> customerController.deleteCustomer(10));
		
	}
}

@Nested
class GetAllCustomerTest{
	@Test
	void testGetAllCustomer() {
		Customer customer= new Customer();
		customer.setCustomerId(1);
		customer.setFirstName("ravi");
		customer.setLastName("theja");
		customer.setAddress("Hyd");
		customer.setEmailId("ravi@gmail.com");
		customer.setPassword("pass");
		customer.setPhoneNum("8888555565");
		
		List<Customer> customerList = null;
		//customerList.add(customer);
		
		Mockito.when(customerService.getAllCustomers()).thenReturn(customerList);
		
		Assertions.assertDoesNotThrow(()->customerController.getCustomer());
		
	}
}


}
