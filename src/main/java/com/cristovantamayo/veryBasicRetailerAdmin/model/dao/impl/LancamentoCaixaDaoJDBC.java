package com.cristovantamayo.veryBasicRetailerAdmin.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cristovantamayo.veryBasicRetailerAdmin.db.DB;
import com.cristovantamayo.veryBasicRetailerAdmin.db.DbException;
import com.cristovantamayo.veryBasicRetailerAdmin.model.dao.LancamentoCaixaDao;
import com.cristovantamayo.veryBasicRetailerAdmin.model.entities.LancamentoCaixa;

public class LancamentoCaixaDaoJDBC implements LancamentoCaixaDao {
	
	private Connection conn;
	
	public LancamentoCaixaDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	private LancamentoCaixa instantiateLancamentoCaixa(ResultSet rs) throws SQLException {
		LancamentoCaixa lanc = new LancamentoCaixa();
		lanc.setId(rs.getInt("id"));
		lanc.setIdPagamento(rs.getInt("id_pagamento"));
		lanc.setIdCaixa(rs.getInt("id_caixa"));
		lanc.setDescricao(rs.getString("descricao"));
		lanc.setCredito(rs.getDouble("credito"));
		lanc.setDebito(rs.getDouble("debito"));
		lanc.setData(rs.getDate("data"));
		return lanc;
	}

	@Override
	public void insert(LancamentoCaixa obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO caixa_lancamentos(id_pagamento,"
															+ "id_caixa, "
															+ "descricao, "
															+ "credito, "
															+ "debito, "
															+ "data "
															+ ") VALUES (?, ?, ?, ?, ?, ?)");
			st.setInt(1, obj.getIdPagamento());
			st.setInt(2, obj.getIdCaixa());
			st.setString(3, obj.getDescricao());
			st.setDouble(4, obj.getCredito());
			st.setDouble(5, obj.getDebito());
			st.setDate(6, obj.getData());
			
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
	public void update(LancamentoCaixa obj) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("UPDATE caixa_lancamentos SET id_pagamento = ?, "
															+ "id_caixa = ?, "
															+ "descricao = ?, "
															+ "endereco = ?, "
															+ "credito = ?, "
															+ "debito = ? "
															+ "data = ? WHERE id = ?");
			st.setInt(1, obj.getIdPagamento());
			st.setInt(2, obj.getIdCaixa());
			st.setString(3, obj.getDescricao());
			st.setDouble(4, obj.getCredito());
			st.setDouble(5, obj.getDebito());
			st.setDate(6, obj.getData());
			
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
			st = conn.prepareStatement("DELETE FROM caixa_lancamentos WHERE id = ?");
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
	public LancamentoCaixa findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT caixa_lancamentos.* " + 
					"FROM caixa_lancamentos " + 
					"WHERE id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()) {
				LancamentoCaixa lanc = instantiateLancamentoCaixa(rs);
				return lanc;
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
	public List<LancamentoCaixa> findAll(Integer idCaixa) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT caixa_lancamentos.* " + 
					"FROM caixa_lancamentos " +
					"WHERE id_caixa = ?" +
					"ORDER BY data DESC");
			st.setInt(1, idCaixa);
			rs = st.executeQuery();
			
			List<LancamentoCaixa> list = new ArrayList<>();
			
			while (rs.next()) {
					
				LancamentoCaixa lanc = instantiateLancamentoCaixa(rs);
				list.add(lanc);
		
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
