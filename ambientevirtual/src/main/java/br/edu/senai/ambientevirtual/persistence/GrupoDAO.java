/*
* Ambiente Virtual
* Copyright (C) 2014 SENAI
* ----------------------------------------------------------------------------
* Este arquivo é parte do Sistema Ambiente Virtual.
* Finalidade - DAO de grupos
* 
* Criado por - William Chenta
*/
package br.edu.senai.ambientevirtual.persistence;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.TypedQuery;

import br.edu.senai.ambientevirtual.domain.Grupo;
import br.edu.senai.ambientevirtual.domain.Usuario;
import br.edu.senai.ambientevirtual.security.InfoUsuario;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class GrupoDAO extends JPACrud<Grupo, Long> {

	private static final long serialVersionUID = 1L;
	
	@Inject
	InfoUsuario infoUsuario;

	public List<Grupo> filtrarQuery(String tpFiltro, Map<String, String> params) {
		
		String query = "";
		Usuario usuario = infoUsuario.retInfo();
		
		if(usuario.getTipoUsu().equals("tut")) {
			query = "Select g from Grupo g where g.tutor.usuario.id = :id";
		}
		if(usuario.getTipoUsu().equals("alu")) {
			query = "Select g from Grupo g, Aluno a where g member of a.grupos and a.usuario.id = :id";
		}		
		
		if ("nome".equals(tpFiltro)) {
			query += " and upper(g.nome) like upper(:nome)";
		}
		if ("turma".equals(tpFiltro)) {
			query += " and upper(g.turma.codigo) like upper(:turma)";
		}		
		if ("tutor".equals(tpFiltro)) {
			query += " and upper(g.tutor.usuario.nome) like upper(:tutor)";
		}		
		if ("todos".equals(tpFiltro)) {
			query += " and (upper(g.nome) like upper(:todos) or "
					+ "upper(g.tutor.usuario.nome) like upper(:todos) or "
					+ "upper(g.turma.codigo) like upper(:todos))";
		}
		
		TypedQuery<Grupo> filtro = getEntityManager().createQuery(query,
				getBeanClass());
		
		for (Iterator<String> iterator = params.keySet().iterator(); iterator.hasNext();) {
			String chave = iterator.next();
			
			if ("semestre".equals(tpFiltro)) {
				filtro.setParameter(chave, Integer.parseInt(params.get(chave)));
			}
			else {
				filtro.setParameter(chave, "%" + params.get(chave) + "%");
			}
		}
		
		filtro.setParameter("id", usuario.getId());
		
		params.clear();
		
		return filtro.getResultList();
	}

}
