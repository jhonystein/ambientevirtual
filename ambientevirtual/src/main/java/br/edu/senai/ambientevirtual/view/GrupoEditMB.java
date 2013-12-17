package br.edu.senai.ambientevirtual.view;

import javax.inject.Inject;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.edu.senai.ambientevirtual.business.GrupoBC;
import br.edu.senai.ambientevirtual.domain.Grupo;

@ViewController
@PreviousView("./grupo_list.jsf")
public class GrupoEditMB extends AbstractEditPageBean<Grupo, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private GrupoBC grupoBC;
	
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