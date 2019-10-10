package com.cristovantamayo.veryBasicRetailerAdmin.model.dao;

import java.util.List;

import com.cristovantamayo.veryBasicRetailerAdmin.model.entities.Pagamento;

public interface PagamentoDao {
	void insert(Pagamento obj);
	void update(Pagamento obj);
	void deleteById(Integer id);
	Pagamento findById(Integer id);
	List<Pagamento> findAll();
}