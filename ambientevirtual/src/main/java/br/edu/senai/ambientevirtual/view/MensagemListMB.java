package br.edu.senai.ambientevirtual.view;

import java.util.List;

import javax.inject.Inject;

import br.edu.senai.ambientevirtual.business.MensagemBC;
import br.edu.senai.ambientevirtual.domain.Mensagem;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;

@ViewController
public class MensagemListMB extends AbstractListPageBean<Mensagem, Long> {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private MensagemBC mensagemBC;

	@Override
	protected List<Mensagem> handleResultList() {
		return mensagemBC.findAll();
	}

}
