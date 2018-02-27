package com.example.nutricao.service.declared;

import java.util.List;

import com.example.nutricao.model.Cardapio;
import com.example.nutricao.model.Escola;
import com.example.nutricao.model.Programa;

public interface ProgramaService {
	public List<Programa> getAll();

	public void cadastrar(Programa programa);

	public Programa getById(Integer id);

	public void excluir(Programa programa);
	
	public void desabilita(Programa programa);

	public List<Programa> buscarPorContemNome(String nome);

	public void adicionaCardapioPrograma(Escola escola, Programa programa, Cardapio cardapio);
	
	public void excluirCardapioPrograma(Escola escola, Programa programa, Cardapio cardapio);
	
}
