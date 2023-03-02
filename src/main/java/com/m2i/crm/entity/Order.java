package com.m2i.crm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.javafaker.Faker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Table(name="orders")
@Data @NoArgsConstructor @AllArgsConstructor
public class Order {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank @NotEmpty(message="must enter a type presta")
	private String typePresta;
	
	@NotBlank @NotEmpty(message="must enter a designation")
	private String designation;
	
	@NotNull(message="must enter a number of days")
	private int nbDays;
	
	@Positive(message="unit price must be positive")
	private int unitPrice;
	
	@Range(min=0, max=2, message="must be 0 1 or 2")
	private int state; // CANCELLED : 0 OPTION : 1 CONFIRMED : 2
	
	@ManyToOne
	@JsonIgnore
	private Client client;
	
	public Order(Faker f) {
		this.typePresta = f.lorem().word();
		this.designation = f.lorem().sentence(3);
		this.nbDays = f.number().numberBetween(1, 200);
		this.unitPrice = f.number().numberBetween(1, 1000);
		this.state = f.number().numberBetween(0, 3);
	}
}
