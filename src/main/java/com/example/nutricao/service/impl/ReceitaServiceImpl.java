package com.example.nutricao.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nutricao.model.ItemReceita;
import com.example.nutricao.model.Produto;
import com.example.nutricao.model.Receita;
import com.example.nutricao.repository.ReceitaRepository;
import com.example.nutricao.service.declared.ProdutoService;
import com.example.nutricao.service.declared.ReceitaService;


@Service
public class ReceitaServiceImpl implements ReceitaService{

	@Autowired
	private ReceitaRepository receitaRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Override
	public List<Receita> getAll() {
		return receitaRepository.findAll();
	}

	@Override
	public void cadastrar(Receita receita) {
		receitaRepository.save(receita);
	}

	@Override
	public Receita getById(Integer id) {
		return receitaRepository.findOne(id);
	}

	@Override
	public void excluir(Receita receita) {
		receitaRepository.delete(receita);
	}

	@Override
	public void adicionaProdutoReceita(Receita receita, Produto produto, double valorParaConversao) {
		if(receita != null && produto!= null) {
			produtoService.desabilita(produto);
			List<ItemReceita> itemsReceita = receita.getItemsReceita();
			List<Produto> prods = receita.getProdutos();
			
			Produto ps = produto.converteInformacoesNutricionais(valorParaConversao);
			
			ItemReceita ir = new ItemReceita();
			ir.setPreco_individual(produto.getValor());
			ir.setId_receita(receita.getId());
			ir.setNomeProduto(produto.getNome());
			ir.setQtd_usada(valorParaConversao);
			ir.setUnidadeMedida(produto.getUnidadeMedidas().getNome());
			itemsReceita.add(ir);
			receita.setProdutos(prods);
			receita.setItemsReceita(itemsReceita);			
			receita.calculaInformacoesNutricioais(ps);
			receitaRepository.save(receita);
		}
	}

	@Override
	public void excluirProdutoReceita(Receita receita, ItemReceita itemReceita, double valorParaConversao) {
		if (receita != null && itemReceita != null) {
			receita.removeItemReceita(itemReceita);
			List<Produto> produtos = new ArrayList<Produto>();
			for(ItemReceita ir: receita.getItemsReceita()) {
				produtos.add(produtoService.getByName(ir.getNomeProduto()));
			}
			for(Produto p: produtos)
				System.out.println(p.getNome());
			
			Produto p2 = produtoService.getByName(itemReceita.getNomeProduto());
			Receita nova = receita.calculaInformacoesNutricioaisMenos(receita, p2, valorParaConversao);
			this.cadastrar(nova);
		}
	}

	@Override
	public void desabilita(Receita receita) {
		receita.setEditavel(false);
		this.cadastrar(receita);
	}
	
}
