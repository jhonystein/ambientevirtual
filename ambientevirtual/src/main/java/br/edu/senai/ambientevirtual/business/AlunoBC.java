package br.edu.senai.ambientevirtual.business;

import java.util.List;

import br.edu.senai.ambientevirtual.domain.Aluno;
import br.edu.senai.ambientevirtual.persistence.AlunoDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class AlunoBC extends DelegateCrud<Aluno, Long, AlunoDAO> {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public void insert(Aluno bean) {
		getDelegate().insert(bean);
	}
	
	public List<Aluno> filtrar(String filtro, String valor) {
		return getDelegate().filtrar(filtro, valor);
	}
	
	public Aluno loadAluno(Long id) {
		return getDelegate().loadAluno(id);
	}	
}
