package com.cristovantamayo.veryBasicRetailerAdmin.model.services;

import java.util.List;

import com.cristovantamayo.veryBasicRetailerAdmin.model.dao.Dao;
import com.cristovantamayo.veryBasicRetailerAdmin.model.dao.DaoFactory;
import com.cristovantamayo.veryBasicRetailerAdmin.model.entities.PagamentoModalidade;

public class PagamentoModalidadeService {
	
	private Dao<PagamentoModalidade> dao = DaoFactory.createPagamentoModalidadeDao();
	
	public List<PagamentoModalidade> findAll() {
		return dao.findAll();
	}
	
	public void saveOrUpdate(PagamentoModalidade obj) {
		if(obj.getId() == null) {
			dao.insert(obj);
		} else {
			dao.update(obj);
		}
	}
	
	public void remove(PagamentoModalidade obj) {
		dao.deleteById(obj.getId());
	}
}
