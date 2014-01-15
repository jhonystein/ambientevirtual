/*
* Ambiente Virtual
* Copyright (C) 2014 SENAI
* ----------------------------------------------------------------------------
* Este arquivo é parte do Sistema Ambiente Virtual.
* Finalidade - Bean de turmas
* 
* Criado por - William Chenta
*/
package br.edu.senai.ambientevirtual.view;

import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.security.RequiredRole;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.edu.senai.ambientevirtual.business.TurmaBC;
import br.edu.senai.ambientevirtual.business.TutorBC;
import br.edu.senai.ambientevirtual.domain.Turma;
import br.edu.senai.ambientevirtual.domain.Tutor;

@ViewController
@PreviousView("./turma_list.jsf")
@RequiredRole("adm")
public class TurmaEditMB extends AbstractEditPageBean<Turma, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private TurmaBC turmaBC;
	
	@Inject
	private TutorBC tutorBC;
	
	public List<Tutor> getTutores() {
		return tutorBC.findAll();
	}
	
	@Override
	@Transactional
	public String delete() {
		this.turmaBC.delete(getId());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String insert() {
		this.turmaBC.insert(getBean());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String update() {
		this.turmaBC.update(getBean());
		return getPreviousView();
	}
	
	@Override
	protected void handleLoad() {
		setBean(this.turmaBC.load(getId()));
	}

}