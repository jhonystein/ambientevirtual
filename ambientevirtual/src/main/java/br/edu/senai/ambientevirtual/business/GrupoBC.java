package br.edu.senai.ambientevirtual.business;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

import br.edu.senai.ambientevirtual.domain.Grupo;
import br.edu.senai.ambientevirtual.persistence.GrupoDAO;

@BusinessController
public class GrupoBC extends DelegateCrud<Grupo, Long, GrupoDAO> {
	
	private static final long serialVersionUID = 1L;
	
}
