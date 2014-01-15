/*
* Ambiente Virtual
* Copyright (C) 2014 SENAI
* ----------------------------------------------------------------------------
* Este arquivo é parte do Sistema Ambiente Virtual.
* Finalidade - Carrega usuário logado
* 
* Criado por - William Chenta
*/
package br.edu.senai.ambientevirtual.security;

import javax.inject.Inject;

import br.edu.senai.ambientevirtual.domain.Usuario;
import br.edu.senai.ambientevirtual.persistence.UsuarioDAO;
import br.gov.frameworkdemoiselle.security.SecurityContext;

public class InfoUsuario {
	@Inject
	SecurityContext securityContext;
	
	@Inject
	UsuarioDAO usuarioDAO;
	
	public Usuario retInfo() {
		Long id = Long.valueOf(securityContext.getUser().getId());
		Usuario usuario = usuarioDAO.load(id);
		return usuario;
	}
}
