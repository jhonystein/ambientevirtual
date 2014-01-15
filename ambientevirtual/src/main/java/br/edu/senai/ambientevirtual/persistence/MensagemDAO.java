/*
* Ambiente Virtual
* Copyright (C) 2014 SENAI
* ----------------------------------------------------------------------------
* Este arquivo é parte do Sistema Ambiente Virtual.
* Finalidade - DAO de mensagens
* 
* Criado por - William Chenta
*/ 
package br.edu.senai.ambientevirtual.persistence;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.TypedQuery;

import br.edu.senai.ambientevirtual.business.AlunoBC;
import br.edu.senai.ambientevirtual.domain.Mensagem;
import br.edu.senai.ambientevirtual.domain.Usuario;
import br.edu.senai.ambientevirtual.security.InfoUsuario;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class MensagemDAO extends JPACrud<Mensagem, Long> {

	private static final long serialVersionUID = 1L;
	@Inject
	InfoUsuario infoUsuario;
	@Inject
	AlunoBC alunoBC;

	public List<Mensagem> filtrarQuery(String tpFiltro,
			Map<String, String> params) {

		String query = "";
		Usuario usuario = infoUsuario.retInfo();

		if (usuario.getTipoUsu().equals("tut")) {
			query = "Select m from Mensagem m where m.tutor.usuario.id = :id";
		}
		if(usuario.getTipoUsu().equals("alu")) {
			query = "Select distinct m from Mensagem m, Grupo g, Turma t, Aluno a where "
					+ "((g member of m.grupo and g member of a.grupos)"
					+ " or (t member of m.turma and t member of a.turmas))"
					+ " and a.usuario.id = :id";
		}
		
		TypedQuery<Mensagem> filtro = getEntityManager().createQuery(query,
				getBeanClass());

		filtro.setParameter("id", usuario.getId());

		return filtro.getResultList();

	}

}
