/*
* Ambiente Virtual
* Copyright (C) 2014 SENAI
* ----------------------------------------------------------------------------
* Este arquivo é parte do Sistema Ambiente Virtual.
* Finalidade - DAO de turmas
* 
* Criado por - William Chenta
*/
package br.edu.senai.ambientevirtual.persistence;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;

import br.edu.senai.ambientevirtual.domain.Turma;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class TurmaDAO extends JPACrud<Turma, Long> {

	private static final long serialVersionUID = 1L;
	
	public List<Turma> filtrarQuery(String tpFiltro, Map<String, String> params) {
		String query = "Select t from Turma t where upper(t.curso) like upper(:curso)";
		
		if ("semestre".equals(tpFiltro)) {
			query = "Select t from Turma t where t.semestre = :semestre";
		}
		if ("codigo".equals(tpFiltro)) {
			query = "Select t from Turma t where upper(t.codigo) like upper(:codigo)";
		}
		if ("todos".equals(tpFiltro)) {
			query = "Select t from Turma t where upper(t.curso) like upper(:todos) or "
					+ "upper(t.codigo) like upper(:todos)";
		}
		
		TypedQuery<Turma> filtro = getEntityManager().createQuery(query,
				getBeanClass());
		
		for (Iterator<String> iterator = params.keySet().iterator(); iterator.hasNext();) {
			String chave = iterator.next();
			
			if ("semestre".equals(tpFiltro)) {
				try {
					filtro.setParameter(chave, Integer.parseInt(params.get(chave)));
				}
				catch(NumberFormatException n) {
					params.clear();
					return null;
				}
			}
			else {
				filtro.setParameter(chave, "%" + params.get(chave) + "%");
			}
		}
		
		params.clear();
		
		return filtro.getResultList();
	}
	
}
