package com.cristovantamayo.veryBasicRetailerAdmin.model.dao;

import java.util.List;

import com.cristovantamayo.veryBasicRetailerAdmin.model.entities.Campanha;

public interface CampanhaDao {
	void insert(Campanha obj);
	void update(Campanha obj);
	void deleteById(Integer id);
	Campanha findById(Integer id);
	List<Campanha> findAll();
}