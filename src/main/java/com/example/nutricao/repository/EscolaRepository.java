package com.example.nutricao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nutricao.model.Escola;

@Repository
public interface EscolaRepository extends JpaRepository<Escola, Integer>{

}
