/*
* Ambiente Virtual
* Copyright (C) 2014 SENAI
* ----------------------------------------------------------------------------
* Este arquivo é parte do Sistema Ambiente Virtual.
* Finalidade - Bean de usuários
* 
* Criado por - William Chenta
*/
package br.edu.senai.ambientevirtual.view;

import javax.inject.Inject;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.edu.senai.ambientevirtual.business.UsuarioBC;
import br.edu.senai.ambientevirtual.domain.Usuario;

@ViewController
@PreviousView("./usuario_list.jsf")
public class UsuarioEditMB extends AbstractEditPageBean<Usuario, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioBC usuarioBC;
	
	@Override
	@Transactional
	public String delete() {
		this.usuarioBC.delete(getId());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String insert() {
		this.usuarioBC.insert(getBean());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String update() {
		this.usuarioBC.update(getBean());
		return getPreviousView();
	}
	
	@Override
	protected void handleLoad() {
		setBean(this.usuarioBC.load(getId()));
	}

}