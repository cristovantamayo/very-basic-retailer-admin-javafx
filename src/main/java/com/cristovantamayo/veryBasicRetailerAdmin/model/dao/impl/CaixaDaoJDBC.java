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
import com.cristovantamayo.veryBasicRetailerAdmin.model.entities.Caixa;
import com.cristovantamayo.veryBasicRetailerAdmin.utils.Tools;

public class CaixaDaoJDBC implements Dao<Caixa> {
	
	private Connection conn;
	
	public CaixaDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	private Caixa instantiateCaixa(ResultSet rs) throws SQLException {
		Caixa cx = new Caixa();
		cx.setId(rs.getInt("id"));
		cx.setData(rs.getDate("data"));
		cx.setValorInicial(rs.getDouble("valor_inicial"));
		cx.setCreditos(rs.getDouble("creditos"));
		cx.setDebitos(rs.getDouble("debitos"));
		cx.setTotal(rs.getDouble("total"));
		return cx;
	}

	@Override
	public void insert(Caixa obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO caixas(data,"
															+ "valor_inicial, "
															+ "creditos, "
															+ "debitos, "
															+ "total, "
															+ ") VALUES (?, ?, ?, ?, ?, ?)");
			st.setDate(1, Tools.convertUtilToSql(obj.getData()));
			st.setDouble(2, obj.getValorInicial());
			st.setDouble(3, obj.getCreditos());
			st.setDouble(4, obj.getDebitos());
			st.setDouble(5, obj.getTotal());
			
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
	public void update(Caixa obj) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("UPDATE caixas SET data = ?, "
															+ "valor_inicial = ?, "
															+ "creditos = ?, "
															+ "debitos = ?, "
															+ "total = ? WHERE id = ?");
			st.setDate(1, Tools.convertUtilToSql(obj.getData()));
			st.setDouble(2, obj.getValorInicial());
			st.setDouble(3, obj.getCreditos());
			st.setDouble(4, obj.getDebitos());
			st.setDouble(5, obj.getTotal());
			
			st.setInt(6, obj.getId());
			
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
			st = conn.prepareStatement("DELETE FROM caixas WHERE id = ?");
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
	public Caixa findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT caixas.* " + 
					"FROM caixas " + 
					"WHERE id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()) {
				Caixa cx = instantiateCaixa(rs);
				return cx;
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
	public List<Caixa> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT caixas.* " + 
					"FROM caixas " +
					"ORDER BY data DESC");
			
			rs = st.executeQuery();
			
			List<Caixa> list = new ArrayList<>();
			
			while (rs.next()) {
					
				Caixa cx = instantiateCaixa(rs);
				list.add(cx);
		
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
