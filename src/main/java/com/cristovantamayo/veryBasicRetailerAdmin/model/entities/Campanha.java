package com.cristovantamayo.veryBasicRetailerAdmin.model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Campanha implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String descricao;
	private Double ajustePercentual;
	private int fluxo;
	private Date dataIn;
	private Date dataOut;
	private List<Categoria> categorias;
	
	public Campanha() {
	}

	public Campanha(int id, String descricao, Double ajustePercentual, int fluxo, Date dataIn, Date dataOut, List<Categoria> categorias) {
		this.id = id;
		this.descricao = descricao;
		this.ajustePercentual = ajustePercentual;
		this.fluxo = fluxo;
		this.dataIn = dataIn;
		this.dataOut = dataOut;
		this.categorias = categorias;
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

	public Double getAjustePercentual() {
		return ajustePercentual;
	}

	public void setAjustePercentual(Double ajustePercentual) {
		this.ajustePercentual = ajustePercentual;
	}

	public int getFluxo() {
		return fluxo;
	}

	public void setFluxo(int fluxo) {
		this.fluxo = fluxo;
	}

	public Date getDataIn() {
		return dataIn;
	}

	public void setDataIn(Date dataIn) {
		this.dataIn = dataIn;
	}

	public Date getDataOut() {
		return dataOut;
	}

	public void setDataOut(Date dataOut) {
		this.dataOut = dataOut;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	@Override
	public String toString() {
		return "Campanha [id=" + id + ", descricao=" + descricao + ", ajustePercentual=" + ajustePercentual
				+ ", fluxo=" + fluxo + ", dataIn=" + dataIn + ", dataOut=" + dataOut
				+ "]";
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
		Campanha other = (Campanha) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
