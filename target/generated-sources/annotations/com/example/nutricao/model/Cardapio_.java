package com.example.nutricao.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Cardapio.class)
public abstract class Cardapio_ {

	public static volatile ListAttribute<Cardapio, Receita> quarta;
	public static volatile ListAttribute<Cardapio, Receita> segunda;
	public static volatile ListAttribute<Cardapio, Receita> quinta;
	public static volatile SingularAttribute<Cardapio, Boolean> editavel;
	public static volatile ListAttribute<Cardapio, Receita> sexta;
	public static volatile ListAttribute<Cardapio, Programa> programa;
	public static volatile SingularAttribute<Cardapio, String> nome;
	public static volatile SingularAttribute<Cardapio, Integer> id;
	public static volatile SingularAttribute<Cardapio, String> turno;
	public static volatile ListAttribute<Cardapio, Receita> terca;

}

