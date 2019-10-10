package com.cristovantamayo.veryBasicRetailerAdmin.model.entities;

import java.io.Serializable;
import java.sql.Date;

public class LancamentoCaixa implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer idPagamento;
	private Integer idCaixa;
	private String descricao;
	private Double credito;
	private Double debito;
	private Date data;
	
	public LancamentoCaixa() {
		
	}

	public LancamentoCaixa(Integer id, Integer idPagamento, Integer idCaixa, String descricao, Double credito,
			Double debito, Date data) {
		this.id = id;
		this.idPagamento = idPagamento;
		this.idCaixa = idCaixa;
		this.descricao = descricao;
		this.credito = credito;
		this.debito = debito;
		this.data = data;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getIdPagamento() {
		return idPagamento;
	}

	public void setIdPagamento(Integer idPagamento) {
		this.idPagamento = idPagamento;
	}

	public Integer getIdCaixa() {
		return idCaixa;
	}

	public void setIdCaixa(Integer idCaixa) {
		this.idCaixa = idCaixa;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getCredito() {
		return credito;
	}

	public void setCredito(Double credito) {
		this.credito = credito;
	}

	public Double getDebito() {
		return debito;
	}

	public void setDebito(Double debito) {
		this.debito = debito;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "LancamentosCaixa [id=" + id + ", idPagamento=" + idPagamento + ", idCaixa=" + idCaixa + ", descricao="
				+ descricao + ", credito=" + credito + ", debito=" + debito + ", data=" + data + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		LancamentoCaixa other = (LancamentoCaixa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
