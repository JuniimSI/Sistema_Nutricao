package com.example.nutricao.model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.nutricao.service.declared.ProdutoService;

@Entity
public class Receita {

	@Autowired
	@Transient
	private ProdutoService produtoService;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private Double carboidratos;
	private Double fibras;
	private Double lipidios;
	private Double proteinas;
	private Double colesterol;
	private Double Ca;
	private Double Zn;
	private Double Fe;
	private Double K;
	private Double Na;
	private Double Mg;
	private Double A;
	private Double C;
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "produtos")
	private List<Produto> produtos;
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
	@JoinTable(name = "itemsReceita")
	private List<ItemReceita> itemsReceita;
	@Enumerated(EnumType.STRING)
	private StatusReceita statusReceita;
	@Transient
	DecimalFormat formato = new DecimalFormat("#,###");
	
	private boolean editavel;
	
	
	public Receita() {
		super();
		this.carboidratos = 0.0;
		this.fibras = 0.0;
		this.lipidios = 0.0;
		this.proteinas = 0.0;
		this.colesterol = 0.0;
		this.Ca = 0.0;
		this.Zn = 0.0;
		this.Fe = 0.0;
		this.K = 0.0;
		this.Na = 0.0;
		this.Mg = 0.0;
		this.A = 0.0;
		this.C = 0.0;
		editavel = true;
	}

	public Receita(Integer id, String nome, List<Produto> produtos, Double carboidratos, Double fibras, Double lipidios,
			Double proteinas, Double colesterol, Double ca, Double zn, Double fe, Double k, Double na, Double mg,
			Double a, Double c) {
		super();
		this.id = id;
		this.nome = nome;
		this.produtos = produtos;
		this.carboidratos = carboidratos;
		this.fibras = fibras;
		this.lipidios = lipidios;
		this.proteinas = proteinas;
		this.colesterol = colesterol;
		this.Ca = ca;
		this.Zn = zn;
		this.Fe = fe;
		this.K = k;
		this.Na = na;
		this.Mg = mg;
		this.A = a;
		this.C = c;
	}

	public void calculaInformacoesNutricioais(Produto p) {
		this.setCarboidratos((this.getCarboidratos() + p.getCarboidratos()));
		this.setFibras(this.getFibras() + p.getFibras());
		this.setLipidios(this.getLipidios() + p.getLipidios());
		this.setProteinas(this.getProteinas() + p.getProteinas());
		this.setColesterol(this.getColesterol() + p.getColesterol());
		this.setCa(this.getCa() + p.getCa());
		this.setZn(this.getZn() + p.getZn());
		this.setFe(this.getFe() + p.getFe());
		this.setK(this.getK() + p.getK());
		this.setNa(this.getNa() + p.getNa());
		this.setMg(this.getMg() + p.getMg());
		this.setA(this.getA() + p.getA());
		this.setC(this.getC() + p.getC());
	}

	public Receita calculaInformacoesNutricioaisMenos(Receita receita, Produto ps, double qtd_usada) {
		Receita r = receita;
		r.setNome(this.getNome());
		r.setId(this.getId());
		r.setProdutos(this.getProdutos());
		r.setItemsReceita(this.getItemsReceita());
		if (receita.getItemsReceita().size() == 0) {
			r.setCarboidratos(0.0);
			r.setFibras(0.0);
			r.setLipidios(0.0);
			r.setProteinas(0.0);
			r.setColesterol(0.0);
			r.setCa(0.0);
			r.setZn(0.0);
			r.setFe(0.0);
			r.setK(0.0);
			r.setNa(0.0);
			r.setMg(0.0);
			r.setA(0.0);
			r.setC(0.0);
			return r;
		}
		if (receita.getItemsReceita() != null && receita.getItemsReceita().size() > 0) {
			Produto p = ps.converteInformacoesNutricionais(qtd_usada);
			r.setCarboidratos(r.getCarboidratos() - p.getCarboidratos());
			r.setFibras(r.getFibras() - p.getFibras());
			r.setLipidios(r.getLipidios() - p.getLipidios());
			r.setProteinas(r.getProteinas() - p.getProteinas());
			r.setColesterol(r.getColesterol() - p.getColesterol());
			r.setCa(r.getCa() - p.getCa());
			r.setZn(r.getZn() - p.getZn());
			r.setFe(r.getFe() - p.getFe());
			r.setK(r.getK() - p.getK());
			r.setNa(r.getNa() - p.getNa());
			r.setMg(r.getMg() - p.getMg());
			r.setA(r.getA() - p.getA());
			r.setC(r.getC() - p.getC());
		}
		return r;
	}

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

	public Double getCarboidratos() {
		return carboidratos;
	}

	public void setCarboidratos(Double carboidratos) {
		this.carboidratos = carboidratos;
	}

	public Double getFibras() {
		return fibras;
	}

	public void setFibras(Double fibras) {
		this.fibras = fibras;
	}

	public Double getLipidios() {
		return lipidios;
	}

	public void setLipidios(Double lipidios) {
		this.lipidios = lipidios;
	}

	public Double getProteinas() {
		return proteinas;
	}

	public void setProteinas(Double proteinas) {
		this.proteinas = proteinas;
	}

	public Double getColesterol() {
		return colesterol;
	}

	public void setColesterol(Double colesterol) {
		this.colesterol = colesterol;
	}

	public Double getCa() {
		return Ca;
	}

	public void setCa(Double ca) {
		Ca = ca;
	}

	public Double getZn() {
		return Zn;
	}

	public void setZn(Double zn) {
		Zn = zn;
	}

	public Double getFe() {
		return Fe;
	}

	public void setFe(Double fe) {
		Fe = fe;
	}

	public Double getK() {
		return K;
	}

	public void setK(Double k) {
		K = k;
	}

	public Double getNa() {
		return Na;
	}

	public void setNa(Double na) {
		Na = na;
	}

	public Double getMg() {
		return Mg;
	}

	public void setMg(Double mg) {
		Mg = mg;
	}

	public Double getA() {
		return A;
	}

	public void setA(Double a) {
		A = a;
	}

	public Double getC() {
		return C;
	}

	public void setC(Double c) {
		C = c;
	}

	public List<Produto> getProdutos() {
		List<Produto> lis = new ArrayList<Produto>();
		for (Produto p : produtos) {
			p.setTaxaConversao(1.0);
			lis.add(p);
		}
		return lis;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	@Override
	public String toString() {
		return "Receita [nome=" + nome + ", K=" + K + ", A=" + A + ", C=" + C + "]";
	}

	public StatusReceita getStatusReceita() {
		return statusReceita;
	}

	public void setStatusReceita(StatusReceita statusReceita) {
		this.statusReceita = statusReceita;
	}

	public Boolean addProduto(Produto produto) {
		if (this.produtos == null) {
			produtos = new ArrayList<Produto>();
		}
		if (produto != null && !this.produtos.contains(produto)) {
			this.produtos.add(produto);
			return true;
		}
		return false;

	}

	public List<ItemReceita> getItemsReceita() {
		return itemsReceita;
	}

	public void setItemsReceita(List<ItemReceita> itemsReceita) {
		this.itemsReceita = itemsReceita;
	}

	public void removeItemReceita(ItemReceita itemReceita) {
		if (this.itemsReceita == null) {
			itemsReceita = new ArrayList<ItemReceita>();
		}
		itemsReceita.remove(itemReceita);
	}

	public boolean isEditavel() {
		return editavel;
	}

	public void setEditavel(boolean editavel) {
		this.editavel = editavel;
	}

}
