package com.cristovantamayo.veryBasicRetailerAdmin.model.dao;

import java.util.List;

import com.cristovantamayo.veryBasicRetailerAdmin.model.entities.Orcamento;

public interface OrcamentoDao {
	void insert(Orcamento obj);
	void update(Orcamento obj);
	void deleteById(Integer id);
	Orcamento findById(Integer id);
	List<Orcamento> findAll();
}