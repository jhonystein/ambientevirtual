package br.edu.senai.ambientevirtual.business;

import java.util.List;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

import br.edu.senai.ambientevirtual.domain.Aluno;
import br.edu.senai.ambientevirtual.persistence.AlunoDAO;

@BusinessController
public class AlunoBC extends DelegateCrud<Aluno, Long, AlunoDAO> {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public void insert(Aluno bean) {
		System.out.println("RN");
		super.insert(bean);
	}
	
	public List<Aluno> filtrar(String filtro, String valor) {
		
		return null;
	}
	
}
