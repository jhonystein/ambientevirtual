package br.edu.senai.ambientevirtual.business;

import java.util.List;

import br.edu.senai.ambientevirtual.domain.Acompanhamento;
import br.edu.senai.ambientevirtual.persistence.AcompanhamentoDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class AcompanhamentoBC extends DelegateCrud<Acompanhamento, Long, AcompanhamentoDAO> {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public void insert(Acompanhamento bean) {
		getDelegate().insert(bean);
	}

	public List<Acompanhamento> filtrar(String tipoFiltro, String valorFiltro) {
		return getDelegate().filtrar(tipoFiltro, valorFiltro);
	}
}
