package com.example.nutricao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nutricao.model.User;


@Repository
public interface UsuarioRepository extends JpaRepository<User, Integer> {
	User findByUsername(String username);

}
