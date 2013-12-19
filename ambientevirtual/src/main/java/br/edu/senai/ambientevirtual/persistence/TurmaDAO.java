package br.edu.senai.ambientevirtual.persistence;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import br.edu.senai.ambientevirtual.domain.Turma;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class TurmaDAO extends JPACrud<Turma, Long> {

	private static final long serialVersionUID = 1L;
	
	public List<Turma> filtrarQuery(String tpFiltro, Map<String, String> params) {
		String query = "Select t from Turma t where t.curso = :curso";
		
		if ("semestre".equals(tpFiltro)) {
			query = "Select t from Turma t where t.semestre = :semestre";
		}		
		
		Query filtro = createQuery(query);
		
		for (Iterator<String> iterator = params.keySet().iterator(); iterator.hasNext();) {
			String chave = iterator.next();
			
			if ("semestre".equals(tpFiltro)) {
				filtro.setParameter(chave, Integer.parseInt(params.get(chave)));
			}
			else {
				filtro.setParameter(chave, params.get(chave));
			}
		}
		
		params.clear();
		
		return (List<Turma>) filtro.getResultList();
	}
	
}
