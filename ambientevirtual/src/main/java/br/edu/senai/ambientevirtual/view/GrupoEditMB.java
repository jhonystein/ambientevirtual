package br.edu.senai.ambientevirtual.view;

import java.util.List;

import javax.inject.Inject;

import br.edu.senai.ambientevirtual.business.GrupoBC;
import br.edu.senai.ambientevirtual.business.TurmaBC;
import br.edu.senai.ambientevirtual.business.TutorBC;
import br.edu.senai.ambientevirtual.domain.Grupo;
import br.edu.senai.ambientevirtual.domain.Turma;
import br.edu.senai.ambientevirtual.domain.Tutor;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.security.RequiredRole;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@ViewController
@PreviousView("./grupo_list.jsf")
@RequiredRole("adm")
public class GrupoEditMB extends AbstractEditPageBean<Grupo, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private GrupoBC grupoBC;

	@Inject
	private TutorBC tutorBC;
	
	@Inject
	private TurmaBC turmaBC;	
	
	public List<Tutor> getTutores() {
		return tutorBC.findAll();
	}
	
	public List<Turma> getTurmas() {
		return turmaBC.findAll();
	}
	
	@Override
	@Transactional
	public String delete() {
		this.grupoBC.delete(getId());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String insert() {
		this.grupoBC.insert(getBean());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String update() {
		this.grupoBC.update(getBean());
		return getPreviousView();
	}
	
	@Override
	protected void handleLoad() {
		setBean(this.grupoBC.load(getId()));
	}

}