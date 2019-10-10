package com.cristovantamayo.veryBasicRetailerAdmin.model.services;

import java.util.List;

import com.cristovantamayo.veryBasicRetailerAdmin.model.dao.DaoFactory;
import com.cristovantamayo.veryBasicRetailerAdmin.model.dao.LancamentoCaixaDao;
import com.cristovantamayo.veryBasicRetailerAdmin.model.entities.LancamentoCaixa;

public class LancamentoCaixaService {
	
	private LancamentoCaixaDao dao = DaoFactory.createLancamentoCaixaDao();
	
	public List<LancamentoCaixa> findAll(Integer id) {
		return dao.findAll(id);
	}
	
	public void saveOrUpdate(LancamentoCaixa obj) {
		if(obj.getId() == null) {
			dao.insert(obj);
		} else {
			dao.update(obj);
		}
	}
	
	public void remove(LancamentoCaixa obj) {
		dao.deleteById(obj.getId());
	}
}
