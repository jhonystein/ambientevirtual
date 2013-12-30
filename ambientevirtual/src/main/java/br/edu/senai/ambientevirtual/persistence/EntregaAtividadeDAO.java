package br.edu.senai.ambientevirtual.persistence;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import br.edu.senai.ambientevirtual.domain.EntregaAtividade;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class EntregaAtividadeDAO extends JPACrud<EntregaAtividade, Long> {
	private static final long serialVersionUID = 1L;
	
	
	public List<EntregaAtividade> filtrarQuery(String tpFiltro, Map<String, String> params) {
		String query = "Select e from EntregaAtividade e where e.atividade.nome = :atividade";
		
		if ("aluno".equals(tpFiltro)) {
			query = "Select e from EntregaAtividade e where e.aluno.usuario.nome = :aluno";
		}		
		
		Query filtro = createQuery(query);
		
		for (Iterator<String> iterator = params.keySet().iterator(); iterator.hasNext();) {
			String chave = iterator.next();
			filtro.setParameter(chave, params.get(chave));
		}
		
		params.clear();
		
		return (List<EntregaAtividade>) filtro.getResultList();
	}
}
