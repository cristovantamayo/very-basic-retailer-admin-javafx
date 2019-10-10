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
import com.cristovantamayo.veryBasicRetailerAdmin.model.entities.Campanha;
import com.cristovantamayo.veryBasicRetailerAdmin.utils.Tools;

public class CampanhaDaoJDBC implements Dao<Campanha> {
	
	private Connection conn;
	
	public CampanhaDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	private Campanha instantiateCampanha(ResultSet rs) throws SQLException {
		Campanha camp = new Campanha();
		camp.setId(rs.getInt("id"));
		camp.setDescricao(rs.getString("descricao"));
		camp.setAjustePercentual(rs.getDouble("ajuste_percentual"));
		camp.setFluxo(rs.getInt("fluxo"));
		camp.setDataIn(Tools.convertUtilToSql(rs.getDate("data_in")));
		camp.setDataOut(Tools.convertUtilToSql(rs.getDate("data_out")));
		return camp;
	}

	@Override
	public void insert(Campanha obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO campanhas(descricao,"
															+ "ajuste_percentual, "
															+ "fluxo, "
															+ "data_in, "
															+ "data_out "
															+ ") VALUES (?, ?, ?, ?, ?)");
			st.setString(1, obj.getDescricao());
			st.setDouble(2, obj.getAjustePercentual());
			st.setInt(3, obj.getFluxo());
			st.setDate(4, Tools.convertUtilToSql(obj.getDataIn()));
			st.setDate(5, Tools.convertUtilToSql(obj.getDataOut()));
			
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
	public void update(Campanha obj) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("UPDATE campanhas SET descricao = ?, "
															+ "ajuste_percentual = ?, "
															+ "fluxo = ?, "
															+ "data_in = ?, "
															+ "data_out = ? WHERE id = ?");
			st.setString(1, obj.getDescricao());
			st.setDouble(2, obj.getAjustePercentual());
			st.setInt(3, obj.getFluxo());
			st.setDate(4, Tools.convertUtilToSql(obj.getDataIn()));
			st.setDate(5, Tools.convertUtilToSql(obj.getDataOut()));
			
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
			st = conn.prepareStatement("DELETE FROM campanhas WHERE id = ?");
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
	public Campanha findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT campanhas.* " + 
					"FROM campanhas " + 
					"WHERE id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()) {
				Campanha camp = instantiateCampanha(rs);
				return camp;
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
	public List<Campanha> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT campanhas.* " + 
					"FROM campanhas " +
					"ORDER BY dataIn DESC");
			
			rs = st.executeQuery();
			
			List<Campanha> list = new ArrayList<>();
			
			while (rs.next()) {
					
				Campanha camp = instantiateCampanha(rs);
				list.add(camp);
		
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
