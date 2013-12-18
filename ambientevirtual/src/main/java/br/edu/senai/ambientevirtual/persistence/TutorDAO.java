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
	
	public List<Tutor> filtrarQuery(Map<String, String> params) {
		// TODO Auto-generated method stub
		
		Query filtro = createQuery("Select t from Tutor t where t.usuario.nome = :nome");
		
		for (Iterator<String> iterator = params.keySet().iterator(); iterator.hasNext();) {
			String chave = iterator.next();
			filtro.setParameter(chave, params.get(chave));
		}
		
		return (List<Tutor>) filtro.getResultList();
	}
	
}
