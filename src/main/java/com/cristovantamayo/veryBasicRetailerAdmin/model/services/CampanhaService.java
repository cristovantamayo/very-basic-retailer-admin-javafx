package com.cristovantamayo.veryBasicRetailerAdmin.model.services;

import java.util.List;

import com.cristovantamayo.veryBasicRetailerAdmin.model.dao.Dao;
import com.cristovantamayo.veryBasicRetailerAdmin.model.dao.DaoFactory;
import com.cristovantamayo.veryBasicRetailerAdmin.model.entities.Campanha;

public class CampanhaService {
	
	private Dao<Campanha> dao = DaoFactory.createCampanhaDao();
	
	public List<Campanha> findAll() {
		return dao.findAll();
	}
	
	public void saveOrUpdate(Campanha obj) {
		if(obj.getId() == null) {
			dao.insert(obj);
		} else {
			dao.update(obj);
		}
	}
	
	public void remove(Campanha obj) {
		dao.deleteById(obj.getId());
	}
}
