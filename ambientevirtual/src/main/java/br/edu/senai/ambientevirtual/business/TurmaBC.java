package br.edu.senai.ambientevirtual.business;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

import br.edu.senai.ambientevirtual.domain.Turma;
import br.edu.senai.ambientevirtual.persistence.TurmaDAO;

@BusinessController
public class TurmaBC extends DelegateCrud<Turma, Long, TurmaDAO> {
	
	private static final long serialVersionUID = 1L;
	
}
