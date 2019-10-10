package com.cristovantamayo.veryBasicRetailerAdmin.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cristovantamayo.veryBasicRetailerAdmin.db.DB;
import com.cristovantamayo.veryBasicRetailerAdmin.db.DbException;
import com.cristovantamayo.veryBasicRetailerAdmin.model.dao.Dao;
import com.cristovantamayo.veryBasicRetailerAdmin.model.entities.Orcamento;

public class OrcamentoDaoJDBC implements Dao<Orcamento> {
	
	private Connection conn;
	
	public OrcamentoDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Orcamento obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"INSERT INTO orcamentos ("
				+ "qtd_produtos, "
				+ "codigo_cliente, "
				+ "data, "
				+ "valor, "
				+ "desconto, "
				+ "vezes"
				+ "baixado) " + 
				"VALUES " + 
				"(?, ?, ?, ?, ?, ?, ?)",
				Statement.RETURN_GENERATED_KEYS);
			
			st.setInt(1, obj.getQtd_produtos());
			st.setInt(2,  obj.getCodigo_cliente());
			st.setDate(3, obj.getData());
			st.setDouble(4, obj.getValor());
			st.setDouble(5, obj.getDesconto());
			st.setInt(6,  obj.getVezes());
			st.setString(7,  obj.getBaixado());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Unexpected error: No rows Affected!");
			}
			
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void update(Orcamento obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"UPDATE seller " + 
				"SET qtd_produtos = ?, "
					+ "codigo_cliente = ?, "
					+ "data = ?, "
					+ "valor = ?, "
					+ "desconto = ?, "
					+ "vezes = ?, "
					+ "baixado = ?" + 
				"WHERE id = ?");
			
			st.setInt(1, obj.getQtd_produtos());
			st.setInt(2, obj.getCodigo_cliente());
			st.setDate(3, obj.getData());
			st.setDouble(4, obj.getValor());
			st.setDouble(5, obj.getDesconto());
			st.setInt(6, obj.getVezes());
			st.setString(7, obj.getBaixado());
			st.setInt(8,  obj.getId());
			
			st.executeUpdate();
			
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
			st = conn.prepareStatement("DELETE FROM orcamentos WHERE id = ?");
			st.setInt(1,  id);
			st.executeUpdate();
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public Orcamento findById(Integer id) {

		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT orcamentos.* FROM orcamentos WHERE orcamentos.id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()) {
				Orcamento orc = instantiateOrcamento(rs);
				return orc;
			}
			
			return null;
			
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}
	
	

	private Orcamento instantiateOrcamento(ResultSet rs) throws SQLException {
		Orcamento obj = new Orcamento();
		obj.setId(rs.getInt("id"));
		obj.setCodigo_cliente(rs.getInt("codigo_cliente"));
		obj.setQtd_produtos(rs.getInt("qtd_produtos"));
		obj.setValor(rs.getDouble("valor"));
		obj.setDesconto(rs.getDouble("desconto"));
		obj.setVezes(rs.getInt("vezes"));
		obj.setData(rs.getDate("data"));
		obj.setBaixado(rs.getString("baixado"));
		return obj;
	}

	@Override
	public List<Orcamento> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT orcamentos.* FROM orcamentos ORDER BY date DESC");
			
			rs = st.executeQuery();
			
			List<Orcamento> list = new ArrayList<>();
						
			while (rs.next()) {
				
				Orcamento obj = instantiateOrcamento(rs);
				list.add(obj);
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
