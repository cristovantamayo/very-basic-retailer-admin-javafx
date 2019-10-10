package com.cristovantamayo.veryBasicRetailerAdmin.model.services;

import java.util.List;

import com.cristovantamayo.veryBasicRetailerAdmin.model.dao.Dao;
import com.cristovantamayo.veryBasicRetailerAdmin.model.dao.DaoFactory;
import com.cristovantamayo.veryBasicRetailerAdmin.model.entities.Caixa;

public class CaixaService {
	
	private Dao<Caixa> dao = DaoFactory.createCaixaDao();
	
	public List<Caixa> findAll() {
		return dao.findAll();
	}
	
	public void saveOrUpdate(Caixa obj) {
		if(obj.getId() == null) {
			dao.insert(obj);
		} else {
			dao.update(obj);
		}
	}
	
	public void remove(Caixa obj) {
		dao.deleteById(obj.getId());
	}
}
