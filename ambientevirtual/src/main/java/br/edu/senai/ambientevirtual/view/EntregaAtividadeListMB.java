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
import br.edu.senai.ambientevirtual.business.EntregaAtividadeBC;
import br.edu.senai.ambientevirtual.domain.EntregaAtividade;

@ViewController
@NextView("./entregaAtividade_edit.jsf")
@PreviousView("./entregaAtividade_list.jsf")
public class EntregaAtividadeListMB extends AbstractListPageBean<EntregaAtividade, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntregaAtividadeBC entregaAtividadeBC;
	
	private String filtro;
	private String tipoFiltro;
	private String prmIdEntrega;
	
	private EntregaAtividade entregaAtividade;
	
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
	
	public String getPrmIdEntrega() {
		return prmIdEntrega;
	}

	public void setPrmIdEntrega(String prmIdEntrega) {
		this.prmIdEntrega = prmIdEntrega;
	}
	
	public EntregaAtividade getEntregaAtividade() {
		return entregaAtividade;
	}

	public void setEntregaAtividade(EntregaAtividade entregaAtividade) {
		this.entregaAtividade = entregaAtividade;
	}
	
	public void loadDetalhesEntrega() {
		entregaAtividade = entregaAtividadeBC.load(Long.valueOf(prmIdEntrega));
	}	

	@Override
	protected List<EntregaAtividade> handleResultList() {
		
		if (filtro != null && !filtro.isEmpty() && tipoFiltro != null && !tipoFiltro.isEmpty()) {
			params.put(tipoFiltro, filtro);			
			return this.entregaAtividadeBC.filtrarQuery(tipoFiltro, params);
		}
		
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