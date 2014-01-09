package br.edu.senai.ambientevirtual.view;

import java.util.List;

import javax.inject.Inject;

import br.edu.senai.ambientevirtual.business.AtividadeBC;
import br.edu.senai.ambientevirtual.business.TutorBC;
import br.edu.senai.ambientevirtual.domain.Atividade;
import br.edu.senai.ambientevirtual.domain.Tutor;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.security.RequiredRole;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@ViewController
@PreviousView("./atividade_list.jsf")
@RequiredRole("adm")
public class AtividadeEditMB extends AbstractEditPageBean<Atividade, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private AtividadeBC atividadeBC;
	
	@Inject
	private TutorBC tutorBC;
	
	public List<Tutor> getGetListTutores() {
		return tutorBC.findAll();
	}

	@Override
	@Transactional
	public String delete() {
		this.atividadeBC.delete(getId());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String insert() {
		this.atividadeBC.insert(getBean());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String update() {
		this.atividadeBC.update(getBean());
		return getPreviousView();
	}
	
	@Override
	protected void handleLoad() {
		setBean(this.atividadeBC.load(getId()));
	}

}