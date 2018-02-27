package com.example.nutricao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nutricao.model.Escola;
import com.example.nutricao.model.Programa;
import com.example.nutricao.repository.EscolaRepository;
import com.example.nutricao.service.declared.EscolaService;
import com.example.nutricao.service.declared.ProgramaService;

@Service
public class EscolaServiceImpl implements EscolaService{

	@Autowired
	private EscolaRepository escolaRepository;
	
	@Autowired
	private ProgramaService programaService;
	
	@Override
	public List<Escola> getAll() {
		return escolaRepository.findAll();
	}

	@Override
	public void cadastrar(Escola escola) {
		escolaRepository.save(escola);
	}

	@Override
	public Escola getById(Integer id) {
		return escolaRepository.findOne(id);
	}

	@Override
	public void excluir(Escola escola) {
		escolaRepository.delete(escola);
	}

	@Override
	public List<Escola> buscarPorContemNome(String nome) {
		return null;
	}

	@Override
	public void adicionaProgramaEscola(Escola escola, Programa programa) {
		if(escola!=null && programa!=null) {
			programaService.desabilita(programa);
			escola.getProgramas().add(programa);
			escolaRepository.save(escola);
		}
	}

	@Override
	public void excluirProgramaEscola(Escola escola, Programa programa) {
		if (escola != null && programa != null) {
			escola.removePrograma(programa);
			this.cadastrar(escola);
		}		
	}

}
