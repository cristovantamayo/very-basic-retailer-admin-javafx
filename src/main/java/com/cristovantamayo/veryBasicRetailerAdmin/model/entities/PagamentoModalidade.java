package com.cristovantamayo.veryBasicRetailerAdmin.model.entities;

import java.io.Serializable;

public class PagamentoModalidade implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String descricao;
	private Double taxa;
	private Integer ordem;
	
	public PagamentoModalidade() {
	}

	public PagamentoModalidade(int id, String descricao, Double taxa, Integer ordem) {
		this.id = id;
		this.descricao = descricao;
		this.taxa = taxa;
		this.ordem = ordem;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getTaxa() {
		return taxa;
	}

	public void setTaxa(Double taxa) {
		this.taxa = taxa;
	}

	public Integer getOrdem() {
		return ordem;
	}

	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}

	@Override
	public String toString() {
		return "PagamentoModalidade [id=" + id + ", descricao=" + descricao + ", taxa=" + taxa + ", orde,=" + ordem + "]";
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
		PagamentoModalidade other = (PagamentoModalidade) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
