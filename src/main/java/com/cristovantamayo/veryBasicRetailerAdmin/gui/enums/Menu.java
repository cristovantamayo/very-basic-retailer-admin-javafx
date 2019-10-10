package com.cristovantamayo.veryBasicRetailerAdmin.gui.enums;

public enum Menu {
	ORDEM_DE_SERICOS(1, "Ordem de Serviço", "Ordens de Serviço","O.S.", "Green"),
	ORCAMENTOS(2, "Orçamento", "Orçamentos", "ORC", "Purple"),
	VENDAS(3, "Venda", "Vendas", "VND", "Blue"),
	CLIENTES(4, "Cliente", "Clientes", "CLI", "Red"),
	CAIXA(5, "Caixa", "Caixa", "CX", "Orange");
	
	private int id;
	private String singular;
	private String plural;
	private String sigla;
	private String cor;
	
	private Menu(int id, String singular, String plural, String sigla, String cor){
        this.id = id;
        this.singular = singular;
        this.plural = plural;
        this.sigla = sigla;
        this.cor = cor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSingular() {
		return singular;
	}

	public void setSingular(String singular) {
		this.singular = singular;
	}

	public String getPlural() {
		return plural;
	}

	public void setPlural(String plural) {
		this.plural = plural;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}
	
}
