package com.cristovantamayo.veryBasicRetailerAdmin.model.dao;

import java.util.List;

import com.cristovantamayo.veryBasicRetailerAdmin.model.entities.Usuario;

public interface UsuarioDao {
	void insert(Usuario obj);
	void update(Usuario obj);
	void deleteById(Integer id);
	Usuario findById(Integer id);
	List<Usuario> findAll();
}