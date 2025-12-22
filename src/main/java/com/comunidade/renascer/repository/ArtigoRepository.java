package com.comunidade.renascer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comunidade.renascer.model.Artigo;

public interface ArtigoRepository extends JpaRepository<Artigo, Long> {
    List<Artigo> findAllByTituloContainingIgnoreCase(String titulo);
}
