package com.example.nutricao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nutricao.model.Produto;
import com.example.nutricao.repository.ProdutoRepository;
import com.example.nutricao.service.declared.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Override
	public List<Produto> getAll() {
		return produtoRepository.findAll();
	}

	@Override
	public void desabilita(Produto produto) {
		produto.setEditavel(false);
		cadastrar(produto);
	}
	
	@Override
	public void cadastrar(Produto produto) {
		produtoRepository.save(produto);
	}

	@Override
	public Produto getById(Integer id) {
		return produtoRepository.findOne(id);
	}

	@Override
	public void excluir(Produto produto) {
		produtoRepository.delete(produto);
	}

	@Override
	public Double buscarPrecoPorNome(String nome) {
		Double p = produtoRepository.getValorByNome(nome);
		System.out.println(p);
		return p;
	}
	
	@Override
	public List<Produto> buscarPorContemNome(String nome) {
		return produtoRepository.getDistinctByNomeStartingWithIgnoreCase(nome);
	}

	@Override
	public Produto getByName(String nome) {
		Produto p = produtoRepository.getByNome(nome);
		return p;
	}

	
}
