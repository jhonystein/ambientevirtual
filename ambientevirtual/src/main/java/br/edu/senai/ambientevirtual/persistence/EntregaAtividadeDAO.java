/*
* Ambiente Virtual
* Copyright (C) 2014 SENAI
* ----------------------------------------------------------------------------
* Este arquivo é parte do Sistema Ambiente Virtual.
* Finalidade - DAO de entregas de atividade
* 
* Criado por - William Chenta
*/
package br.edu.senai.ambientevirtual.persistence;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.TypedQuery;

import br.edu.senai.ambientevirtual.domain.EntregaAtividade;
import br.gov.frameworkdemoiselle.security.SecurityContext;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class EntregaAtividadeDAO extends JPACrud<EntregaAtividade, Long> {
	private static final long serialVersionUID = 1L;
	@Inject
	SecurityContext securityContext;

	public List<EntregaAtividade> filtrarQuery(String tpFiltro,
			Map<String, String> params) {
		String query = "Select e from EntregaAtividade e where e.atividade.tutor.usuario.id = :id";

		if ("atividade".equals(tpFiltro)) {
			query = "Select e from EntregaAtividade e where upper(e.atividade.nome) like upper(:atividade) "
					+ "and e.atividade.tutor.usuario.id = :id";
		}
		if ("aluno".equals(tpFiltro)) {
			query = "Select e from EntregaAtividade e where upper(e.aluno.usuario.nome) like upper(:aluno) "
					+ "and e.atividade.tutor.usuario.id = :id";
		}

		TypedQuery<EntregaAtividade> filtro = getEntityManager().createQuery(
				query, getBeanClass());

		for (Iterator<String> iterator = params.keySet().iterator(); iterator
				.hasNext();) {
			String chave = iterator.next();
			filtro.setParameter(chave, "%" + params.get(chave) + "%");
		}

		Long id = Long.valueOf(securityContext.getUser().getId());
		filtro.setParameter("id", id);

		params.clear();

		return filtro.getResultList();
	}
}
