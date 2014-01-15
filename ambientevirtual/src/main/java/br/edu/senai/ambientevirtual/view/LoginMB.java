/*
* Ambiente Virtual
* Copyright (C) 2014 SENAI
* ----------------------------------------------------------------------------
* Este arquivo é parte do Sistema Ambiente Virtual.
* Finalidade - Bean de login
* 
* Criado por - William Chenta
*/
package br.edu.senai.ambientevirtual.view;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import br.edu.senai.ambientevirtual.security.Credenciais;
import br.gov.frameworkdemoiselle.security.SecurityContext;

@ManagedBean
@SessionScoped
public class LoginMB implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private Credenciais credenciais;
	
	@Inject
	private SecurityContext securityContext;
	
	public Credenciais getCredenciais() {
		return credenciais;
	}

	public void setCredenciais(Credenciais credenciais) {
		this.credenciais = credenciais;
	}
	
	public void logar(){
		securityContext.login();
	}
}
