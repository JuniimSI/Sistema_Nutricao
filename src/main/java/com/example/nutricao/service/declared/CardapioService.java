package com.example.nutricao.service.declared;

import java.util.List;

import com.example.nutricao.model.Cardapio;
import com.example.nutricao.model.Escola;
import com.example.nutricao.model.Programa;
import com.example.nutricao.model.Receita;

public interface CardapioService {
	
	public List<Cardapio> getAll();
	public void cadastrar(Cardapio cardapio);
	public Cardapio getById(Integer id);
	public void desabilita(Cardapio cardapio);
	public void excluir(Cardapio cardapio);
	public List<Cardapio> buscarPorContemNome(String nome);
	public void excluirCardapioPrograma(Escola escola, Cardapio cardapio, Programa programa);
	public Cardapio retornaCardapioEscolaPrograma(Integer escola, Integer programa);
	
	public void adicionaReceitaCardapioSegunda(Escola escola, Programa programa, Cardapio cardapio, Receita receita);
	public void adicionaReceitaCardapioTerca( Escola escola, Programa programa, Cardapio cardapio, Receita receita);
	public void adicionaReceitaCardapioQuarta( Escola escola, Programa programa, Cardapio cardapio, Receita receita);
	public void adicionaReceitaCardapioQuinta( Escola escola, Programa programa, Cardapio cardapio, Receita receita);
	public void adicionaReceitaCardapioSexta( Escola escola, Programa programa, Cardapio cardapio, Receita receita);
	
	public void adicionaReceitaCardapioSegundaSimple( Cardapio cardapio, Receita receita);
	public void adicionaReceitaCardapioTercaSimple( Cardapio cardapio, Receita receita);
	public void adicionaReceitaCardapioQuartaSimple( Cardapio cardapio, Receita receita);
	public void adicionaReceitaCardapioQuintaSimple( Cardapio cardapio, Receita receita);
	public void adicionaReceitaCardapioSextaSimple(Cardapio cardapio, Receita receita);
	
	public void excluiReceitaCardapioSegunda( Escola escola, Programa programa, Cardapio cardapio, Receita receita);
	public void excluiReceitaCardapioTerca( Escola escola, Programa programa, Cardapio cardapio, Receita receita);
	public void excluiReceitaCardapioQuarta( Escola escola, Programa programa, Cardapio cardapio, Receita receita);
	public void excluiReceitaCardapioQuinta( Escola escola, Programa programa, Cardapio cardapio, Receita receita);
	public void excluiReceitaCardapioSexta( Escola escola, Programa programa, Cardapio cardapio, Receita receita);
	
	public void excluiReceitaCardapioSegundaSimple( Cardapio cardapio, Receita receita);
	public void excluiReceitaCardapioTercaSimple( Cardapio cardapio, Receita receita);
	public void excluiReceitaCardapioQuartaSimple( Cardapio cardapio, Receita receita);
	public void excluiReceitaCardapioQuintaSimple( Cardapio cardapio, Receita receita);
	public void excluiReceitaCardapioSextaSimple( Cardapio cardapio, Receita receita);
	
	
	
}
