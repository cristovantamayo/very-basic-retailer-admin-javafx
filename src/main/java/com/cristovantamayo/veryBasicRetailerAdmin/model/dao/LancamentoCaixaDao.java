package com.cristovantamayo.veryBasicRetailerAdmin.model.dao;

import java.util.List;

import com.cristovantamayo.veryBasicRetailerAdmin.model.entities.LancamentoCaixa;

public interface LancamentoCaixaDao {
	void insert(LancamentoCaixa obj);
	void update(LancamentoCaixa obj);
	void deleteById(Integer id);
	LancamentoCaixa findById(Integer id);
	List<LancamentoCaixa> findAll(Integer id);
}