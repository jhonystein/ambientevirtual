/*
* Ambiente Virtual
* Copyright (C) 2014 SENAI
* ----------------------------------------------------------------------------
* Este arquivo é parte do Sistema Ambiente Virtual.
* Finalidade - Autenticador de usuários do sistema
* 
* Criado por - William Chenta
*/
package br.edu.senai.ambientevirtual.security;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.edu.senai.ambientevirtual.business.UsuarioBC;
import br.edu.senai.ambientevirtual.domain.Usuario;
import br.edu.senai.ambientevirtual.exception.LoginException;
import br.gov.frameworkdemoiselle.security.Authenticator;
import br.gov.frameworkdemoiselle.security.User;

public class Autenticador implements Authenticator {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Credenciais credenciais;
	
	@Inject
	private UsuarioBC usuarioBC;
	
	private Usuario usuario = null;
	
	@Override
	public boolean authenticate() {
		boolean autenticado = false;
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		String redirect = "/ambientevirtual/";
		
		if(!(credenciais.getNome() == null) && !(credenciais.getSenha() == null)) {
			usuario = usuarioBC.buscaUsuarioCredenciais(credenciais);
		} 
		
		if(usuario != null) {
			autenticado = true;
			
			if(usuario.getTipoUsu().equals("adm")) {
				redirect += "index.jsf";
			}
			if(usuario.getTipoUsu().equals("tut")) {
				redirect += "tutor/index.jsf";
			}
			if(usuario.getTipoUsu().equals("alu")) {
				redirect += "aluno/index.jsf";
			}
			
			try {
				ec.redirect(redirect);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {			
			throw new LoginException();
		}
		return autenticado;
	}

	@Override
	public User getUser() {
		
		//se nao passou pela tela de login nem tenta logar
		if(!(credenciais.getNome() == null) && !(credenciais.getSenha() == null)) {
			usuario = usuarioBC.buscaUsuarioCredenciais(credenciais);
		} 
		
		//se n autenticou, volta para a tela de login
		if(usuario == null) {
			return null;
		}
		return new UsuarioUser(usuario);
	}

	@Override
	public void unAuthenticate() {}
	
}
