package com.comunidade.renascer.util;

import com.comunidade.renascer.model.Usuario;
import com.comunidade.renascer.model.UsuarioLogin;

public class TestBuilder {
	public static Usuario criarUsuario(Long id, String nome, String usuario, String senha) {
		Usuario novoUsuario = new Usuario();
		novoUsuario.setId(id);
		novoUsuario.setNome(nome);
		novoUsuario.setUsuario(usuario);
		novoUsuario.setSenha(senha);
		novoUsuario.setFoto("-");
		return novoUsuario;
	}

	public static UsuarioLogin criarUsuarioLogin(String usuario, String senha) {
		UsuarioLogin usuarioLogin = new UsuarioLogin();
		usuarioLogin.setUsuario(usuario);
		usuarioLogin.setSenha(senha);
		return usuarioLogin;
	}
}