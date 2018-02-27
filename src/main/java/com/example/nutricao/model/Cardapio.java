package com.example.nutricao.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class Cardapio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String turno;

	private String nome;
	@ManyToMany
	private List<Receita> segunda;
	@ManyToMany
	private List<Receita> terca;
	@ManyToMany
	private List<Receita> quarta;
	@ManyToMany
	private List<Receita> quinta;
	@ManyToMany
	private List<Receita> sexta;
	@ManyToMany(mappedBy = "cardapios")
	private List<Programa> programa;

	private boolean editavel;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Receita> getSegunda() {
		return segunda;
	}

	public void setSegunda(List<Receita> segunda) {
		this.segunda = segunda;
	}

	public List<Receita> getTerca() {
		return terca;
	}

	public void setTerca(List<Receita> terca) {
		this.terca = terca;
	}

	public List<Receita> getQuarta() {
		return quarta;
	}

	public void setQuarta(List<Receita> quarta) {
		this.quarta = quarta;
	}

	public List<Receita> getQuinta() {
		return quinta;
	}

	public void setQuinta(List<Receita> quinta) {
		this.quinta = quinta;
	}

	public List<Receita> getSexta() {
		return sexta;
	}

	public void setSexta(List<Receita> sexta) {
		this.sexta = sexta;
	}

	public Cardapio(Integer id, String turno, String nome, List<Receita> segunda, List<Receita> terca,
			List<Receita> quarta, List<Receita> quinta, List<Receita> sexta, List<Programa> programa) {
		super();
		this.id = id;
		this.turno = turno;
		this.nome = nome;
		this.segunda = segunda;
		this.terca = terca;
		this.quarta = quarta;
		this.quinta = quinta;
		this.sexta = sexta;
		this.programa = programa;
	}

	public Cardapio() {
		super();
		editavel = true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public List<Programa> getPrograma() {
		return programa;
	}

	public void setPrograma(List<Programa> programa) {
		this.programa = programa;
	}

	public boolean isEditavel() {
		return editavel;
	}

	public void setEditavel(boolean editavel) {
		this.editavel = editavel;
	}

	public void addSegunda(Receita receita) {
		segunda.add(receita);
	}

	public void addTerca(Receita receita) {
		terca.add(receita);
	}

	public void addQuarta(Receita receita) {
		quarta.add(receita);
	}

	public void addQuinta(Receita receita) {
		quinta.add(receita);
	}

	public void addSexta(Receita receita) {
		sexta.add(receita);
	}

	public List<Custos> getCustosNaoOrganizados() {
		List<Custos> custos = new ArrayList<Custos>();
		if (segunda != null) {
			for (Receita r : segunda) {
				for (ItemReceita ir : r.getItemsReceita()) {
					custos.add(new Custos(ir.getNomeProduto(), ir.getQtd_usada(), ir.getPreco_individual()));
				}
			}
		}
		if (terca != null) {
			for (Receita r : terca) {
				for (ItemReceita ir : r.getItemsReceita()) {
					custos.add(new Custos(ir.getNomeProduto(), ir.getQtd_usada(), ir.getPreco_individual()));
				}
			}
		}
		if (quarta != null) {
			for (Receita r : quarta) {
				for (ItemReceita ir : r.getItemsReceita()) {
					custos.add(new Custos(ir.getNomeProduto(), ir.getQtd_usada(), ir.getPreco_individual()));
				}
			}
		}
		if (quinta != null) {
			for (Receita r : quinta) {
				for (ItemReceita ir : r.getItemsReceita()) {
					custos.add(new Custos(ir.getNomeProduto(), ir.getQtd_usada(), ir.getPreco_individual()));
				}
			}
		}
		if (sexta != null) {
			for (Receita r : sexta) {
				for (ItemReceita ir : r.getItemsReceita()) {
					custos.add(new Custos(ir.getNomeProduto(), ir.getQtd_usada(), ir.getPreco_individual()));
				}
			}
		}
		return custos;
	}

	public Integer contains(String nomeProduto, List<Custos> itens) {
		for (int i = 0; i < itens.size(); i++) {
			if (itens.get(i).getNomeProduto().equals(nomeProduto))
				return i;
		}
		return null;
	}

	public List<Custos> getCustosOrganizados(){	
		
		List<Custos> custos = this.getCustosNaoOrganizados();
		List<Custos> ret = new ArrayList<Custos>();
		Integer val = 0;
		
		for(int i = 0; i < custos.size(); i++){
			val = contains(custos.get(i).getNomeProduto(), ret);
			if(val!=null){
				ret.get(val).setQtdUtilizada(ret.get(val).getQtdUtilizada()+custos.get(i).getQtdUtilizada());
				ret.get(val).setPrecoProduto(ret.get(val).getQtdUtilizada()*custos.get(i).getPrecoProduto());
				continue;
			}else{
				ret.add(custos.get(i));
			}		
		}
		return ret;
	}
	
}
