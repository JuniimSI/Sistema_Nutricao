package com.example.nutricao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.nutricao.model.Receita;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Integer>{

}
