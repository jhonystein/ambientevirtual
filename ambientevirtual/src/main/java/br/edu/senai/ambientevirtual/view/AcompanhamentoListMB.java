package br.edu.senai.ambientevirtual.view;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.security.RequiredRole;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.edu.senai.ambientevirtual.business.AcompanhamentoBC;
import br.edu.senai.ambientevirtual.domain.Acompanhamento;

@ViewController
@NextView("./acompanhamento_edit.jsf")
@PreviousView("./acompanhamento_list.jsf")
@RequiredRole("tut")
public class AcompanhamentoListMB extends AbstractListPageBean<Acompanhamento, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private AcompanhamentoBC acompanhamentoBC;

	private String tipoFiltro;
	
	private String filtro;
	
	@Override
	protected List<Acompanhamento> handleResultList() {
		if (filtro != null) {
			return this.acompanhamentoBC.filtrar(tipoFiltro, filtro);
		}
		return this.acompanhamentoBC.findAll();
	}
	
	@Transactional
	public String deleteSelection() {
		boolean delete;
		for (Iterator<Long> iter = getSelection().keySet().iterator(); iter.hasNext();) {
			Long id = iter.next();
			delete = getSelection().get(id);
			if (delete) {
				acompanhamentoBC.delete(id);
				iter.remove();
			}
		}
		return getPreviousView();
	}

	public String getTipoFiltro() {
		return tipoFiltro;
	}

	public void setTipoFiltro(String tipoFiltro) {
		this.tipoFiltro = tipoFiltro;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

}