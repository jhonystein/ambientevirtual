package br.edu.senai.ambientevirtual.persistence;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;

import br.edu.senai.ambientevirtual.domain.Tutor;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class TutorDAO extends JPACrud<Tutor, Long> {

	private static final long serialVersionUID = 1L;

	public List<Tutor> filtrarQuery(String tpFiltro, Map<String, String> params) {
		String query = "Select t from Tutor t where upper(t.usuario.nome) like upper(:nome)";
		
		if ("email".equals(tpFiltro)) {
			query = "Select t from Tutor t where upper(t.usuario.email) like upper(:email)";
		}		
		if ("nucleo".equals(tpFiltro)) {
			query = "Select t from Tutor t where upper(t.nucleo) like upper(:nucleo)";
		}
		if ("todos".equals(tpFiltro)) {
			query = "Select t from Tutor t where (upper(t.usuario.nome) like upper(:todos) or "
					+ "upper(t.usuario.email) like upper(:todos) or "
					+ "upper(t.nucleo) like upper(:todos))";
		}
		
		TypedQuery<Tutor> filtro = getEntityManager().createQuery(query,
				getBeanClass());
		
		for (Iterator<String> iterator = params.keySet().iterator(); iterator.hasNext();) {
			String chave = iterator.next();
			filtro.setParameter(chave, "%" + params.get(chave) + "%");
		}
		
		params.clear();
		
		return filtro.getResultList();
	}
	
	public Tutor loadTutor(Long id) {
		String query = "Select t from Tutor t where upper(t.usuario.id) = :id";
		TypedQuery<Tutor> filtro = getEntityManager().createQuery(query,
				getBeanClass());
		
		filtro.setParameter("id", id);
		
		return filtro.getSingleResult();
	}
}
