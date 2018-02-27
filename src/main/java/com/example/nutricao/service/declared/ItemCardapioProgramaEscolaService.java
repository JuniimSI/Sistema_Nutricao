package com.example.nutricao.service.declared;

import com.example.nutricao.model.ItemCardapioProgramaEscola;

public interface ItemCardapioProgramaEscolaService {
	Integer getCardapioId(Integer escola, Integer programa);
	ItemCardapioProgramaEscola getCardapioByIds(Integer escola, Integer programa, Integer cardapio);
}
