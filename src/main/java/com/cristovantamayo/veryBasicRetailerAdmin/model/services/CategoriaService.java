package com.cristovantamayo.veryBasicRetailerAdmin.model.services;

import java.util.List;

import com.cristovantamayo.veryBasicRetailerAdmin.model.dao.Dao;
import com.cristovantamayo.veryBasicRetailerAdmin.model.dao.DaoFactory;
import com.cristovantamayo.veryBasicRetailerAdmin.model.entities.Categoria;

public class CategoriaService {
	
	private Dao<Categoria> dao = DaoFactory.createCategoriaDao();
	
	public List<Categoria> findAll() {
		return dao.findAll();
	}
	
	public void saveOrUpdate(Categoria obj) {
		if(obj.getId() == null) {
			dao.insert(obj);
		} else {
			dao.update(obj);
		}
	}
	
	public void remove(Categoria obj) {
		dao.deleteById(obj.getId());
	}
}
