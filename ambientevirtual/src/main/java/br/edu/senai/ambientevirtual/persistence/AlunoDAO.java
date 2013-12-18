package br.edu.senai.ambientevirtual.persistence;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import br.edu.senai.ambientevirtual.domain.Aluno;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class AlunoDAO extends JPACrud<Aluno, Long> {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioDAO usuarioDAO;
	
	@Override
	public void insert(Aluno aluno) {
		System.out.println("syso");
		Logger.getLogger(AlunoDAO.class).info("insert");
		usuarioDAO.insert(aluno.getUsuario());
		super.insert(aluno);
		Logger.getLogger(AlunoDAO.class).info("Inserido");
	}

	public List<Aluno> filtrar(String filtro, String valor) {
		Query busca = createQuery("select a from Aluno a where a.:filtro = :valor");
		busca.setParameter("filtro", filtro);
		busca.setParameter("valor", valor);
		return busca.getResultList();
	}
}
