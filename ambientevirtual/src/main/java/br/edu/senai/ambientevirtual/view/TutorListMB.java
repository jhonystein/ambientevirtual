package br.edu.senai.ambientevirtual.view;

import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.edu.senai.ambientevirtual.business.TutorBC;
import br.edu.senai.ambientevirtual.domain.Tutor;

@ViewController
@NextView("./tutor_edit.jsf")
@PreviousView("./tutor_list.jsf")
public class TutorListMB extends AbstractListPageBean<Tutor, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private TutorBC tutorBC;
	
	@Override
	protected List<Tutor> handleResultList() {
		return this.tutorBC.findAll();
	}
	
	@Transactional
	public String deleteSelection() {
		boolean delete;
		for (Iterator<Long> iter = getSelection().keySet().iterator(); iter.hasNext();) {
			Long id = iter.next();
			delete = getSelection().get(id);
			if (delete) {
				tutorBC.delete(id);
				iter.remove();
			}
		}
		return getPreviousView();
	}

}