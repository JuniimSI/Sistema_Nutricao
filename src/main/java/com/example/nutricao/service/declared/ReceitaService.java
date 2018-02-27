package com.example.nutricao.service.declared;

import java.util.List;

import com.example.nutricao.model.ItemReceita;
import com.example.nutricao.model.Produto;
import com.example.nutricao.model.Receita;

public interface ReceitaService {
	public List<Receita> getAll();

	public void cadastrar(Receita receita);

	public Receita getById(Integer id);

	public void excluir(Receita receita);

	public void desabilita(Receita receita);

	public void adicionaProdutoReceita(Receita receita, Produto produto, double valorParaConversao);

	public void excluirProdutoReceita(Receita receita, ItemReceita itemReceita, double valorParaConversao);
	
}
