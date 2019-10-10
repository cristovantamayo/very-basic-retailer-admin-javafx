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
import com.cristovantamayo.veryBasicRetailerAdmin.model.entities.Ordem;
import com.cristovantamayo.veryBasicRetailerAdmin.utils.Tools;

public class OrdemDaoJDBC implements Dao<Ordem> {
	
	private Connection conn;
	
	public OrdemDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	private Ordem instantiateOrdem(ResultSet rs) throws SQLException {
		Ordem ord = new Ordem();
		ord.setId(rs.getInt("id"));
		ord.setId_cliente(rs.getInt("id_cliente"));
		ord.setId_orcamento(rs.getInt("id_orcamento"));
		ord.setDescr(rs.getString("descr"));
		ord.setData(rs.getDate("data"));
		ord.setData_baixa(rs.getDate("data_baixa"));
		ord.setBaixada(rs.getString("baixada"));
		ord.setObs(rs.getString("obs"));
		return ord;
	}

	@Override
	public void insert(Ordem obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO os(id_cliente,"
															+ "id_orcamento, "
															+ "descr, "
															+ "data, "
															+ "data_baixa, "
															+ "obs, "
															+ "baixada) VALUES (?, ?, ?, ?, ?, ?, ?)");
			st.setInt(1, obj.getId_cliente());
			st.setInt(2, obj.getId_orcamento());
			st.setString(3, obj.getDescr());
			st.setDate(4, Tools.convertUtilToSql(obj.getData()));
			st.setDate(5, Tools.convertUtilToSql(obj.getData_baixa()));
			st.setString(6, obj.getObs());
			st.setString(7, obj.getBaixada());
			
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
	public void update(Ordem obj) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("UPDATE os SET id_cliente = ?, "
															+ "id_orcamento = ?, "
															+ "descr = ?, "
															+ "data = ?, "
															+ "data_baixa = ?, "
															+ "obs = ?, "
															+ "baixada = ? WHERE id = ?");
			st.setInt(1, obj.getId_cliente());
			st.setInt(2, obj.getId_orcamento());
			st.setString(3, obj.getDescr());
			st.setDate(4, Tools.convertUtilToSql(obj.getData()));
			st.setDate(5, Tools.convertUtilToSql(obj.getData_baixa()));
			st.setString(6, obj.getObs());
			st.setString(7, obj.getBaixada());
			
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
			st = conn.prepareStatement("DELETE FROM clientes WHERE id = ?");
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
	public Ordem findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT os.* " + 
					"FROM os " + 
					"WHERE id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()) {
				Ordem os = instantiateOrdem(rs);
				return os;
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
	public List<Ordem> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT os.* " + 
					"FROM os " +
					"ORDER BY data DESC");
			
			rs = st.executeQuery();
			
			List<Ordem> list = new ArrayList<>();
			
			while (rs.next()) {
					
				Ordem os = instantiateOrdem(rs);
				list.add(os);
		
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
