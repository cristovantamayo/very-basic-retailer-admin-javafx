package com.cristovantamayo.veryBasicRetailerAdmin.model.services;

import java.util.List;

import com.cristovantamayo.veryBasicRetailerAdmin.model.dao.Dao;
import com.cristovantamayo.veryBasicRetailerAdmin.model.dao.DaoFactory;
import com.cristovantamayo.veryBasicRetailerAdmin.model.entities.Usuario;

public class UsuarioService {
	
	private Dao<Usuario> dao = DaoFactory.createUsuarioDao();
	
	public List<Usuario> findAll() {
		return dao.findAll();
	}
	
	public void saveOrUpdate(Usuario obj) {
		if(obj.getId() == null) {
			dao.insert(obj);
		} else {
			dao.update(obj);
		}
	}
	
	public void remove(Usuario obj) {
		dao.deleteById(obj.getId());
	}
}
