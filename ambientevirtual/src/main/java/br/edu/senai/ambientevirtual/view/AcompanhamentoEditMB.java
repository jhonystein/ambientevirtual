package br.edu.senai.ambientevirtual.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.inject.Inject;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.edu.senai.ambientevirtual.business.AcompanhamentoBC;
import br.edu.senai.ambientevirtual.domain.Acompanhamento;
import br.edu.senai.ambientevirtual.domain.Situacao;

@ViewController
@PreviousView("./acompanhamento_list.jsf")
public class AcompanhamentoEditMB extends AbstractEditPageBean<Acompanhamento, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private AcompanhamentoBC acompanhamentoBC;
	
	private List<SelectItem> situacoes;
	
	public AcompanhamentoEditMB() {
		situacoes = new ArrayList<SelectItem>();
		situacoes.add(new SelectItem(Situacao.CHEGADA_TARDIA));
		situacoes.add(new SelectItem(Situacao.SAIDA_ANTECIPADA));
		situacoes.add(new SelectItem(Situacao.IRREGULAR));
	}
	
	@Override
	@Transactional
	public String delete() {
		this.acompanhamentoBC.delete(getId());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String insert() {
		System.out.println(
				"Bean A"+getBean().getAluno()+", T"+getBean().getTurma()+"Si"+getBean().getSituacao());
		
		this.acompanhamentoBC.insert(getBean());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String update() {
		this.acompanhamentoBC.update(getBean());
		return getPreviousView();
	}
	
	@Override
	protected void handleLoad() {
		setBean(this.acompanhamentoBC.load(getId()));
	}

	public List<SelectItem> getSituacoes() {
		return situacoes;
	}

	public void setSituacoes(List<SelectItem> situacoes) {
		this.situacoes = situacoes;
	}

}