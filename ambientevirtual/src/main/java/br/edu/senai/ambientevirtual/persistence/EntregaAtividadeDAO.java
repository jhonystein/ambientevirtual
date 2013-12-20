package br.edu.senai.ambientevirtual.persistence;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

import br.edu.senai.ambientevirtual.domain.Aluno;
import br.edu.senai.ambientevirtual.domain.EntregaAtividade;
import br.edu.senai.ambientevirtual.domain.Sexo;
import br.edu.senai.ambientevirtual.domain.Usuario;

@PersistenceController
public class EntregaAtividadeDAO extends JPACrud<EntregaAtividade, Long> {

	private static final long serialVersionUID = 1L;
	
	@Inject
	AlunoDAO alunoDAO;
	
	@Override
	public void insert(EntregaAtividade entity) {
		
		//Aluno de teste
		Aluno aluno = new Aluno();
		Usuario usuario = new Usuario();
		usuario.setNome("Eduardo Flores");
		usuario.setLogin("eduardo" + (int)(Math.random()*100));
		usuario.setSenha("123");
		usuario.setSexo(Sexo.MASCULINO);
		aluno.setUsuario(usuario);
		aluno.setMatricula("PRONATEC M1");
		alunoDAO.insert(aluno);
		entity.setAluno(aluno);
		
		super.insert(entity);
	}

}
