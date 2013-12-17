package br.edu.senai.ambientevirtual.view;

import javax.inject.Inject;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.edu.senai.ambientevirtual.business.EntregaAtividadeBC;
import br.edu.senai.ambientevirtual.domain.EntregaAtividade;

@ViewController
@PreviousView("./entregaAtividade_list.jsf")
public class EntregaAtividadeEditMB extends AbstractEditPageBean<EntregaAtividade, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntregaAtividadeBC entregaAtividadeBC;
	
	@Override
	@Transactional
	public String delete() {
		this.entregaAtividadeBC.delete(getId());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String insert() {
		this.entregaAtividadeBC.insert(getBean());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String update() {
		this.entregaAtividadeBC.update(getBean());
		return getPreviousView();
	}
	
	@Override
	protected void handleLoad() {
		setBean(this.entregaAtividadeBC.load(getId()));
	}

}