package com.example.nutricao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nutricao.model.Cardapio;
import com.example.nutricao.model.Escola;
import com.example.nutricao.model.ItemCardapioProgramaEscola;
import com.example.nutricao.model.Programa;
import com.example.nutricao.repository.EscolaRepository;
import com.example.nutricao.repository.ItemCardapioProgramaEscolaRepository;
import com.example.nutricao.repository.ProgramaRepository;
import com.example.nutricao.service.declared.CardapioService;
import com.example.nutricao.service.declared.ProgramaService;

@Service
public class ProgramaServiceImpl implements ProgramaService{
	
	@Autowired
	private CardapioService cardapioService;
	
	@Autowired
	private ProgramaRepository programaRepository;
	
	@Autowired
	private EscolaRepository escolaRepository;
	
	@Autowired
	private ItemCardapioProgramaEscolaRepository icpeRepository;
	
	@Override
	public List<Programa> getAll() {
		return programaRepository.findAll();
	}

	@Override
	public void cadastrar(Programa programa) {
		programaRepository.save(programa);
	}

	@Override
	public Programa getById(Integer id) {
		return programaRepository.findOne(id);
	}

	@Override
	public void excluir(Programa programa) {
		programaRepository.delete(programa);
	}

	@Override
	public List<Programa> buscarPorContemNome(String nome) {
		return null;
	}

	@Override
	public void adicionaCardapioPrograma(Escola escola, Programa programa, Cardapio cardapio) {
		if(cardapio!=null && programa!=null && escola!=null) {
			cardapioService.desabilita(cardapio);
			List<Programa> progsEscola = escola.getProgramas();
			System.out.println(progsEscola.get(0).getNome() + progsEscola.size()+"asdakskdas ");
			for(Programa p: progsEscola) {
				if(p.equals(programa)) {
					ItemCardapioProgramaEscola icpe = new ItemCardapioProgramaEscola();
					icpe.setEscolaId(escola.getId());
					icpe.setProgramaId(programa.getId());
					icpe.setCardapioId(cardapio.getId());
					icpeRepository.save(icpe);
					List<ItemCardapioProgramaEscola> nodes = new ArrayList<ItemCardapioProgramaEscola>();
					nodes.add(icpe);
					p.setItemPrograma(nodes);
				}
			}
			escolaRepository.save(escola);
		}
	}
	
	@Override
	public void excluirCardapioPrograma(Escola escola,Programa programa, Cardapio cardapio) {
		if(escola!=null && cardapio!=null && programa!=null) {
			System.out.println("here");
			programa.removeCardapio(cardapio);
			ItemCardapioProgramaEscola icpe = icpeRepository.getCardapio(escola.getId(), programa.getId(), cardapio.getId());
			programa.removeItemCardapio(icpe);
			programa.setCardapios(null);
			programa.setItemPrograma(null);
			icpeRepository.deleteCardapio(escola.getId(), programa.getId(), cardapio.getId());
			this.cadastrar(programa);
		}	
	}

	@Override
	public void desabilita(Programa programa) {
		programa.setEditavel(false);
		this.cadastrar(programa);
	}


}
