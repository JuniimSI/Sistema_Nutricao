package com.example.nutricao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nutricao.model.Cardapio;
import com.example.nutricao.model.Escola;
import com.example.nutricao.model.Programa;
import com.example.nutricao.model.Receita;
import com.example.nutricao.repository.CardapioRepository;
import com.example.nutricao.service.declared.CardapioService;
import com.example.nutricao.service.declared.ReceitaService;

@Service
public class CardapioServiceImpl implements CardapioService {

	@Autowired
	private CardapioRepository cardapioRepository;
	
	@Autowired
	private ReceitaService receitaService;

	@Override
	public List<Cardapio> getAll() {
		return cardapioRepository.findAllDistinctByNome();
	}

	@Override
	public void cadastrar(Cardapio cardapio) {
		cardapioRepository.save(cardapio);
	}

	@Override
	public Cardapio getById(Integer id) {
		return cardapioRepository.findOne(id);
	}
	
	@Override
	public void desabilita(Cardapio cardapio) {
		cardapio.setEditavel(false);
		this.cadastrar(cardapio);
	}
	
	@Override
	public void excluir(Cardapio cardapio) {
		cardapioRepository.delete(cardapio);
	}

	@Override
	public List<Cardapio> buscarPorContemNome(String nome) {
		return null;
	}

	@Override
	public void excluirCardapioPrograma(Escola escola, Cardapio cardapio, Programa programa) {
		if (escola != null && cardapio != null && programa != null) {
			programa.removeCardapio(cardapio);
			this.cadastrar(cardapio);
		}
	}

	@Override
	public void adicionaReceitaCardapioSegunda( Escola escola, Programa programa, Cardapio cardapio, Receita receita) {
		if (escola != null && programa != null && cardapio != null && receita != null) {
			receitaService.desabilita(receita);
			cardapio.addSegunda(receita);
			this.cadastrar(cardapio);
		}
	}

	@Override
	public void adicionaReceitaCardapioTerca(Escola escola, Programa programa, Cardapio cardapio, Receita receita) {
		if (escola != null && programa != null && cardapio != null && receita != null) {
			receitaService.desabilita(receita);
			cardapio.addTerca(receita);
			this.cadastrar(cardapio);
		}
	}

	@Override
	public void adicionaReceitaCardapioQuarta(Escola escola, Programa programa, Cardapio cardapio, Receita receita) {
		if (escola != null && programa != null && cardapio != null && receita != null) {
			receitaService.desabilita(receita);
			cardapio.addQuarta(receita);
			this.cadastrar(cardapio);
		}
	}

	@Override
	public void adicionaReceitaCardapioQuinta(Escola escola, Programa programa, Cardapio cardapio, Receita receita) {
		if (escola != null && programa != null && cardapio != null && receita != null) {
			receitaService.desabilita(receita);
			cardapio.addQuinta(receita);
			this.cadastrar(cardapio);
		}
	}

	@Override
	public void adicionaReceitaCardapioSexta(Escola escola, Programa programa, Cardapio cardapio, Receita receita) {
		if (escola != null && programa != null && cardapio != null && receita != null) {
			receitaService.desabilita(receita);
			cardapio.addSexta(receita);
			this.cadastrar(cardapio);
		}
	}

	@Override
	public void excluiReceitaCardapioSegunda(Escola escola, Programa programa, Cardapio cardapio, Receita receita) {
		if (escola != null && programa != null && cardapio != null && receita != null) {
			cardapio.setSegunda(null);
			this.cadastrar(cardapio);
		}
	}

	@Override
	public void excluiReceitaCardapioTerca(Escola escola, Programa programa, Cardapio cardapio, Receita receita) {
		if (escola != null && programa != null && cardapio != null && receita != null) {
			cardapio.setTerca(null);
			this.cadastrar(cardapio);
		}
	}

	@Override
	public void excluiReceitaCardapioQuarta(Escola escola, Programa programa, Cardapio cardapio, Receita receita) {
		if (escola != null && programa != null && cardapio != null && receita != null) {
			cardapio.setQuarta(null);
			this.cadastrar(cardapio);
		}
	}

	@Override
	public void excluiReceitaCardapioQuinta(Escola escola, Programa programa, Cardapio cardapio, Receita receita) {
		if(escola!=null && programa!=null && cardapio!=null && receita!=null ) {
			cardapio.setQuinta(null);
			this.cadastrar(cardapio);
		}
	}

	@Override
	public void excluiReceitaCardapioSexta(Escola escola, Programa programa, Cardapio cardapio, Receita receita) {
		if(escola!=null && programa!=null && cardapio!=null && receita!=null ) {
			cardapio.setSexta(null);
			this.cadastrar(cardapio);
		}
		
	}
	
	public Cardapio retornaCardapioEscolaPrograma(Integer escola, Integer programa) {
		return cardapioRepository.findCardapioByAllExtend(escola, programa);
	}

	@Override
	public void adicionaReceitaCardapioSegundaSimple(Cardapio cardapio, Receita receita) {
		if (cardapio != null && receita != null) {
			receitaService.desabilita(receita);
			cardapio.addSegunda(receita);
			this.cadastrar(cardapio);
		}		
	}

	@Override
	public void adicionaReceitaCardapioTercaSimple(Cardapio cardapio, Receita receita) {
		if (cardapio != null && receita != null) {
			receitaService.desabilita(receita);
			cardapio.addTerca(receita);
			this.cadastrar(cardapio);
		}		
		
	}

	@Override
	public void adicionaReceitaCardapioQuartaSimple(Cardapio cardapio, Receita receita) {
		if (cardapio != null && receita != null) {
			receitaService.desabilita(receita);
			cardapio.addQuarta(receita);
			this.cadastrar(cardapio);
		}		
		
	}

	@Override
	public void adicionaReceitaCardapioQuintaSimple(Cardapio cardapio, Receita receita) {
		if (cardapio != null && receita != null) {
			receitaService.desabilita(receita);
			cardapio.addQuinta(receita);
			this.cadastrar(cardapio);
		}		
		
	}

	@Override
	public void adicionaReceitaCardapioSextaSimple(Cardapio cardapio, Receita receita) {
		if (cardapio != null && receita != null) {
			receitaService.desabilita(receita);
			cardapio.addSexta(receita);
			this.cadastrar(cardapio);
		}		
		
	}

	@Override
	public void excluiReceitaCardapioSegundaSimple(Cardapio cardapio, Receita receita) {
		if (cardapio != null && receita != null) {
			cardapio.getSegunda().remove(receita);
			this.cadastrar(cardapio);
		}
		
	}

	@Override
	public void excluiReceitaCardapioTercaSimple(Cardapio cardapio, Receita receita) {
		if (cardapio != null && receita != null) {
			cardapio.getTerca().remove(receita);
			this.cadastrar(cardapio);
		}		
	}

	@Override
	public void excluiReceitaCardapioQuartaSimple(Cardapio cardapio, Receita receita) {
		if (cardapio != null && receita != null) {
			cardapio.getQuarta().remove(receita);
			this.cadastrar(cardapio);
		}
		
	}

	@Override
	public void excluiReceitaCardapioQuintaSimple(Cardapio cardapio, Receita receita) {
		if (cardapio != null && receita != null) {
			cardapio.getQuinta().remove(receita);
			this.cadastrar(cardapio);
		}
		
	}

	@Override
	public void excluiReceitaCardapioSextaSimple(Cardapio cardapio, Receita receita) {
		if (cardapio != null && receita != null) {
			cardapio.getSexta().remove(receita);
			this.cadastrar(cardapio);
		}
		
	}

}
