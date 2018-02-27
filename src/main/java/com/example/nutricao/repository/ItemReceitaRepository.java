package com.example.nutricao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nutricao.model.ItemReceita;
@Repository
public interface ItemReceitaRepository extends JpaRepository<ItemReceita, Integer>{
	
	
}
