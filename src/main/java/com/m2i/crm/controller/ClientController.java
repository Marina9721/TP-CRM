package com.m2i.crm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
import com.m2i.crm.service.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {
	
	@Autowired
	ClientService cService;
	
	@Autowired
	ClientRepository cRepo;
	
	@Autowired
	OrderRepository oRepo;
	
	@PostMapping("/fake/{number}")
	public List<Client> createFakeClients(@PathVariable int number) {
		List<Client> clients = new ArrayList<>();
		Faker f = new Faker();
		Random r = new Random();
		for(int i=0; i<number; i++) {
			Client c = new Client(f);
			cRepo.save(c);
			
			for(int j=0; j<r.nextInt(5); j++) {
				Order o = new Order(f);
				c.getOrders().add(o);
				oRepo.save(o);
			}
			clients.add(c);
		}
		return clients;
	}
	
	@GetMapping("/getall")
	public List<Client> getAllClients(){
		return cService.getAll();
	}
	
	@GetMapping("/getone/{id}")
	public Client getClientById(@PathVariable int id) {
		return cService.getById(id);
	}
	
	@PostMapping("/create")
	public void postClient(@RequestBody @Valid Client c) {
		cService.create(c);	
	}
	
	@PutMapping("/modify/{id}")
	public void putClient(@PathVariable int id, @RequestBody @Valid Client c) {
		cService.update(id, c);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteClient(@PathVariable int id) {
		cService.delete(id);
	}

}
