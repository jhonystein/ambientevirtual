/*
* Ambiente Virtual
* Copyright (C) 2014 SENAI
* ----------------------------------------------------------------------------
* Este arquivo é parte do Sistema Ambiente Virtual.
* Finalidade - Controller de tutores
* 
* Criado por - William Chenta
*/
package br.edu.senai.ambientevirtual.business;

import java.util.List;
import java.util.Map;

import br.edu.senai.ambientevirtual.domain.Tutor;
import br.edu.senai.ambientevirtual.persistence.TutorDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class TutorBC extends DelegateCrud<Tutor, Long, TutorDAO> {
	
	private static final long serialVersionUID = 1L;
	
	public List<Tutor> filtrarQuery(String filtro, Map<String, String> params) {
		return getDelegate().filtrarQuery(filtro, params);
	}	
	
	public Tutor loadTutor(Long id) {
		return getDelegate().loadTutor(id);
	}
}
