package com.cristovantamayo.veryBasicRetailerAdmin.model.dao;

import java.util.List;

import com.cristovantamayo.veryBasicRetailerAdmin.model.entities.Caixa;

public interface CaixaDao {
	void insert(Caixa obj);
	void update(Caixa obj);
	void deleteById(Integer id);
	Caixa findById(Integer id);
	List<Caixa> findAll();
}