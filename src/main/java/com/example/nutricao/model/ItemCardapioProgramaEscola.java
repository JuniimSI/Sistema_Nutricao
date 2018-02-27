package com.example.nutricao.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ItemCardapioProgramaEscola {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer escolaId;
	
	private Integer programaId;
	
	private Integer cardapioId;

	public ItemCardapioProgramaEscola() {
		super();
	}

	public ItemCardapioProgramaEscola(Integer id, Integer escolaId, Integer programaId, Integer cardapioId) {
		super();
		this.id = id;
		this.escolaId = escolaId;
		this.programaId = programaId;
		this.cardapioId = cardapioId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEscolaId() {
		return escolaId;
	}

	public void setEscolaId(Integer escolaId) {
		this.escolaId = escolaId;
	}

	public Integer getProgramaId() {
		return programaId;
	}

	public void setProgramaId(Integer programaId) {
		this.programaId = programaId;
	}

	public Integer getCardapioId() {
		return cardapioId;
	}

	public void setCardapioId(Integer cardapioId) {
		this.cardapioId = cardapioId;
	}
	
	
}
