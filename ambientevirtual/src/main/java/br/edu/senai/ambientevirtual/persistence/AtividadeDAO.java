package br.edu.senai.ambientevirtual.persistence;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import br.edu.senai.ambientevirtual.domain.Atividade;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class AtividadeDAO extends JPACrud<Atividade, Long> {

	private static final long serialVersionUID = 1L;

	public List<Atividade> filtrarQuery(String tpFiltro,
			Map<String, String> params) {
		String query = "Select a from Atividade a where upper(a.nome) like upper(:nome)";

		if ("tutor".equals(tpFiltro)) {
			query = "Select a from Atividade a where upper(a.tutor.usuario.nome) like upper(:tutor)";
		}
		if ("todos".equals(tpFiltro)) {
			query = "Select a from Atividade a where (upper(a.tutor.usuario.nome) like upper(:todos) "
					+ "or upper(a.nome) like upper(:todos))";
		}

		Query filtro = createQuery(query);

		for (Iterator<String> iterator = params.keySet().iterator(); iterator
				.hasNext();) {
			String chave = iterator.next();
			filtro.setParameter(chave, "%" + params.get(chave) + "%");
		}

		params.clear();

		return (List<Atividade>) filtro.getResultList();
	}

}
