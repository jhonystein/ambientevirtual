package br.edu.senai.ambientevirtual.persistence;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import br.edu.senai.ambientevirtual.domain.Grupo;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class GrupoDAO extends JPACrud<Grupo, Long> {

	private static final long serialVersionUID = 1L;

	public List<Grupo> filtrarQuery(String tpFiltro, Map<String, String> params) {
		String query = "Select g from Grupo g where g.nome = :nome";
		
		if ("turma".equals(tpFiltro)) {
			query = "Select g from Grupo g where g.turma.codigo = :turma";
		}
		
		if ("tutor".equals(tpFiltro)) {
			query = "Select g from Grupo g where g.tutor.usuario.nome = :tutor";
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
		
		return (List<Grupo>) filtro.getResultList();
	}

}
