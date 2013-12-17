package br.edu.senai.ambientevirtual.view;

import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.edu.senai.ambientevirtual.business.EntregaAtividadeBC;
import br.edu.senai.ambientevirtual.domain.EntregaAtividade;

@ViewController
@NextView("./entregaAtividade_edit.jsf")
@PreviousView("./entregaAtividade_list.jsf")
public class EntregaAtividadeListMB extends AbstractListPageBean<EntregaAtividade, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntregaAtividadeBC entregaAtividadeBC;
	
	@Override
	protected List<EntregaAtividade> handleResultList() {
		return this.entregaAtividadeBC.findAll();
	}
	
	@Transactional
	public String deleteSelection() {
		boolean delete;
		for (Iterator<Long> iter = getSelection().keySet().iterator(); iter.hasNext();) {
			Long id = iter.next();
			delete = getSelection().get(id);
			if (delete) {
				entregaAtividadeBC.delete(id);
				iter.remove();
			}
		}
		return getPreviousView();
	}

}