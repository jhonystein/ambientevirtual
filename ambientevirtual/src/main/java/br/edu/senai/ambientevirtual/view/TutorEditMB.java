package br.edu.senai.ambientevirtual.view;

import javax.inject.Inject;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.edu.senai.ambientevirtual.business.TutorBC;
import br.edu.senai.ambientevirtual.domain.Tutor;

@ViewController
@PreviousView("./tutor_list.jsf")
public class TutorEditMB extends AbstractEditPageBean<Tutor, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private TutorBC tutorBC;
	
	@Override
	@Transactional
	public String delete() {
		this.tutorBC.delete(getId());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String insert() {
		this.tutorBC.insert(getBean());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String update() {
		this.tutorBC.update(getBean());
		return getPreviousView();
	}
	
	@Override
	protected void handleLoad() {
		setBean(this.tutorBC.load(getId()));
	}

}