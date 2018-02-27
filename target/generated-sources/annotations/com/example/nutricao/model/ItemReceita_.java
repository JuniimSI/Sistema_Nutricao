package com.example.nutricao.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ItemReceita.class)
public abstract class ItemReceita_ {

	public static volatile SingularAttribute<ItemReceita, Integer> id_receita;
	public static volatile SingularAttribute<ItemReceita, String> unidadeMedida;
	public static volatile SingularAttribute<ItemReceita, Integer> id;
	public static volatile SingularAttribute<ItemReceita, Double> preco_individual;
	public static volatile SingularAttribute<ItemReceita, String> nomeProduto;
	public static volatile SingularAttribute<ItemReceita, Double> qtd_usada;

}

