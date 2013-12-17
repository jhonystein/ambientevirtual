package br.edu.senai.ambientevirtual.business;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

import br.edu.senai.ambientevirtual.domain.Atividade;
import br.edu.senai.ambientevirtual.persistence.AtividadeDAO;

@BusinessController
public class AtividadeBC extends DelegateCrud<Atividade, Long, AtividadeDAO> {
	
	private static final long serialVersionUID = 1L;
	
}
