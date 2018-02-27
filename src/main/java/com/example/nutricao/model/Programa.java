package com.example.nutricao.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Programa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	
	private String telefone;
	
	private Long qtd_alunos;
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<Cardapio> cardapios;

	@ManyToMany(mappedBy = "programas")
	private Set<Escola> escola;
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
	private List<ItemCardapioProgramaEscola> itemPrograma;
	
	private boolean editavel;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Long getQtd_alunos() {
		return qtd_alunos;
	}

	public void setQtd_alunos(Long qtd_alunos) {
		this.qtd_alunos = qtd_alunos;
	}

	public List<Cardapio> getCardapios() {
		return cardapios;
	}

	public void setCardapios(List<Cardapio> cardapios) {
		this.cardapios = cardapios;
	}

	public Programa(Integer id, String nome, String telefone, Long qtd_alunos, List<Cardapio> cardapios,
			Set<Escola> escola) {
		super();
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.qtd_alunos = qtd_alunos;
		this.cardapios = cardapios;
		this.escola = escola;
	}

	public Programa() {
		super();
		editavel=true;
	}

	public Set<Escola> getEscola() {
		return escola;
	}

	public void setEscola(Set<Escola> escola) {
		this.escola = escola;
	}

	public void removeCardapio(Cardapio cardapio) {
		cardapios = null;		
	}
	
	public void removeItemCardapio(ItemCardapioProgramaEscola icpe) {
		itemPrograma = null;
	}

	public boolean isEditavel() {
		return editavel;
	}

	public void setEditavel(boolean editavel) {
		this.editavel = editavel;
	}

	public List<ItemCardapioProgramaEscola> getItemPrograma() {
		return itemPrograma;
	}

	public void setItemPrograma(List<ItemCardapioProgramaEscola> itemPrograma) {
		this.itemPrograma = itemPrograma;
	}
	
	
	
}
