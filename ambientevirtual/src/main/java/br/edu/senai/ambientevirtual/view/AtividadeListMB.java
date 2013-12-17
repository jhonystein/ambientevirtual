package br.edu.senai.ambientevirtual.view;

import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.edu.senai.ambientevirtual.business.AtividadeBC;
import br.edu.senai.ambientevirtual.domain.Atividade;

@ViewController
@NextView("./atividade_edit.jsf")
@PreviousView("./atividade_list.jsf")
public class AtividadeListMB extends AbstractListPageBean<Atividade, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private AtividadeBC atividadeBC;
	
	@Override
	protected List<Atividade> handleResultList() {
		return this.atividadeBC.findAll();
	}
	
	@Transactional
	public String deleteSelection() {
		boolean delete;
		for (Iterator<Long> iter = getSelection().keySet().iterator(); iter.hasNext();) {
			Long id = iter.next();
			delete = getSelection().get(id);
			if (delete) {
				atividadeBC.delete(id);
				iter.remove();
			}
		}
		return getPreviousView();
	}

}