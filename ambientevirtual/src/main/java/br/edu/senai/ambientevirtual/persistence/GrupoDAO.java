package br.edu.senai.ambientevirtual.persistence;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;

import br.edu.senai.ambientevirtual.domain.Grupo;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class GrupoDAO extends JPACrud<Grupo, Long> {

	private static final long serialVersionUID = 1L;

	public List<Grupo> filtrarQuery(String tpFiltro, Map<String, String> params) {
		String query = "Select g from Grupo g where upper(g.nome) like upper(:nome)";
		
		if ("turma".equals(tpFiltro)) {
			query = "Select g from Grupo g where upper(g.turma.codigo) like upper(:turma)";
		}		
		if ("tutor".equals(tpFiltro)) {
			query = "Select g from Grupo g where upper(g.tutor.usuario.nome) like upper(:tutor)";
		}		
		if ("todos".equals(tpFiltro)) {
			query = "Select g from Grupo g where (upper(g.nome) like upper(:todos) or "
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
		
		params.clear();
		
		return filtro.getResultList();
	}

}
