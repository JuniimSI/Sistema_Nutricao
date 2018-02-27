package com.example.nutricao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nutricao.model.ItemCardapioProgramaEscola;
import com.example.nutricao.repository.ItemCardapioProgramaEscolaRepository;
import com.example.nutricao.service.declared.ItemCardapioProgramaEscolaService;

@Service
public class ItemCardapioProgramaEscolaServiceImpl implements ItemCardapioProgramaEscolaService{

	@Autowired
	private ItemCardapioProgramaEscolaRepository icpeRepository;
	@Override
	public Integer getCardapioId(Integer escola, Integer programa) {
		System.out.println(escola+" "+programa);
		return icpeRepository.findCardapioId(escola, programa);
	}
	@Override
	public ItemCardapioProgramaEscola getCardapioByIds(Integer escola, Integer programa, Integer cardapio) {
		return icpeRepository.getCardapio(escola, programa, cardapio);		
	}

}
