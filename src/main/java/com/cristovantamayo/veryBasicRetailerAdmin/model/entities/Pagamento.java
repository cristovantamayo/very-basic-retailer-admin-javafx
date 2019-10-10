package com.cristovantamayo.veryBasicRetailerAdmin.model.entities;

import java.io.Serializable;

public class Pagamento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private int id_orcamento;
	private int numero;
	private int id_modalidade;
	private Double taxa;
	private Double custo;
	private Double valor_cobrado;
	
	public Pagamento() {
		
	}

	public Pagamento(int id, int id_orcamento, int numero, int id_modalidade, Double taxa, Double custo,
			Double valor_cobrado) {
		this.id = id;
		this.id_orcamento = id_orcamento;
		this.numero = numero;
		this.id_modalidade = id_modalidade;
		this.taxa = taxa;
		this.custo = custo;
		this.valor_cobrado = valor_cobrado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_orcamento() {
		return id_orcamento;
	}

	public void setId_orcamento(int id_orcamento) {
		this.id_orcamento = id_orcamento;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getId_modalidade() {
		return id_modalidade;
	}

	public void setId_modalidade(int id_modalidade) {
		this.id_modalidade = id_modalidade;
	}

	public Double getTaxa() {
		return taxa;
	}

	public void setTaxa(Double taxa) {
		this.taxa = taxa;
	}

	public Double getCusto() {
		return custo;
	}

	public void setCusto(Double custo) {
		this.custo = custo;
	}

	public Double getValor_cobrado() {
		return valor_cobrado;
	}

	public void setValor_cobrado(Double valor_cobrado) {
		this.valor_cobrado = valor_cobrado;
	}

	@Override
	public String toString() {
		return "Pagamento [id=" + id + ", id_orcamento=" + id_orcamento + ", numero=" + numero
				+ ", id_modalidade=" + id_modalidade + ", taxa=" + taxa + ", custo=" + custo
				+ ", valor_cobrado=" + valor_cobrado + "]";
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
		Pagamento other = (Pagamento) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
