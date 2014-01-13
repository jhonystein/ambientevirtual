package br.edu.senai.ambientevirtual.persistence;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.TypedQuery;

import br.edu.senai.ambientevirtual.domain.Atividade;
import br.edu.senai.ambientevirtual.domain.Usuario;
import br.gov.frameworkdemoiselle.security.SecurityContext;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class AtividadeDAO extends JPACrud<Atividade, Long> {

	private static final long serialVersionUID = 1L;
	@Inject
	SecurityContext securityContext;
	
	@Inject
	UsuarioDAO usuarioDAO; 
	
	public List<Atividade> filtrarQuery(String tpFiltro,
			Map<String, String> params) {

		String queryCompl = "";
		Long id = Long.valueOf(securityContext.getUser().getId());
		Usuario usuario = usuarioDAO.load(id);
		
		if(usuario.getTipoUsu().equals("tut")) {
			queryCompl = "1=1 and a.tutor.usuario.id = :id";
		}
		if(usuario.getTipoUsu().equals("alu")) {
			queryCompl = "1=1";
		}
		
		String query = "Select a from Atividade a where " + queryCompl;

		if ("nome".equals(tpFiltro)) {
			query = "Select a from Atividade a where upper(a.nome) like upper(:nome) and " + queryCompl;
		}
		if ("tutor".equals(tpFiltro)) {
			query = "Select a from Atividade a where upper(a.tutor.usuario.nome) like upper(:tutor) and " + queryCompl;
		}
		if ("todos".equals(tpFiltro)) {
			query = "Select a from Atividade a where (upper(a.tutor.usuario.nome) like upper(:todos) "
					+ "or upper(a.nome) like upper(:todos)) and " + queryCompl;
		}

		TypedQuery<Atividade> filtro = getEntityManager().createQuery(query,
				getBeanClass());
		
		for (Iterator<String> iterator = params.keySet().iterator(); iterator
				.hasNext();) {
			String chave = iterator.next();
			filtro.setParameter(chave, "%" + params.get(chave) + "%");
		}
		
		if(usuario.getTipoUsu().equals("tut")) {
			filtro.setParameter("id", id);
		}

		params.clear();

		return filtro.getResultList();
	}

}
