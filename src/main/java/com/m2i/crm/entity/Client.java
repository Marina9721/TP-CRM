package com.m2i.crm.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Range;

import com.github.javafaker.Faker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Table(name="clients")
@Data @NoArgsConstructor @AllArgsConstructor
public class Client {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank @NotEmpty(message="must enter a company name")
	private String companyName;
	
	@NotBlank @NotEmpty(message="must enter a first name")
	private String firstName;
	
	@NotBlank @NotEmpty(message="must enter a last name")
	private String lastName;
	
	@Email(message="invalid email, enter a proper email") 
	@NotEmpty(message="enter your email")
	@Column(unique=true)
	private String email;
	
	private String phone;
	
	@NotBlank @NotEmpty(message="must enter an address")
	private String address;
	
	@NotBlank @NotEmpty(message="must enter a zipcode")
	private String zipCode;
	
	@NotBlank @NotEmpty(message="must enter a city")
	private String city;
	
	@NotBlank @NotEmpty(message="must enter a country")
	private String country;
	
	@Range(min=0, max=1, message="must be 0 or 1")
	private int state;  //INACTIVE : 0 ACTIVE : 1
	
	@OneToMany(targetEntity = Order.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_oc", referencedColumnName = "id")
	private List<Order> orders;
	
	
	public Client(Faker f) {
		this.companyName = f.company().name();
		this.firstName = f.name().firstName();
		this.lastName = f.name().lastName();
		this.email = f.internet().emailAddress();
		this.phone = f.phoneNumber().phoneNumber();
		this.address = f.address().streetAddress();
		this.zipCode = f.address().zipCode();
		this.city = f.address().city();
		this.country = f.address().country();
		this.state = f.number().numberBetween(0, 2);
		this.orders = new ArrayList<>();
	}
	
}
