package com.cristovantamayo.veryBasicRetailerAdmin.model.dao;

import java.util.List;

import com.cristovantamayo.veryBasicRetailerAdmin.model.entities.PagamentoModalidade;

public interface PagamentoModalidadeDao {
	void insert(PagamentoModalidade obj);
	void update(PagamentoModalidade obj);
	void deleteById(Integer id);
	PagamentoModalidade findById(Integer id);
	List<PagamentoModalidade> findAll();
}