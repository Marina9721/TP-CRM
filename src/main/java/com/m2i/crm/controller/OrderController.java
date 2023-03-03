package com.m2i.crm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.javafaker.Faker;
import com.m2i.crm.entity.Client;
import com.m2i.crm.entity.Order;
import com.m2i.crm.repository.ClientRepository;
import com.m2i.crm.repository.OrderRepository;
import com.m2i.crm.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	OrderService oService;
	
	@Autowired
	OrderRepository oRepo;
	
	@Autowired
	ClientRepository cRepo;
	
	
	@PostMapping("/fake/{idClient}/{number}")
	public List<Order> createFakeOrders(@PathVariable int idClient, @PathVariable int number) {
		List<Order> orders = new ArrayList<>();
		Faker f = new Faker();
		Client c = cRepo.findById(idClient).orElse(null);
		if(c!=null) {
			for(int i=0; i<number; i++) {
				Order o = new Order(f);
				oRepo.save(o);
				c.getOrders().add(o);
				cRepo.save(c);
				orders.add(o);
			}
		}
		
		return orders;
	}
	
	@GetMapping("/getall")
	public List<Order> getAllOrders(){
		return oService.getAll();
	}
	
	@GetMapping("/getone/{id}")
	public Order getOrderById(@PathVariable int id) {
		return oService.getById(id);
	}
	
	@PostMapping("/create/{idClient}")
	public void postOrder(@RequestBody @Valid Order o, @PathVariable int idClient) {
		Client c = cRepo.findById(idClient).orElse(null);
		if(c!=null) {
			oService.create(o);
			c.getOrders().add(o);
			cRepo.save(c);
		}
	}
	
	@PutMapping("/modify/{id}")
	public void putOrder(@PathVariable int id, @RequestBody @Valid Order o) {
		oService.update(id, o);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteOrder(@PathVariable int id) {
		oService.delete(id);
	}

}
