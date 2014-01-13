package br.edu.senai.ambientevirtual.persistence;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.TypedQuery;

import br.edu.senai.ambientevirtual.domain.Grupo;
import br.edu.senai.ambientevirtual.domain.Usuario;
import br.edu.senai.ambientevirtual.security.InfoUsuario;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class GrupoDAO extends JPACrud<Grupo, Long> {

	private static final long serialVersionUID = 1L;
	
	@Inject
	InfoUsuario infoUsuario;

	public List<Grupo> filtrarQuery(String tpFiltro, Map<String, String> params) {
		
		String queryCompl = "";
		Usuario usuario = infoUsuario.retInfo();
		
		if(usuario.getTipoUsu().equals("tut")) {
			queryCompl = "1=1 and g.tutor.usuario.id = :id";
		}
		if(usuario.getTipoUsu().equals("alu")) {
			queryCompl = "1=1";
		}
		
		String query = "Select g from Grupo g where " + queryCompl;
		
		if ("nome".equals(tpFiltro)) {
			query = "Select g from Grupo g where upper(g.nome) like upper(:nome) and " + queryCompl;
		}
		if ("turma".equals(tpFiltro)) {
			query = "Select g from Grupo g where upper(g.turma.codigo) like upper(:turma) and " + queryCompl;
		}		
		if ("tutor".equals(tpFiltro)) {
			query = "Select g from Grupo g where upper(g.tutor.usuario.nome) like upper(:tutor) and " + queryCompl;
		}		
		if ("todos".equals(tpFiltro)) {
			query = "Select g from Grupo g where (upper(g.nome) like upper(:todos) or "
					+ "upper(g.tutor.usuario.nome) like upper(:todos) or "
					+ "upper(g.turma.codigo) like upper(:todos)) and " + queryCompl;
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
		
		if(usuario.getTipoUsu().equals("tut")) {
			filtro.setParameter("id", usuario.getId());
		}
		
		params.clear();
		
		return filtro.getResultList();
	}

}
