package com.example.nutricao.utils;

public enum UnidadeMedidas {
	 KILO("Kg"), LITRO("L"), PACOTE("Pacote");
	    private String nome;

	    UnidadeMedidas(String nome){
	        this.nome = nome;
	    }

	    public String getNome(){
	        return nome;
	    }
}
