package com.example.nutricao.model;


import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import com.example.nutricao.utils.UnidadeMedidas;

@Entity
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	@Transient
	private Double taxaConversao;
	private Double valor;
	private Integer quantidade;
	private String unidadeDeMedida;
	@Enumerated(EnumType.STRING)
	private UnidadeMedidas unidadeMedidas;
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
	@ManyToMany(mappedBy = "produtos")
	private Set<Receita> receitas;	
	private boolean editavel;
	
	public Produto() {
		super();
		taxaConversao = 1.00;
		editavel = true;
	}

	public Produto converteInformacoesNutricionais(Double valorUnitario) {
		Produto p = new Produto();
		p.setNome(this.nome);
		p.setReceitas(this.getReceitas());
		p.setUnidadeMedidas(this.unidadeMedidas);
		p.setValor(this.valor);
		p.setQuantidade(this.quantidade);
		p.setCarboidratos(valorPosPorcentagem(regraDe3(valorUnitario, 1.0), this.carboidratos));
		System.out.println(p.getCarboidratos() + "converteinformçõesnutricionais");
		p.setFibras(valorPosPorcentagem(regraDe3(valorUnitario, 1.0), this.fibras));
		p.setLipidios(valorPosPorcentagem(regraDe3(valorUnitario, 1.0), this.lipidios));
		p.setProteinas(valorPosPorcentagem(regraDe3(valorUnitario, 1.0), this.proteinas));
		p.setColesterol(valorPosPorcentagem(regraDe3(valorUnitario, 1.0), this.colesterol));
		p.setCa(valorPosPorcentagem(regraDe3(valorUnitario, 1.0), this.Ca));
		p.setZn(valorPosPorcentagem(regraDe3(valorUnitario, 1.0), this.Zn));
		p.setFe(valorPosPorcentagem(regraDe3(valorUnitario, 1.0), this.Fe));
		p.setK(valorPosPorcentagem(regraDe3(valorUnitario, 1.0), this.K));
		p.setNa(valorPosPorcentagem(regraDe3(valorUnitario, 1.0), this.Na));
		p.setMg(valorPosPorcentagem(regraDe3(valorUnitario, 1.0), this.Mg));
		p.setA(valorPosPorcentagem(regraDe3(valorUnitario, 1.0), this.A));
		p.setC(valorPosPorcentagem(regraDe3(valorUnitario, 1.0), this.C));
		return p;
	}
	
	public Double valorPosPorcentagem(Double porcentagem, Double campo) {
		return ((porcentagem/1.000)*campo)/100;
	}
	
	public Double regraDe3(Double valorDado, Double campo) {
		return (valorDado*100)/campo;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public String getUnidadeDeMedida() {
		return unidadeDeMedida;
	}
	public void setUnidadeDeMedida(String unidadeDeMedida) {
		this.unidadeDeMedida = unidadeDeMedida;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public UnidadeMedidas getUnidadeMedidas() {
		return unidadeMedidas;
	}
	public void setUnidadeMedidas(UnidadeMedidas unidadeMedidas) {
		this.unidadeMedidas = unidadeMedidas;
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

	public Double getTaxaConversao() {
		return taxaConversao;
	}

	public void setTaxaConversao(Double taxaConversao) {
		this.taxaConversao = taxaConversao;
	}

	@Override
	public String toString() {
		return "Produto [nome=" + nome + ", K=" + K + ", A=" + A + ", C=" + C + "]";
	}

	public Set<Receita> getReceitas() {
		return receitas;
	}

	public void setReceitas(Set<Receita> receitas) {
		this.receitas = receitas;
	}

	public boolean isEditavel() {
		return editavel;
	}

	public void setEditavel(boolean editavel) {
		this.editavel = editavel;
	}

	public boolean verificaNulos() {
		if(this.A == null || this.C == null || this.Ca== null || this.carboidratos== null || this.colesterol== null || this.Fe== null || this.fibras== null || 
				this.K== null || this.lipidios== null || this.Mg== null || this.Na== null || this.nome== null || this.proteinas== null || this.valor==null || 
				this.quantidade== null || this.taxaConversao == null)
			return false;
		return true;
	}

}
