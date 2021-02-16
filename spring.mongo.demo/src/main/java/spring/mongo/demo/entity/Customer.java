package spring.mongo.demo.entity;

import javax.persistence.Column;
import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Document
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customerId;

	@Length(min=3,message="Name minimum length has to be 3")
    private String firstName;

    private String lastName;

    @Indexed(unique=true)
    @Email(message = "Email should be valid")
    @NotNull(message="Email Id not entered")
    private String emailId;
    
    @Indexed(unique=true)
    @Length(min=10,max=10,message="Phone number length has to be 10")
    @NotNull(message="Phone number not entered")
    private String phoneNum;
    
    @NotNull(message="Password not entered")
    @Length(min=4,message="Name minimum length has to be 4")
    private String password;
    
    private String address;
   
	public Customer() {}

	public Customer(Customer customer2) {
		super();
		this.customerId = customer2.customerId;
		this.firstName = customer2.firstName;
		this.lastName = customer2.lastName;
		this.emailId = customer2.emailId;
		this.phoneNum = customer2.phoneNum;
		this.password = customer2.password;
		this.address = customer2.address;
		}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
    
    

}
