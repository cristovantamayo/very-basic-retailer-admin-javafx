package com.cristovantamayo.veryBasicRetailerAdmin.model.services;

import java.util.List;

import com.cristovantamayo.veryBasicRetailerAdmin.model.dao.Dao;
import com.cristovantamayo.veryBasicRetailerAdmin.model.dao.DaoFactory;
import com.cristovantamayo.veryBasicRetailerAdmin.model.entities.Pagamento;

public class PagamentoService {
	
	private Dao<Pagamento> dao = DaoFactory.createPagamentoDao();
	
	public List<Pagamento> findAll() {
		return dao.findAll();
	}
	
	public void saveOrUpdate(Pagamento obj) {
		if(obj.getId() == null) {
			dao.insert(obj);
		} else {
			dao.update(obj);
		}
	}
	
	public void remove(Pagamento obj) {
		dao.deleteById(obj.getId());
	}
}
