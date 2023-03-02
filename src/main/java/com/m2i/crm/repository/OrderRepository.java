package com.m2i.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.m2i.crm.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
