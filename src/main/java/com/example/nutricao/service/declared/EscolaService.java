package com.example.nutricao.service.declared;

import java.util.List;

import com.example.nutricao.model.Escola;
import com.example.nutricao.model.Programa;

public interface EscolaService {
	public List<Escola> getAll();

	public void cadastrar(Escola escola);

	public Escola getById(Integer id);

	public void excluir(Escola escola);

	public List<Escola> buscarPorContemNome(String nome);

	public void adicionaProgramaEscola(Escola escola, Programa programa);

	public void excluirProgramaEscola(Escola escola, Programa programa);
	
}
