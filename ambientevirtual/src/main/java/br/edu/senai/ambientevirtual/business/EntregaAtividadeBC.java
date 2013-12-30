package br.edu.senai.ambientevirtual.business;

import br.edu.senai.ambientevirtual.domain.EntregaAtividade;
import br.edu.senai.ambientevirtual.persistence.EntregaAtividadeDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class EntregaAtividadeBC extends DelegateCrud<EntregaAtividade, Long, EntregaAtividadeDAO> {
	private static final long serialVersionUID = 1L;
}
