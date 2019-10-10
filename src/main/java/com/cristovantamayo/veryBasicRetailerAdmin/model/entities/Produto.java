package com.cristovantamayo.veryBasicRetailerAdmin.model.entities;

import java.io.Serializable;

import com.cristovantamayo.veryBasicRetailerAdmin.model.entities.enums.TipoProduto;

public class Produto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private Double preco;
	private Double precoCompra;
	private String codigodebarras;
	private String codigoproduto;
	private Integer emEstoque;
	private TipoProduto tipo;
	
	public Produto() {
	}

	public Produto(int id, String nome, Double preco, Double precoCompra,
			Integer emEstoque, String codigodebarras, String codigoproduto, TipoProduto tipo) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.precoCompra = precoCompra;
		this.codigodebarras = codigodebarras;
		this.codigoproduto = codigoproduto;
		this.emEstoque = emEstoque;
		this.tipo = tipo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Double getPrecoCompra() {
		return precoCompra;
	}

	public void setPrecoCompra(Double precoCompra) {
		this.precoCompra = precoCompra;
	}

	public String getCodigodebarras() {
		return codigodebarras;
	}

	public Integer getEmEstoque() {
		return emEstoque;
	}

	public void setEmEstoque(Integer emEstoque) {
		this.emEstoque = emEstoque;
	}

	public void setCodigodebarras(String codigodebarras) {
		this.codigodebarras = codigodebarras;
	}

	public String getCodigoproduto() {
		return codigoproduto;
	}

	public void setCodigoproduto(String codigoproduto) {
		this.codigoproduto = codigoproduto;
	}

	public TipoProduto getTipo() {
		return tipo;
	}

	public void setTipo(TipoProduto tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Produtos [id=" + id + ", nome=" + nome + ", preco=" + preco + ", "
				+ "precoCompra=" + precoCompra + ", tipo=" + tipo + "emEstoque=" + emEstoque + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
