package br.edu.senai.ambientevirtual.persistence;

import br.edu.senai.ambientevirtual.domain.Mensagem;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class MensagemDAO extends JPACrud<Mensagem, Long> {

	private static final long serialVersionUID = 1L;

}
