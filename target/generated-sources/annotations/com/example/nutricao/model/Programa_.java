package com.example.nutricao.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Programa.class)
public abstract class Programa_ {

	public static volatile ListAttribute<Programa, Cardapio> cardapios;
	public static volatile SingularAttribute<Programa, String> telefone;
	public static volatile SetAttribute<Programa, Escola> escola;
	public static volatile SingularAttribute<Programa, Boolean> editavel;
	public static volatile SingularAttribute<Programa, Long> qtd_alunos;
	public static volatile ListAttribute<Programa, ItemCardapioProgramaEscola> itemPrograma;
	public static volatile SingularAttribute<Programa, String> nome;
	public static volatile SingularAttribute<Programa, Integer> id;

}

