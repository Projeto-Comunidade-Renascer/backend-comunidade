package com.comunidade.renascer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comunidade.renascer.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByUsuario(String usuario);
}
