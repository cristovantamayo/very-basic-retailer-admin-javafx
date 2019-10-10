package com.cristovantamayo.veryBasicRetailerAdmin.model.dao;

import com.cristovantamayo.veryBasicRetailerAdmin.db.DB;
import com.cristovantamayo.veryBasicRetailerAdmin.model.dao.impl.CaixaDaoJDBC;
import com.cristovantamayo.veryBasicRetailerAdmin.model.dao.impl.CampanhaDaoJDBC;
import com.cristovantamayo.veryBasicRetailerAdmin.model.dao.impl.CategoriaDaoJDBC;
import com.cristovantamayo.veryBasicRetailerAdmin.model.dao.impl.ClienteDaoJDBC;
import com.cristovantamayo.veryBasicRetailerAdmin.model.dao.impl.LancamentoCaixaDaoJDBC;
import com.cristovantamayo.veryBasicRetailerAdmin.model.dao.impl.OrcamentoDaoJDBC;
import com.cristovantamayo.veryBasicRetailerAdmin.model.dao.impl.OrdemDaoJDBC;
import com.cristovantamayo.veryBasicRetailerAdmin.model.dao.impl.PagamentoDaoJDBC;
import com.cristovantamayo.veryBasicRetailerAdmin.model.dao.impl.PagamentoModalidadeDaoJDBC;
import com.cristovantamayo.veryBasicRetailerAdmin.model.dao.impl.ProdutoDaoJDBC;
import com.cristovantamayo.veryBasicRetailerAdmin.model.dao.impl.UsuarioDaoJDBC;
import com.cristovantamayo.veryBasicRetailerAdmin.model.entities.Caixa;
import com.cristovantamayo.veryBasicRetailerAdmin.model.entities.Campanha;
import com.cristovantamayo.veryBasicRetailerAdmin.model.entities.Categoria;
import com.cristovantamayo.veryBasicRetailerAdmin.model.entities.Cliente;
import com.cristovantamayo.veryBasicRetailerAdmin.model.entities.Orcamento;
import com.cristovantamayo.veryBasicRetailerAdmin.model.entities.Ordem;
import com.cristovantamayo.veryBasicRetailerAdmin.model.entities.Pagamento;
import com.cristovantamayo.veryBasicRetailerAdmin.model.entities.PagamentoModalidade;
import com.cristovantamayo.veryBasicRetailerAdmin.model.entities.Produto;
import com.cristovantamayo.veryBasicRetailerAdmin.model.entities.Usuario;

public class DaoFactory {
	public static Dao<Cliente> createClienteDao() {
		return (Dao<Cliente>) new ClienteDaoJDBC(DB.getConnection());
	}
		
	public static Dao<Orcamento> createOrcamentoDao() {
		return (Dao<Orcamento>) new OrcamentoDaoJDBC(DB.getConnection());
	}
	
	public static Dao<Ordem> createOrdemDao() {
		return (Dao<Ordem>) new OrdemDaoJDBC(DB.getConnection());
	}
	
	public static Dao<Produto> createProdutoDao() {
		return (Dao<Produto>) new ProdutoDaoJDBC(DB.getConnection());
	}
	
	public static Dao<Pagamento> createPagamentoDao() {
		return (Dao<Pagamento>) new PagamentoDaoJDBC(DB.getConnection());
	}
	
	public static Dao<Caixa> createCaixaDao() {
		return (Dao<Caixa>) new CaixaDaoJDBC(DB.getConnection());
	}
	
	public static Dao<PagamentoModalidade> createPagamentoModalidadeDao() {
		return (Dao<PagamentoModalidade>) new PagamentoModalidadeDaoJDBC(DB.getConnection());
	}
	
	public static Dao<Categoria> createCategoriaDao() {
		return (Dao<Categoria>) new CategoriaDaoJDBC(DB.getConnection());
	}
	
	public static Dao<Campanha> createCampanhaDao() {
		return (Dao<Campanha>) new CampanhaDaoJDBC(DB.getConnection());
	}
	
	public static Dao<Usuario> createUsuarioDao() {
		return (Dao<Usuario>) new UsuarioDaoJDBC(DB.getConnection());
	}
	
	public static LancamentoCaixaDao createLancamentoCaixaDao() {
		return (LancamentoCaixaDao) new LancamentoCaixaDaoJDBC(DB.getConnection());
	}
}
