package br.edu.senai.ambientevirtual.view;

import javax.inject.Inject;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.edu.senai.ambientevirtual.business.AcompanhamentoBC;
import br.edu.senai.ambientevirtual.domain.Acompanhamento;

@ViewController
@PreviousView("./acompanhamento_list.jsf")
public class AcompanhamentoEditMB extends AbstractEditPageBean<Acompanhamento, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private AcompanhamentoBC acompanhamentoBC;
	
	@Override
	@Transactional
	public String delete() {
		this.acompanhamentoBC.delete(getId());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String insert() {
		this.acompanhamentoBC.insert(getBean());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String update() {
		this.acompanhamentoBC.update(getBean());
		return getPreviousView();
	}
	
	@Override
	protected void handleLoad() {
		setBean(this.acompanhamentoBC.load(getId()));
	}

}