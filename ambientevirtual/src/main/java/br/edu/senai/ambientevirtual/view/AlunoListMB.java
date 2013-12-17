package br.edu.senai.ambientevirtual.view;

import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.edu.senai.ambientevirtual.business.AlunoBC;
import br.edu.senai.ambientevirtual.domain.Aluno;

@ViewController
@NextView("./aluno_edit.jsf")
@PreviousView("./aluno_list.jsf")
public class AlunoListMB extends AbstractListPageBean<Aluno, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private AlunoBC alunoBC;
	
	@Override
	protected List<Aluno> handleResultList() {
		return this.alunoBC.findAll();
	}
	
	@Transactional
	public String deleteSelection() {
		boolean delete;
		for (Iterator<Long> iter = getSelection().keySet().iterator(); iter.hasNext();) {
			Long id = iter.next();
			delete = getSelection().get(id);
			if (delete) {
				alunoBC.delete(id);
				iter.remove();
			}
		}
		return getPreviousView();
	}

}