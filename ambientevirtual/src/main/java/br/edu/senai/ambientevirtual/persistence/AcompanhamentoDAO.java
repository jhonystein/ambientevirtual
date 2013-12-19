package br.edu.senai.ambientevirtual.persistence;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.TypedQuery;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

import br.edu.senai.ambientevirtual.domain.Acompanhamento;
import br.edu.senai.ambientevirtual.domain.Aluno;
import br.edu.senai.ambientevirtual.domain.Sexo;
import br.edu.senai.ambientevirtual.domain.Turma;
import br.edu.senai.ambientevirtual.domain.Usuario;

@PersistenceController
public class AcompanhamentoDAO extends JPACrud<Acompanhamento, Long> {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private AlunoDAO alunoDAO;
	
	@Inject
	private TurmaDAO turmaDAO;
	
	@Override
	public void insert(Acompanhamento entity) {
		//Teste de inserção de acompanhamento com aluno e turma fixos.
		Aluno aluno = new Aluno();
		Usuario usuario = new Usuario();
		usuario.setNome("Eduardo Flores");
		usuario.setLogin("eduardo" + (int)(Math.random()*100));
		usuario.setSenha("123");
		usuario.setSexo(Sexo.MASCULINO);
		aluno.setUsuario(usuario);
		aluno.setMatricula("PRONATEC M1");
		alunoDAO.insert(aluno);
		Turma turma = new Turma();
		turma.setCurso("PRONATEC Programação");
		turma.setSemestre(1);
		turmaDAO.insert(turma);
		entity.setAluno(aluno);
		entity.setTurma(turma);
		
		super.insert(entity);
	}

	public List<Acompanhamento> filtrar(String tipoFiltro, String valorFiltro) {
		TypedQuery<Acompanhamento> busca;
		if (tipoFiltro.equals("ocorrencia")) {
			busca = getEntityManager().createQuery(
					"select a from Acompanhamento"
					+ " where a.ocorrencia = :valor", getBeanClass());
			
		} else if (tipoFiltro.equals("aluno")) {
			busca = getEntityManager().createQuery(
					"select a from Acompanhamento a"
					+ " where a.aluno.usuario.nome like :valor", getBeanClass());
			
		} else if (tipoFiltro.equals("turma")) {
			busca = getEntityManager().createQuery(
					"select a from Acompanhamento a"
					+ " where a.turma.codigo like :valor", getBeanClass());
			
		} else {
			busca = getEntityManager().createQuery(
					"select a from Acompanhamento a"
					+ " where a.ocorrencia = :data or"
					+ " a.aluno.usuario.nome like :valor or"
					+ " a.turma.codigo like :valor", getBeanClass());
			busca.setParameter("data", valorFiltro);
		}
		busca.setParameter("valor", valorFiltro);
		return busca.getResultList();
	}
}
