/*
* Ambiente Virtual
* Copyright (C) 2014 SENAI
* ----------------------------------------------------------------------------
* Este arquivo é parte do Sistema Ambiente Virtual.
* Finalidade - Controller de turmas
* 
* Criado por - William Chenta
*/
package br.edu.senai.ambientevirtual.business;

import java.util.List;
import java.util.Map;

import br.edu.senai.ambientevirtual.domain.Turma;
import br.edu.senai.ambientevirtual.persistence.TurmaDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class TurmaBC extends DelegateCrud<Turma, Long, TurmaDAO> {
	
	private static final long serialVersionUID = 1L;
	
	public List<Turma> filtrarQuery(String filtro, Map<String, String> params) {
		return getDelegate().filtrarQuery(filtro, params);
	}
	
}
