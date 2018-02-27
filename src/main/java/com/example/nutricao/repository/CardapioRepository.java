package com.example.nutricao.repository;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.nutricao.model.Cardapio;

@Repository
public interface CardapioRepository  extends JpaRepository<Cardapio, Integer> {
	@Query("SELECT c from Cardapio c Group By c.nome")
	public List<Cardapio> findAllDistinctByNome();
	//"SELECT DISTINCT e FROM AlocacaoItemSetor alo INNER JOIN alo.itemEntrada item INNER JOIN item.entrada e INNER JOIN alo.setor s WHERE s.id = :idSetor"
	@Query("Select c from Escola e inner join e.programas p inner join p.cardapios c where e.id=:idEscola and p.id=:idPrograma ")
	public Cardapio findCardapioByAllExtend(@Param("idEscola")Integer idEscola,@Param("idPrograma") Integer idPrograma);
}
