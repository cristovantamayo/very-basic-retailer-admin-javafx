package com.cristovantamayo.veryBasicRetailerAdmin.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cristovantamayo.veryBasicRetailerAdmin.db.DB;
import com.cristovantamayo.veryBasicRetailerAdmin.db.DbException;
import com.cristovantamayo.veryBasicRetailerAdmin.model.dao.ClienteDao;
import com.cristovantamayo.veryBasicRetailerAdmin.model.entities.Cliente;

public class ClienteDaoJDBC implements ClienteDao {
	
	private Connection conn;
	
	public ClienteDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	private Cliente instantiateCliente(ResultSet rs) throws SQLException {
		Cliente cli = new Cliente();
		cli.setId(rs.getInt("id"));
		cli.setNome(rs.getString("nome"));
		cli.setRg(rs.getString("rg"));
		cli.setCpf(rs.getString("cpf"));
		cli.setEndereco(rs.getString("endereco"));
		cli.setNumero(rs.getString("numero"));
		cli.setComplemento(rs.getString("complemento"));
		cli.setBairro(rs.getString("bairro"));
		cli.setCidade(rs.getString("cidade"));
		cli.setEstado(rs.getString("estado"));
		cli.setCep(rs.getString("cep"));
		cli.setTelefone(rs.getString("telefone"));
		cli.setCelular(rs.getString("celular"));
		cli.setEmail(rs.getString("email"));
		cli.setHistorico(rs.getString("historico"));
		return cli;
	}

	@Override
	public void insert(Cliente obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO clientes(nome,"
															+ "rg, "
															+ "cpf, "
															+ "endereco, "
															+ "numero, "
															+ "complemento, "
															+ "bairro, "
															+ "cidade, "
															+ "estado, "
															+ "cep, "
															+ "telefone, "
															+ "celular, "
															+ "email, "
															+ "historico, "
															+ "codStatus, "
															+ ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			st.setString(1, obj.getNome());
			st.setString(2, obj.getRg());
			st.setString(3, obj.getCpf());
			st.setString(4, obj.getEndereco());
			st.setString(5, obj.getNumero());
			st.setString(6, obj.getBairro());
			st.setString(7, obj.getCidade());
			st.setString(8, obj.getEstado());
			st.setString(9, obj.getCep());
			st.setString(10, obj.getTelefone());
			st.setString(11, obj.getCelular());
			st.setString(12, obj.getEmail());
			st.setString(13, obj.getHistorico());
			st.setInt(14, obj.getCodStatus());
			
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
	public void update(Cliente obj) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("UPDATE clientes SET name = ?, "
															+ "rg = ?, "
															+ "cpf = ?, "
															+ "endereco = ?, "
															+ "numero = ?, "
															+ "bairro = ?, "
															+ "cidade = ?, "
															+ "estado = ?, "
															+ "cep = ?, "
															+ "telefone = ?, "
															+ "celular = ?, "
															+ "email = ?, "
															+ "historico = ?, "
															+ "codStatus = ? WHERE id = ?");
			st.setString(1, obj.getNome());
			st.setString(2, obj.getRg());
			st.setString(3, obj.getCpf());
			st.setString(4, obj.getEndereco());
			st.setString(5, obj.getNumero());
			st.setString(6, obj.getBairro());
			st.setString(7, obj.getCidade());
			st.setString(8, obj.getEstado());
			st.setString(9, obj.getCep());
			st.setString(10, obj.getTelefone());
			st.setString(11, obj.getCelular());
			st.setString(12, obj.getEmail());
			st.setString(13, obj.getHistorico());
			st.setInt(14, obj.getCodStatus());
			
			st.setInt(15, obj.getId());
			
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
	public Cliente findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT clientes.* " + 
					"FROM clientes " + 
					"WHERE id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()) {
				Cliente cli = instantiateCliente(rs);
				return cli;
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
	public List<Cliente> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT * " + 
					"FROM clientes " +
					"ORDER BY nome");
			
			rs = st.executeQuery();
			
			List<Cliente> list = new ArrayList<>();
			
			while (rs.next()) {
				
				Cliente cli = instantiateCliente(rs);
				list.add(cli);
		
			}
			
			return list;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Cliente> findByName(String partial, Integer qtd) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT * " + 
					"FROM clientes " +
					"WHERE nome like ? " +
					"ORDER BY nome" + (qtd != null ? " LIMIT 0, ?" : "")
					);
			
			st.setString(1, '%'+partial+'%');
			if(qtd != null) { st.setInt(2, qtd); }
			
			rs = st.executeQuery();
			
			List<Cliente> list = new ArrayList<>();
			
			while (rs.next()) {
				
				Cliente cli = instantiateCliente(rs);
				list.add(cli);
		
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
