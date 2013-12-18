package br.edu.senai.ambientevirtual.view;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.edu.senai.ambientevirtual.business.TurmaBC;
import br.edu.senai.ambientevirtual.domain.Turma;

@ViewController
@NextView("./turma_edit.jsf")
@PreviousView("./turma_list.jsf")
public class TurmaListMB extends AbstractListPageBean<Turma, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private TurmaBC turmaBC;

	@Override
	protected List<Turma> handleResultList() {
		return this.turmaBC.findAll();
	}

	@Transactional
	public String deleteSelection() {
		boolean delete;
		for (Iterator<Long> iter = getSelection().keySet().iterator(); iter
				.hasNext();) {
			Long id = iter.next();
			delete = getSelection().get(id);
			if (delete) {
				turmaBC.delete(id);
				iter.remove();
			}
		}
		return getPreviousView();
	}

}