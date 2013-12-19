package br.edu.senai.ambientevirtual.view;

import java.util.List;

import javax.inject.Inject;

import br.edu.senai.ambientevirtual.business.TurmaBC;
import br.edu.senai.ambientevirtual.business.TutorBC;
import br.edu.senai.ambientevirtual.domain.Turma;
import br.edu.senai.ambientevirtual.domain.Tutor;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;

@ViewController
@PreviousView("./turma_list.jsf")
public class TurmaAddTutorMB extends AbstractEditPageBean<Turma, Long> {

	private static final long serialVersionUID = 1L;
	@Inject
	private TurmaBC turmaBC;
	
	@Inject
	private TutorBC tutorBC;
	
	public List<Tutor> getTutores() {
		return tutorBC.findAll();
	}
	
	@Override
	public String delete() {
		return null;
	}

	@Override
	public String insert() {
		return null;
	}

	@Override
	public String update() {
		this.turmaBC.update(getBean());
		return getPreviousView();
	}

	@Override
	protected void handleLoad() {
		setBean(this.turmaBC.load(getId()));
	}
	
}
