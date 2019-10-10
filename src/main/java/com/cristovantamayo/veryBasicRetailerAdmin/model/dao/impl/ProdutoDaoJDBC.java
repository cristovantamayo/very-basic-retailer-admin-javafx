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
import com.cristovantamayo.veryBasicRetailerAdmin.model.entities.Produto;
import com.cristovantamayo.veryBasicRetailerAdmin.model.entities.enums.TipoProduto;

public class ProdutoDaoJDBC implements Dao<Produto> {
	
	private Connection conn;
	
	public ProdutoDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	private Produto instantiateProduto(ResultSet rs) throws SQLException {
		Produto prod = new Produto();
		prod.setId(rs.getInt("id"));
		prod.setNome(rs.getString("nome"));
		prod.setPreco(rs.getDouble("preco"));
		prod.setPrecoCompra(rs.getDouble("precocompra"));
		prod.setCodigodebarras(rs.getString("codigodebarras"));
		prod.setCodigoproduto(rs.getString("codigoproduto"));
		prod.setEmEstoque(rs.getInt("estoque"));
		prod.setTipo((TipoProduto) rs.getObject("tipo"));
		return prod;
	}

	@Override
	public void insert(Produto obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO produtos (nome,"
															+ "preco, "
															+ "precocompra, "
															+ "codigodebarras, "
															+ "codigoproduto, "
															+ "estoque, "
															+ "tipo, "
															+ ") VALUES (?, ?, ?, ?, ?, ?, ?)");
			st.setString(1, obj.getNome());
			st.setDouble(2, obj.getPreco());
			st.setDouble(3, obj.getPrecoCompra());
			st.setString(4, obj.getCodigodebarras());
			st.setString(5, obj.getCodigoproduto());
			st.setInt(6, obj.getEmEstoque());
			st.setObject(7, (TipoProduto) obj.getTipo());
			
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
	public void update(Produto obj) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("UPDATE department SET nome = ?, "
															+ "preco = ?, "
															+ "precocompra = ?, "
															+ "codigodebarras = ?, "
															+ "codigoProduto = ?, "
															+ "estoque = ?, "
															+ "tipo = ? WHERE id = ?");
			st.setString(1, obj.getNome());
			st.setDouble(2, obj.getPreco());
			st.setDouble(3, obj.getPrecoCompra());
			st.setString(4, obj.getCodigodebarras());
			st.setString(5, obj.getCodigoproduto());
			st.setInt(6, obj.getEmEstoque());
			st.setObject(7, (TipoProduto) obj.getTipo());
			st.setInt(8, obj.getId());
			
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
			st = conn.prepareStatement("DELETE FROM produtos WHERE id = ?");
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
	public Produto findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT produtos.* " + 
					"FROM produto " + 
					"WHERE produtos.id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()) {
				Produto prod = instantiateProduto(rs);
				return prod;
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
	public List<Produto> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT produtos.* " + 
					"FROM produtos " +
					"ORDER BY produtos.nome ASC");
			
			rs = st.executeQuery();
			
			List<Produto> list = new ArrayList<>();
			
			while (rs.next()) {
					
				Produto prod = instantiateProduto(rs);
				list.add(prod);
		
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
