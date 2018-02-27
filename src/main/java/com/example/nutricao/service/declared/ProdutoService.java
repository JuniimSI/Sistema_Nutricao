package com.example.nutricao.service.declared;

import java.util.List;


import com.example.nutricao.model.Produto;

public interface ProdutoService {

	public List<Produto> getAll();

	public void cadastrar(Produto produto);

	public Produto getById(Integer id);

	public void excluir(Produto produto);

	public List<Produto> buscarPorContemNome(String nome);
	
	public Double buscarPrecoPorNome(String nome);
	
	public void desabilita(Produto produto);

	public Produto getByName(String nome);
}
