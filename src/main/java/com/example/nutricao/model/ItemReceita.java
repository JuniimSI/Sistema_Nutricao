package com.example.nutricao.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ItemReceita {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	public String nomeProduto;
	
	private String unidadeMedida;
	
	private Integer id_receita;
	
	private double qtd_usada;
	
	private double preco_individual;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ItemReceita() {
		super();
	}

	public double getQtd_usada() {
		return qtd_usada;
	}

	public void setQtd_usada(double qtd_usada) {
		this.qtd_usada = qtd_usada;
	}



	public String getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}


	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public Integer getId_receita() {
		return id_receita;
	}

	public void setId_receita(Integer id_receita) {
		this.id_receita = id_receita;
	}

	public double getPreco_individual() {
		return preco_individual;
	}

	public void setPreco_individual(double preco_individual) {
		this.preco_individual = preco_individual;
	}
	
	
	
}
