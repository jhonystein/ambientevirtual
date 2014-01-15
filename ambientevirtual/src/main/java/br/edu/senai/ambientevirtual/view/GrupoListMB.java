/*
* Ambiente Virtual
* Copyright (C) 2014 SENAI
* ----------------------------------------------------------------------------
* Este arquivo é parte do Sistema Ambiente Virtual.
* Finalidade - Bean de grupos
* 
* Criado por - William Chenta
*/
package br.edu.senai.ambientevirtual.view;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import br.edu.senai.ambientevirtual.business.GrupoBC;
import br.edu.senai.ambientevirtual.domain.Grupo;
import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.security.RequiredRole;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@ViewController
@NextView("./grupo_edit.jsf")
@PreviousView("./grupo_list.jsf")
@RequiredRole({"tut","alu"})
public class GrupoListMB extends AbstractListPageBean<Grupo, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private GrupoBC grupoBC;

	private String filtro;
	private String tipoFiltro;
	private Map<String, String> params = new HashMap<String, String>();

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public String getTipoFiltro() {
		return tipoFiltro;
	}

	public void setTipoFiltro(String tipoFiltro) {
		this.tipoFiltro = tipoFiltro;
	}

	@Override
	protected List<Grupo> handleResultList() {

		if (filtro != null && !filtro.isEmpty() && tipoFiltro != null
				&& !tipoFiltro.isEmpty()) {
			params.put(tipoFiltro, filtro);
			return this.grupoBC.filtrarQuery(tipoFiltro, params);
		}

		return this.grupoBC.filtrarQuery("", params);
	}

	@Transactional
	public String deleteSelection() {
		boolean delete;
		for (Iterator<Long> iter = getSelection().keySet().iterator(); iter
				.hasNext();) {
			Long id = iter.next();
			delete = getSelection().get(id);
			if (delete) {
				grupoBC.delete(id);
				iter.remove();
			}
		}
		return getPreviousView();
	}

}