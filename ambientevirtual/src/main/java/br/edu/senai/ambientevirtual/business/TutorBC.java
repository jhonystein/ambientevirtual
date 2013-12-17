package br.edu.senai.ambientevirtual.business;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

import br.edu.senai.ambientevirtual.domain.Tutor;
import br.edu.senai.ambientevirtual.persistence.TutorDAO;

@BusinessController
public class TutorBC extends DelegateCrud<Tutor, Long, TutorDAO> {
	
	private static final long serialVersionUID = 1L;
	
}
