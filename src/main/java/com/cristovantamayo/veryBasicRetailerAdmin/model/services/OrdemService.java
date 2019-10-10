package com.cristovantamayo.veryBasicRetailerAdmin.model.services;

import java.util.List;

import com.cristovantamayo.veryBasicRetailerAdmin.model.dao.Dao;
import com.cristovantamayo.veryBasicRetailerAdmin.model.dao.DaoFactory;
import com.cristovantamayo.veryBasicRetailerAdmin.model.entities.Ordem;

public class OrdemService {
	
	private Dao<Ordem> dao = DaoFactory.createOrdemDao();
	
	public List<Ordem> findAll() {
		return dao.findAll();
	}
	
	public void saveOrUpdate(Ordem obj) {
		if(obj.getId() == null) {
			dao.insert(obj);
		} else {
			dao.update(obj);
		}
	}
	
	public void remove(Ordem obj) {
		dao.deleteById(obj.getId());
	}
}
