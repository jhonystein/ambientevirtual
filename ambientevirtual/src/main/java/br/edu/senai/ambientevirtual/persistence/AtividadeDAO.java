package br.edu.senai.ambientevirtual.persistence;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

import br.edu.senai.ambientevirtual.domain.Atividade;

@PersistenceController
public class AtividadeDAO extends JPACrud<Atividade, Long> {

	private static final long serialVersionUID = 1L;
	

}
