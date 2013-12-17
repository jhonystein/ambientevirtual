package br.edu.senai.ambientevirtual.persistence;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

import br.edu.senai.ambientevirtual.domain.Aluno;

@PersistenceController
public class AlunoDAO extends JPACrud<Aluno, Long> {

	private static final long serialVersionUID = 1L;
	

}
