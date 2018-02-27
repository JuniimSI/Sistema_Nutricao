package com.example.nutricao.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.nutricao.model.ItemCardapioProgramaEscola;
@Repository
public interface ItemCardapioProgramaEscolaRepository extends JpaRepository<ItemCardapioProgramaEscola, Integer>{
	ItemCardapioProgramaEscola getByEscolaIdAndProgramaId(Integer escolaId, Integer programaId);

	@Query("Select icpe.cardapioId from ItemCardapioProgramaEscola as icpe where icpe.escolaId=:idEscola and icpe.programaId=:idPrograma")
	Integer findCardapioId(@Param("idEscola")Integer idEscola,@Param("idPrograma") Integer idPrograma);

	@Query("Select icpe from ItemCardapioProgramaEscola as icpe where icpe.escolaId=:idEscola and icpe.programaId=:idPrograma and icpe.cardapioId=:idCardapio")
	ItemCardapioProgramaEscola getCardapio(@Param("idEscola")Integer escola,@Param("idPrograma") Integer programa,@Param("idCardapio") Integer cardapio);
	
	@Modifying
	@Transactional
	@Query("Delete from ItemCardapioProgramaEscola as icpe where icpe.escolaId=:idEscola and icpe.programaId=:idPrograma and icpe.cardapioId=:idCardapio")
	void deleteCardapio(@Param("idEscola")Integer escola,@Param("idPrograma") Integer programa,@Param("idCardapio") Integer cardapio);
}
