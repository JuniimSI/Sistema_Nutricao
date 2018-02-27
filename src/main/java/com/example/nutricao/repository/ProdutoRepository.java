package com.example.nutricao.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.nutricao.model.Produto;


@Repository
public interface ProdutoRepository  extends JpaRepository<Produto, Integer>{

	Produto findById(Integer id);

	List<Produto> getDistinctByNomeStartingWithIgnoreCase(String nome);
	
	Produto getByNome(String nome);
	
	@Query("select valor from Produto where nome=:nome")
	Double getValorByNome(@Param("nome") String nome);
	
}
