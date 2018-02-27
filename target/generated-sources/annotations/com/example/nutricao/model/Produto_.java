package com.example.nutricao.model;

import com.example.nutricao.utils.UnidadeMedidas;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Produto.class)
public abstract class Produto_ {

	public static volatile SingularAttribute<Produto, Double> fibras;
	public static volatile SingularAttribute<Produto, Double> A;
	public static volatile SingularAttribute<Produto, Double> C;
	public static volatile SingularAttribute<Produto, UnidadeMedidas> unidadeMedidas;
	public static volatile SingularAttribute<Produto, Boolean> editavel;
	public static volatile SingularAttribute<Produto, Double> carboidratos;
	public static volatile SingularAttribute<Produto, Double> colesterol;
	public static volatile SingularAttribute<Produto, Double> valor;
	public static volatile SingularAttribute<Produto, Double> proteinas;
	public static volatile SingularAttribute<Produto, String> nome;
	public static volatile SingularAttribute<Produto, Double> K;
	public static volatile SingularAttribute<Produto, Double> Na;
	public static volatile SingularAttribute<Produto, Double> Zn;
	public static volatile SetAttribute<Produto, Receita> receitas;
	public static volatile SingularAttribute<Produto, Double> Mg;
	public static volatile SingularAttribute<Produto, Integer> id;
	public static volatile SingularAttribute<Produto, Integer> quantidade;
	public static volatile SingularAttribute<Produto, Double> lipidios;
	public static volatile SingularAttribute<Produto, Double> Ca;
	public static volatile SingularAttribute<Produto, String> unidadeDeMedida;
	public static volatile SingularAttribute<Produto, Double> Fe;

}

