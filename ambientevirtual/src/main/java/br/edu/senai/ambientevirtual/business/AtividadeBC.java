package br.edu.senai.ambientevirtual.business;

import java.util.List;
import java.util.Map;

import br.edu.senai.ambientevirtual.domain.Atividade;
import br.edu.senai.ambientevirtual.persistence.AtividadeDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class AtividadeBC extends DelegateCrud<Atividade, Long, AtividadeDAO> {
	
	private static final long serialVersionUID = 1L;
	
	public List<Atividade> filtrarQuery(String filtro, Map<String, String> params) {
		return getDelegate().filtrarQuery(filtro, params);
	}
	
}
