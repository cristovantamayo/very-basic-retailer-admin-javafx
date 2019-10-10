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
import com.cristovantamayo.veryBasicRetailerAdmin.model.entities.Usuario;

public class UsuarioDaoJDBC implements Dao<Usuario> {
	
	private Connection conn;
	
	public UsuarioDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	private Usuario instantiateUsuario(ResultSet rs) throws SQLException {
		Usuario user = new Usuario();
		user.setId(rs.getInt("id"));
		user.setNome(rs.getString("nome"));
		user.setUsuario(rs.getString("usuario"));
		user.setSenha(rs.getString("senha"));
		user.setRemovido(rs.getString("removido"));
		return user;
	}

	@Override
	public void insert(Usuario obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO usuarios(nome,"
															+ "usuario, "
															+ "senha, "
															+ "removido, "
															+ ") VALUES (?, ?, ?, ?)");
			st.setString(1, obj.getNome());
			st.setString(2, obj.getUsuario());
			st.setString(3, obj.getSenha());
			st.setString(4, obj.getRemovido());
			
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
	public void update(Usuario obj) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("UPDATE usuarios SET name = ?, "
															+ "usuario = ?, "
															+ "senha = ?, "
															+ "removido = ? WHERE id = ?");
			st.setString(1, obj.getNome());
			st.setString(2, obj.getUsuario());
			st.setString(3, obj.getSenha());
			st.setString(4, obj.getRemovido());
			
			st.setInt(5, obj.getId());
			
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
			st = conn.prepareStatement("DELETE FROM usuarios WHERE id = ?");
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
	public Usuario findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT usuarios.* " + 
					"FROM usuarios " + 
					"WHERE id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()) {
				Usuario user = instantiateUsuario(rs);
				return user;
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
	public List<Usuario> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT usuarios.* " + 
					"FROM usuarios " +
					"ORDER BY nome");
			
			rs = st.executeQuery();
			
			List<Usuario> list = new ArrayList<>();
			
			while (rs.next()) {
					
				Usuario user = instantiateUsuario(rs);
				list.add(user);
		
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
