package com.m2i.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.m2i.crm.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

}
