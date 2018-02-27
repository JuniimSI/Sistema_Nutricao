package com.example.nutricao.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Escola {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	
	private String endereco;
	
	private String telefone;
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<Programa> programas;

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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public List<Programa> getProgramas() {
		return programas;
	}

	public void setProgramas(List<Programa> programas) {
		this.programas = programas;
	}

	public Escola() {
		super();
	}

	public Escola(Integer id, String nome, String endereco, String telefone, List<Programa> programas) {
		super();
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.telefone = telefone;
		this.programas = programas;
	}
	
	public void removePrograma(Programa programa) {
		if(this.programas == null) {
			programas = new ArrayList<Programa>();
		}
		programas.remove(programa);		
		this.setProgramas(programas);
	}
}
