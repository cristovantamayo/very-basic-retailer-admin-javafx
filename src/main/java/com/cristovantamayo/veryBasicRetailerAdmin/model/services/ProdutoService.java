package com.cristovantamayo.veryBasicRetailerAdmin.model.services;

import java.util.List;

import com.cristovantamayo.veryBasicRetailerAdmin.model.dao.Dao;
import com.cristovantamayo.veryBasicRetailerAdmin.model.dao.DaoFactory;
import com.cristovantamayo.veryBasicRetailerAdmin.model.entities.Produto;

public class ProdutoService {
	
	private Dao<Produto> dao = DaoFactory.createProdutoDao();
	
	public List<Produto> findAll() {
		return dao.findAll();
	}
	
	public void saveOrUpdate(Produto obj) {
		if(obj.getId() == null) {
			dao.insert(obj);
		} else {
			dao.update(obj);
		}
	}
	
	public void remove(Produto obj) {
		dao.deleteById(obj.getId());
	}
}
