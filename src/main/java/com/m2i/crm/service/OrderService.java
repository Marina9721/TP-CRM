package com.m2i.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m2i.crm.entity.Order;
import com.m2i.crm.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	OrderRepository oRepo;

	public List<Order> getAll() {
		return oRepo.findAll();
	}
	
	public Order getById(int id) {
		return oRepo.findById(id).orElse(null);
	}
	
	public void create(Order o) {
		oRepo.save(o);
	}
	
	public void update(int id, Order o) {
		Order order = oRepo.findById(id).orElse(null);
		if(o!=null) {
			order.setTypePresta(o.getTypePresta());
			order.setDesignation(o.getDesignation());
			order.setNbDays(o.getNbDays());
			order.setUnitPrice(o.getUnitPrice());
			order.setState(o.getState());
			//order.setClient(o.getClient());
		}
		oRepo.save(order);
	}
	
	public void delete(int id) {
		Order order = oRepo.findById(id).orElse(null);
		if(order!=null) {
			oRepo.delete(order);
		}
	}
}
