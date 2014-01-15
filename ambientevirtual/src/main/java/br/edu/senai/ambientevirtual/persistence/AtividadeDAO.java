package br.edu.senai.ambientevirtual.persistence;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.TypedQuery;

import br.edu.senai.ambientevirtual.domain.Atividade;
import br.edu.senai.ambientevirtual.domain.Usuario;
import br.edu.senai.ambientevirtual.security.InfoUsuario;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class AtividadeDAO extends JPACrud<Atividade, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	InfoUsuario infoUsuario;

	public List<Atividade> filtrarQuery(String tpFiltro,
			Map<String, String> params) {

		String query = "";
		Usuario usuario = infoUsuario.retInfo();

		if (usuario.getTipoUsu().equals("tut")) {
			query = "Select a from Atividade a where a.tutor.usuario.id = :id";
		}
		if (usuario.getTipoUsu().equals("alu")) {
			query = "Select distinct a from Atividade a, Grupo g, Turma t, "
					+ "Aluno al where ((g member of a.grupos and g member of al.grupos)"
					+ " or (t member of a.turmas and t member of al.turmas)) "
					+ "and al.usuario.id = :id ";
		}

		if ("nome".equals(tpFiltro)) {
			query += " and upper(a.nome) like upper(:nome)";
		}
		if ("tutor".equals(tpFiltro)) {
			query = " and upper(a.tutor.usuario.nome) like upper(:tutor)";
		}
		if ("todos".equals(tpFiltro)) {
			query += " and (upper(a.tutor.usuario.nome) like upper(:todos) "
					+ "or upper(a.nome) like upper(:todos))";
		}

		TypedQuery<Atividade> filtro = getEntityManager().createQuery(query,
				getBeanClass());

		for (Iterator<String> iterator = params.keySet().iterator(); iterator
				.hasNext();) {
			String chave = iterator.next();
			filtro.setParameter(chave, "%" + params.get(chave) + "%");
		}

		filtro.setParameter("id", usuario.getId());

		params.clear();

		return filtro.getResultList();
	}

}
