package com.m2i.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m2i.crm.entity.Client;
import com.m2i.crm.repository.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	ClientRepository cRepo;
	
	public List<Client> getAll(){
		return cRepo.findAll();
	}

	public Client getById(int id) {
		return cRepo.findById(id).orElse(null);
	}
	
	public void create(Client c) {
		cRepo.save(c);
	}
	
	public void update(int id, Client c) {
		Client client = cRepo.findById(id).orElse(null);
		if(c!=null) {
			client.setCompanyName(c.getCompanyName());
			client.setFirstName(c.getFirstName());
			client.setLastName(c.getLastName());
			client.setEmail(c.getEmail());
			client.setPhone(c.getPhone());
			client.setAddress(c.getAddress());
			client.setZipCode(c.getZipCode());
			client.setCity(c.getZipCode());
			client.setCountry(c.getCountry());
			client.setState(c.getState());
			cRepo.save(client);
		}
	}
	
	public void delete(int id) {
		Client client = cRepo.findById(id).orElse(null);
		if(client!=null) {
			cRepo.delete(client);
		}
	}
}

