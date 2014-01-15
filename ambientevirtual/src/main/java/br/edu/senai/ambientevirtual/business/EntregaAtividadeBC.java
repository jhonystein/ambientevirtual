/*
* Ambiente Virtual
* Copyright (C) 2014 SENAI
* ----------------------------------------------------------------------------
* Este arquivo é parte do Sistema Ambiente Virtual.
* Finalidade - Controller de entregas de atividade
* 
* Criado por - William Chenta
*/
package br.edu.senai.ambientevirtual.business;

import java.util.List;
import java.util.Map;

import br.edu.senai.ambientevirtual.domain.EntregaAtividade;
import br.edu.senai.ambientevirtual.persistence.EntregaAtividadeDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class EntregaAtividadeBC extends DelegateCrud<EntregaAtividade, Long, EntregaAtividadeDAO> {
	private static final long serialVersionUID = 1L;
	
	public List<EntregaAtividade> filtrarQuery(String filtro, Map<String, String> params) {
		return getDelegate().filtrarQuery(filtro, params);
	}
}
