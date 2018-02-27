package com.example.nutricao.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Receita.class)
public abstract class Receita_ {

	public static volatile SingularAttribute<Receita, Double> fibras;
	public static volatile SingularAttribute<Receita, Double> A;
	public static volatile SingularAttribute<Receita, Double> C;
	public static volatile SingularAttribute<Receita, Boolean> editavel;
	public static volatile SingularAttribute<Receita, Double> carboidratos;
	public static volatile SingularAttribute<Receita, Double> colesterol;
	public static volatile SingularAttribute<Receita, Double> proteinas;
	public static volatile SingularAttribute<Receita, String> nome;
	public static volatile SingularAttribute<Receita, Double> K;
	public static volatile SingularAttribute<Receita, Double> Na;
	public static volatile SingularAttribute<Receita, Double> Zn;
	public static volatile ListAttribute<Receita, Produto> produtos;
	public static volatile SingularAttribute<Receita, Double> Mg;
	public static volatile SingularAttribute<Receita, Integer> id;
	public static volatile SingularAttribute<Receita, Double> lipidios;
	public static volatile ListAttribute<Receita, ItemReceita> itemsReceita;
	public static volatile SingularAttribute<Receita, StatusReceita> statusReceita;
	public static volatile SingularAttribute<Receita, Double> Ca;
	public static volatile SingularAttribute<Receita, Double> Fe;

}

