package com.example.nutricao.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Escola.class)
public abstract class Escola_ {

	public static volatile SingularAttribute<Escola, String> telefone;
	public static volatile SingularAttribute<Escola, String> endereco;
	public static volatile ListAttribute<Escola, Programa> programas;
	public static volatile SingularAttribute<Escola, String> nome;
	public static volatile SingularAttribute<Escola, Integer> id;

}

