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
import com.cristovantamayo.veryBasicRetailerAdmin.model.entities.Categoria;

public class CategoriaDaoJDBC implements Dao<Categoria> {
	
	private Connection conn;
	
	public CategoriaDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	private Categoria instantiateCategoria(ResultSet rs) throws SQLException {
		Categoria cat = new Categoria();
		cat.setId(rs.getInt("id"));
		cat.setNome(rs.getString("nome"));
		return cat;
	}

	@Override
	public void insert(Categoria obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO categorias(nome) VALUES (?)");
			st.setString(1, obj.getNome());
			
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
	public void update(Categoria obj) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("UPDATE categorias SET nome = ? WHERE id = ?");
			st.setString(1, obj.getNome());
			
			st.setInt(2, obj.getId());
			
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
			st = conn.prepareStatement("DELETE FROM categorias WHERE id = ?");
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
	public Categoria findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT categorias.* " + 
					"FROM categorias " + 
					"WHERE id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()) {
				Categoria cat = instantiateCategoria(rs);
				return cat;
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
	public List<Categoria> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT categorias.* " + 
					"FROM categorias " +
					"ORDER BY nome DESC");
			
			rs = st.executeQuery();
			
			List<Categoria> list = new ArrayList<>();
			
			while (rs.next()) {
					
				Categoria cat = instantiateCategoria(rs);
				list.add(cat);
		
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
