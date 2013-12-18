package br.edu.senai.ambientevirtual.business;

import java.util.List;
import java.util.Map;

import br.edu.senai.ambientevirtual.domain.Tutor;
import br.edu.senai.ambientevirtual.persistence.TutorDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class TutorBC extends DelegateCrud<Tutor, Long, TutorDAO> {
	
	private static final long serialVersionUID = 1L;
	
	public List<Tutor> filtrarQuery(Map<String, String> params) {
		return getDelegate().filtrarQuery(params);
	}
		
}
