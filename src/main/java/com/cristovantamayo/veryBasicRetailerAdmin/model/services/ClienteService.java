package com.cristovantamayo.veryBasicRetailerAdmin.model.services;

import java.util.List;

import com.cristovantamayo.veryBasicRetailerAdmin.model.dao.ClienteDao;
import com.cristovantamayo.veryBasicRetailerAdmin.model.dao.DaoFactory;
import com.cristovantamayo.veryBasicRetailerAdmin.model.entities.Cliente;

public class ClienteService {
	
	private ClienteDao dao = DaoFactory.createClienteDao();
	
	public List<Cliente> findAll() {
		return dao.findAll();
	}
	
	public List<Cliente> findByName(String partial, Integer limite) {
		return dao.findByName(partial, limite);
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
