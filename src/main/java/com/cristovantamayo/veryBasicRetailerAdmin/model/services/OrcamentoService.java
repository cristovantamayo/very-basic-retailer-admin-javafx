package com.cristovantamayo.veryBasicRetailerAdmin.model.services;

import java.util.List;

import com.cristovantamayo.veryBasicRetailerAdmin.model.dao.Dao;
import com.cristovantamayo.veryBasicRetailerAdmin.model.dao.DaoFactory;
import com.cristovantamayo.veryBasicRetailerAdmin.model.entities.Orcamento;

public class OrcamentoService {
	
	private Dao<Orcamento> dao = DaoFactory.createOrcamentoDao();
	
	public List<Orcamento> findAll() {
		return dao.findAll();
	}
	
	public void saveOrUpdate(Orcamento obj) {
		if(obj.getId() == null) {
			dao.insert(obj);
		} else {
			dao.update(obj);
		}
	}
	
	public void remove(Orcamento obj) {
		dao.deleteById(obj.getId());
	}
}
