package com.cristovantamayo.veryBasicRetailerAdmin.model.entities;

import java.io.Serializable;
import java.util.Date;

public class Caixa implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Date data;
	private Double valorInicial;
	private Double creditos;
	private Double debitos;
	private Double total;
	
	public Caixa() {
	}

	public Caixa(int id, Date data, Double creditos, Double debitos, Double total, Double valorInicial) {
		this.id = id;
		this.data = data;
		this.creditos = creditos;
		this.debitos = debitos;
		this.total = total;
		this.valorInicial = valorInicial;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Double getCreditos() {
		return creditos;
	}

	public void setCreditos(Double creditos) {
		this.creditos = creditos;
	}

	public Double getDebitos() {
		return debitos;
	}

	public void setDebitos(Double debitos) {
		this.debitos = debitos;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getValorInicial() {
		return valorInicial;
	}

	public void setValorInicial(Double valorInicial) {
		this.valorInicial = valorInicial;
	}

	@Override
	public String toString() {
		return "Caixa [id=" + id + ", data=" + data  + ", valorInicial="
				+ valorInicial + ", creditos=" + creditos + ", debitos=" + debitos + ", total=" + total + "]";
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
		Caixa other = (Caixa) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
