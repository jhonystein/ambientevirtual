package br.edu.senai.ambientevirtual.persistence;

import br.edu.senai.ambientevirtual.domain.EntregaAtividade;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class EntregaAtividadeDAO extends JPACrud<EntregaAtividade, Long> {
	private static final long serialVersionUID = 1L;
}
