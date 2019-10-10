package com.cristovantamayo.veryBasicRetailerAdmin.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cristovantamayo.veryBasicRetailerAdmin.db.DB;
import com.cristovantamayo.veryBasicRetailerAdmin.db.DbException;
import com.cristovantamayo.veryBasicRetailerAdmin.model.dao.Dao;
import com.cristovantamayo.veryBasicRetailerAdmin.model.entities.Pagamento;

public class PagamentoDaoJDBC implements Dao<Pagamento> {
	
	private Connection conn;
	
	public PagamentoDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	private Pagamento instantiatePagamento(ResultSet rs) throws SQLException {
		Pagamento pag = new Pagamento();
		pag.setId(rs.getInt("id"));
		pag.setId_orcamento(rs.getInt("id_orcamento"));
		pag.setNumero(rs.getInt("numero"));
		pag.setId_modalidade(rs.getInt("id_modalidade"));
		pag.setTaxa(rs.getDouble("taxa"));
		pag.setCusto(rs.getDouble("custo"));
		pag.setValor_cobrado(rs.getDouble("valor_cobrado"));
		return pag;
	}

	@Override
	public void insert(Pagamento obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO pagamentos (id_orcamento,"
															+ "numero, "
															+ "id_modalidade, "
															+ "taxa, "
															+ "custo, "
															+ "valor_cobrado"
															+ ") VALUES (?, ?, ?, ?, ?, ?)");
			st.setInt(1, obj.getId());
			st.setInt(2, obj.getNumero());
			st.setInt(3, obj.getId_modalidade());
			st.setDouble(4, obj.getTaxa());
			st.setDouble(5, obj.getCusto());
			st.setDouble(6, obj.getValor_cobrado());
			
			st.executeUpdate();
			System.out.println("Inserted!");
			System.out.println();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void update(Pagamento obj) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("UPDATE pagamentos SET id_orcamento = ?, "
															+ "numero = ?, "
															+ "id_modalidade = ?, "
															+ "taxa = ?, "
															+ "custo = ?, "
															+ "valor_cobrado = ? WHERE id = ?");
			st.setInt(1, obj.getId_orcamento());
			st.setInt(2, obj.getNumero());
			st.setInt(3, obj.getId_modalidade());
			st.setDouble(4, obj.getTaxa());
			st.setDouble(5, obj.getCusto());
			st.setDouble(6, obj.getValor_cobrado());
			
			st.setInt(7, obj.getId());
			
			int rowsAffected = st.executeUpdate();
			System.out.println( rowsAffected + " rows affected!");
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM pagamentos WHERE id = ?");
			st.setInt(1, id);
			st.executeUpdate();
			System.out.println("Deleted!");
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Pagamento findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT * " + 
					"FROM pagamentos " + 
					"WHERE id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()) {
				Pagamento pag = instantiatePagamento(rs);
				return pag;
			}
			
			return null;
			
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Pagamento> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT * " + 
					"FROM pagamentos " +
					"ORDER BY data DESC");
			
			rs = st.executeQuery();
			
			List<Pagamento> list = new ArrayList<>();
			
			while (rs.next()) {
					
				Pagamento pag = instantiatePagamento(rs);
				list.add(pag);
		
			}
			
			return list;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}
