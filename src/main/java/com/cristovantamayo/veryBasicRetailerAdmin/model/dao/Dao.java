package com.cristovantamayo.veryBasicRetailerAdmin.model.dao;

import java.util.List;

public interface Dao<E> {
	void insert(E obj);	
	void update(E obj);
	void deleteById(Integer id);
	E findById(Integer id);
	List<E> findAll();
}