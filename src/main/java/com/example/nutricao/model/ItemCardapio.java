package com.example.nutricao.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ItemCardapio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	
	@ManyToOne
	private Receita primeira;
	
	@ManyToOne
	private Receita segunda;
	
	@ManyToOne
	private Receita terceira;
	
	@ManyToOne
	private Receita quarta;

	public List<Receita> getReceitas(){
		List<Receita> segundas = new ArrayList<>();
		segundas.add(primeira);
		segundas.add(segunda);
		segundas.add(terceira);
		segundas.add(quarta);
		return segundas;
	}
	
	public Receita getPrimeira() {
		return primeira;
	}

	public void setPrimeira(Receita primeira) {
		this.primeira = primeira;
	}

	public Receita getSegunda() {
		return segunda;
	}

	public void setSegunda(Receita segunda) {
		this.segunda = segunda;
	}

	public Receita getTerceira() {
		return terceira;
	}

	public void setTerceira(Receita terceira) {
		this.terceira = terceira;
	}

	public Receita getQuarta() {
		return quarta;
	}

	public void setQuarta(Receita quarta) {
		this.quarta = quarta;
	}
	
}
