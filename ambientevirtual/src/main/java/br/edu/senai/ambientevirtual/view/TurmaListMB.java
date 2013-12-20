package br.edu.senai.ambientevirtual.view;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
	protected List<Turma> handleResultList() {
		
		if (filtro != null && !filtro.isEmpty() && tipoFiltro != null && !tipoFiltro.isEmpty()) {
			params.put(tipoFiltro, filtro);			
			return this.turmaBC.filtrarQuery(tipoFiltro, params);
		}
		
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