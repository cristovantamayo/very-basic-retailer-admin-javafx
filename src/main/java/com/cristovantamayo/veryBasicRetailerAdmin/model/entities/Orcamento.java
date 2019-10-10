package com.cristovantamayo.veryBasicRetailerAdmin.model.entities;

import java.io.Serializable;
import java.sql.Date;

public class Orcamento implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private int qtd_produtos;
	private int id_cliente;
	private Date data;
	private Double valor;
	private Double desconto;
	private String baixado;
	private Integer vezes;;
	
	public Orcamento() {
	}

	public Orcamento(int id, int qtd_produtos, int id_cliente, Date data, Double valor, Double desconto, 
			String baixado, Integer vezes) {
		this.id = id;
		this.qtd_produtos = qtd_produtos;
		this.id_cliente = id_cliente;
		this.data = data;
		this.valor = valor;
		this.desconto = desconto;
		this.baixado = baixado;
		this.vezes = vezes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQtd_produtos() {
		return qtd_produtos;
	}

	public void setQtd_produtos(int qtd_produtos) {
		this.qtd_produtos = qtd_produtos;
	}

	public int getCodigo_cliente() {
		return id_cliente;
	}

	public void setCodigo_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public String getBaixado() {
		return baixado;
	}

	public void setBaixado(String baixado) {
		this.baixado = baixado;
	}

	public Integer getVezes() {
		return vezes;
	}

	public void setVezes(Integer vezes) {
		this.vezes = vezes;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Orcamento [id=" + id + ", qtd_produtos=" + qtd_produtos + ", data=" + data + ", valor=" + valor
				+ ", desconto=" + desconto + ", baixado=" + baixado + ", vezes=" + vezes + "]";
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
		Orcamento other = (Orcamento) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
