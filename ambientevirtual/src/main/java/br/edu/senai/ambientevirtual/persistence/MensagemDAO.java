package br.edu.senai.ambientevirtual.persistence;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.TypedQuery;

import br.edu.senai.ambientevirtual.business.AlunoBC;
import br.edu.senai.ambientevirtual.domain.Mensagem;
import br.edu.senai.ambientevirtual.domain.Usuario;
import br.edu.senai.ambientevirtual.security.InfoUsuario;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class MensagemDAO extends JPACrud<Mensagem, Long> {

	private static final long serialVersionUID = 1L;
	@Inject
	InfoUsuario infoUsuario;
	@Inject
	AlunoBC alunoBC;

	public List<Mensagem> filtrarQuery(String tpFiltro,
			Map<String, String> params) {

		String queryCompl = "";
		Usuario usuario = infoUsuario.retInfo();

		if (usuario.getTipoUsu().equals("tut")) {
			queryCompl = "1=1 and m.tutor.usuario.id = :id";
		}
		
		String query = "Select m from Mensagem m where " + queryCompl;

		TypedQuery<Mensagem> filtro = getEntityManager().createQuery(query,
				getBeanClass());

		filtro.setParameter("id", usuario.getId());

		return filtro.getResultList();

	}

}
