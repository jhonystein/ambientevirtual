package br.edu.senai.ambientevirtual.persistence;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import br.edu.senai.ambientevirtual.domain.Tutor;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class TutorDAO extends JPACrud<Tutor, Long> {

	private static final long serialVersionUID = 1L;

	public List<Tutor> filtrarQuery(String tpFiltro, Map<String, String> params) {
		String query = "Select t from Tutor t where t.usuario.nome = :nome";
		
		if ("email".equals(tpFiltro)) {
			query = "Select t from Tutor t where t.usuario.email = :email";
		}
		
		if ("nucleo".equals(tpFiltro)) {
			query = "Select t from Tutor t where t.nucleo = :nucleo";
		}		
		
		Query filtro = createQuery(query);
		
		for (Iterator<String> iterator = params.keySet().iterator(); iterator.hasNext();) {
			String chave = iterator.next();
			filtro.setParameter(chave, params.get(chave));
		}
		
		return (List<Tutor>) filtro.getResultList();
	}
}
