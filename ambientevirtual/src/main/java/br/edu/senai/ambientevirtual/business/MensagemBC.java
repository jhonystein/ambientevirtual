package br.edu.senai.ambientevirtual.business;

import java.util.List;
import java.util.Map;

import br.edu.senai.ambientevirtual.domain.Mensagem;
import br.edu.senai.ambientevirtual.persistence.MensagemDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class MensagemBC extends DelegateCrud<Mensagem, Long, MensagemDAO> {

	private static final long serialVersionUID = 1L;
	
	public List<Mensagem> filtrarQuery(String filtro, Map<String, String> params) {
		return getDelegate().filtrarQuery(filtro, params);
	}

}
