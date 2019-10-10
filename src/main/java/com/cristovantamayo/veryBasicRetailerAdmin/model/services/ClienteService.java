package com.cristovantamayo.veryBasicRetailerAdmin.model.services;

import java.util.List;

import com.cristovantamayo.veryBasicRetailerAdmin.model.dao.Dao;
import com.cristovantamayo.veryBasicRetailerAdmin.model.dao.DaoFactory;
import com.cristovantamayo.veryBasicRetailerAdmin.model.entities.Cliente;

public class ClienteService {
	
	private Dao<Cliente> dao = DaoFactory.createClienteDao();
	
	public List<Cliente> findAll() {
		return dao.findAll();
	}
	
	public void saveOrUpdate(Cliente cli) {
		if(cli.getId() == null) {
			dao.insert(cli);
		} else {
			dao.update(cli);
		}
	}
	
	public void remove(Cliente cli) {
		dao.deleteById(cli.getId());
	}
}
