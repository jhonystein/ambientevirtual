package br.edu.senai.ambientevirtual.business;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

import br.edu.senai.ambientevirtual.domain.Aluno;
import br.edu.senai.ambientevirtual.persistence.AlunoDAO;

@BusinessController
public class AlunoBC extends DelegateCrud<Aluno, Long, AlunoDAO> {
	
	private static final long serialVersionUID = 1L;
	
}
