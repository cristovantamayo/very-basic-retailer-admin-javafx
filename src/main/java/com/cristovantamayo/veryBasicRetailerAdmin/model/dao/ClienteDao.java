package com.cristovantamayo.veryBasicRetailerAdmin.model.dao;

import java.util.List;

import com.cristovantamayo.veryBasicRetailerAdmin.model.entities.Cliente;

public interface ClienteDao {
	void insert(Cliente obj);
	void update(Cliente obj);
	void deleteById(Integer id);
	Cliente findById(Integer id);
	List<Cliente> findAll();
}