package br.edu.senai.ambientevirtual.persistence;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.edu.senai.ambientevirtual.domain.Aluno;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class AlunoDAO extends JPACrud<Aluno, Long> {

	private static final long serialVersionUID = 1L;
	
	public List<Aluno> filtrar(String filtro, String valor) {
		TypedQuery<Aluno> busca;
		if (filtro.equals("matricula")) {
			busca = getEntityManager().createQuery(
					"select a from Aluno a where :filtro like :valor", getBeanClass());
			busca.setParameter("filtro", "a."+filtro);
		} else {
			busca = getEntityManager().createQuery(
					"select a from Aluno a where :filtro like :valor", getBeanClass());
			busca.setParameter("filtro", "a.usuario."+filtro);
		}
		busca.setParameter("valor", "%"+valor+"%");
		return busca.getResultList();
	}
}
