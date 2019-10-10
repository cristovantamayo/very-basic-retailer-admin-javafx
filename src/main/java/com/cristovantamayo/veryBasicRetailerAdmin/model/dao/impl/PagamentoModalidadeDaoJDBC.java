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
import com.cristovantamayo.veryBasicRetailerAdmin.model.entities.PagamentoModalidade;

public class PagamentoModalidadeDaoJDBC implements Dao<PagamentoModalidade> {
	
	private Connection conn;
	
	public PagamentoModalidadeDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	private PagamentoModalidade instantiatePagamentoModalidade(ResultSet rs) throws SQLException {
		PagamentoModalidade pag = new PagamentoModalidade();
		pag.setId(rs.getInt("id"));
		pag.setDescricao(rs.getString("descricao"));
		pag.setTaxa(rs.getDouble("taxa_percentual"));
		pag.setOrdem(rs.getInt("ordem"));
		return pag;
	}

	@Override
	public void insert(PagamentoModalidade obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO pagamentos_modalidades (descricao,"
															+ "taxa_percentual, "
															+ "ordem "
															+ ") VALUES (?, ?, ?)");
			st.setString(1, obj.getDescricao());
			st.setDouble(2, obj.getTaxa());
			st.setDouble(3, obj.getOrdem());
			
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
	public void update(PagamentoModalidade obj) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("UPDATE pagamentos_modalidades SET descricao = ?, "
															+ "taxa_percentual = ?, "
															+ "ordem =  ? WHERE id = ?");
			st.setString(1, obj.getDescricao());
			st.setDouble(2, obj.getTaxa());
			st.setDouble(3, obj.getOrdem());
			
			st.setInt(4, obj.getId());
			
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
			st = conn.prepareStatement("DELETE FROM pagamentos_modalidades WHERE id = ?");
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
	public PagamentoModalidade findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT * " + 
					"FROM pagamentos_modalidades " + 
					"WHERE id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()) {
				PagamentoModalidade pag = instantiatePagamentoModalidade(rs);
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
	public List<PagamentoModalidade> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT * " + 
					"FROM pagamentos_modalidades " +
					"ORDER BY ordem ASC");
			
			rs = st.executeQuery();
			
			List<PagamentoModalidade> list = new ArrayList<>();
			
			while (rs.next()) {
					
				PagamentoModalidade pag = instantiatePagamentoModalidade(rs);
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
