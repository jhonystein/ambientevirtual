/*
* Ambiente Virtual
* Copyright (C) 2014 SENAI
* ----------------------------------------------------------------------------
* Este arquivo é parte do Sistema Ambiente Virtual.
* Finalidade - Controller de grupos
* 
* Criado por - William Chenta
*/
package br.edu.senai.ambientevirtual.business;

import java.util.List;
import java.util.Map;

import br.edu.senai.ambientevirtual.domain.Grupo;
import br.edu.senai.ambientevirtual.persistence.GrupoDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class GrupoBC extends DelegateCrud<Grupo, Long, GrupoDAO> {
	
	private static final long serialVersionUID = 1L;
	
	public List<Grupo> filtrarQuery(String filtro, Map<String, String> params) {
		return getDelegate().filtrarQuery(filtro, params);
	}
	
}
