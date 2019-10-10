package com.cristovantamayo.veryBasicRetailerAdmin.model.entities;

import java.io.Serializable;
import java.util.Date;

public class Ordem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private int id_cliente;
	private int id_orcamento;
	private String descr;
	private Date data;
	private Date data_baixa;
	private String baixada;
	private String obs;
	
	public Ordem() {
	}

	public Ordem(int id, int id_cliente, int id_orcamento, String descr, Date data, Date data_baixa, 
			String baixada, String obs) {
		this.id = id;
		this.id_cliente = id_cliente;
		this.id_orcamento = id_orcamento;
		this.descr = descr;
		this.data = data;
		this.data_baixa = data_baixa;
		this.baixada = baixada;
		this.obs = obs;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}

	public int getId_orcamento() {
		return id_orcamento;
	}

	public void setId_orcamento(int id_orcamento) {
		this.id_orcamento = id_orcamento;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getData_baixa() {
		return data_baixa;
	}

	public void setData_baixa(Date data_baixa) {
		this.data_baixa = data_baixa;
	}

	public String getBaixada() {
		return baixada;
	}

	public void setBaixada(String baixada) {
		this.baixada = baixada;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	@Override
	public String toString() {
		return "Ordem [id=" + id + ", descr=" + descr + ", data=" + data + ", baixada=" + baixada + "]";
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
		Ordem other = (Ordem) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
