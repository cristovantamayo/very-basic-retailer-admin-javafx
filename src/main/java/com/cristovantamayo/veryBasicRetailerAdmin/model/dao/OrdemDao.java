package com.cristovantamayo.veryBasicRetailerAdmin.model.dao;

import java.util.List;

import com.cristovantamayo.veryBasicRetailerAdmin.model.entities.Ordem;

public interface OrdemDao {
	void insert(Ordem obj);
	void update(Ordem obj);
	void deleteById(Integer id);
	Ordem findById(Integer id);
	List<Ordem> findAll();
}