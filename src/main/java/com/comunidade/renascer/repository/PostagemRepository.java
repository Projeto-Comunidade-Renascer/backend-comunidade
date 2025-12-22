package com.comunidade.renascer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comunidade.renascer.model.Postagem;

public interface PostagemRepository extends JpaRepository<Postagem, Long>{
	
	List<Postagem> findAllByTextoContainingIgnoreCase(String texto);
}
