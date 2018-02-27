package com.example.nutricao.model;

public class Custos {

	private String nomeProduto;
	private double qtdUtilizada;
	private double precoProduto;
	
	
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public double getQtdUtilizada() {
		return qtdUtilizada;
	}
	public void setQtdUtilizada(double qtdUtilizada) {
		this.qtdUtilizada = qtdUtilizada;
	}
	public Custos(String nomeProduto, double qtdUtilizada, double precoProduto) {
		super();
		this.nomeProduto = nomeProduto;
		this.qtdUtilizada = qtdUtilizada;
		this.precoProduto = precoProduto;
	}
	public Custos() {
		super();
	}
	public double getPrecoProduto() {
		return precoProduto;
	}
	public void setPrecoProduto(double precoProduto) {
		this.precoProduto = precoProduto;
	}
	
}
