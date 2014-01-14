package br.edu.senai.ambientevirtual.persistence;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.TypedQuery;

import br.edu.senai.ambientevirtual.domain.Acompanhamento;
import br.edu.senai.ambientevirtual.domain.Usuario;
import br.edu.senai.ambientevirtual.security.InfoUsuario;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class AcompanhamentoDAO extends JPACrud<Acompanhamento, Long> {

	private static final long serialVersionUID = 1L;
	
	@Inject
	InfoUsuario infoUsuario;
	
	public List<Acompanhamento> filtrar(String tipoFiltro, String valorFiltro) {
		TypedQuery<Acompanhamento> busca;
		
		Usuario usuario = infoUsuario.retInfo();
		
		busca = getEntityManager().createQuery(
				"select a from Acompanhamento a where a.tutor.usuario.id = :id", getBeanClass());
		
		if (tipoFiltro.equals("ocorrencia")) {
			busca = getEntityManager().createQuery(
					"select a from Acompanhamento a"
					+ " where a.ocorrencia = :valor and a.tutor.usuario.id = :id", getBeanClass());
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			try {
				busca.setParameter("valor", sdf.parse(valorFiltro));
			} catch (ParseException e) {
				busca.setParameter("valor", null);
				e.printStackTrace();
			}
		} else if (tipoFiltro.equals("aluno")) {
			busca = getEntityManager().createQuery(
					"select a from Acompanhamento a"
					+ " where a.aluno.usuario.nome like :valor and a.tutor.usuario.id = :id", getBeanClass());
			busca.setParameter("valor", "%"+valorFiltro+"%");
		} else if (tipoFiltro.equals("turma")) {
			busca = getEntityManager().createQuery(
					"select a from Acompanhamento a"
					+ " where a.turma.codigo like :valor and a.tutor.usuario.id = :id", getBeanClass());
			busca.setParameter("valor", "%"+valorFiltro+"%");
		} else if (tipoFiltro.equals("todos")){
			busca = getEntityManager().createQuery(
					"select a from Acompanhamento a"
					+ " where (a.ocorrencia = :data or"
					+ " a.aluno.usuario.nome like :valor or"
					+ " a.turma.codigo like :valor) and a.tutor.usuario.id = :id", getBeanClass());
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			try {
				busca.setParameter("data", sdf.parse(valorFiltro));
			} catch (ParseException e) {
				busca.setParameter("data", null);
				e.printStackTrace();
			}
			busca.setParameter("valor", "%"+valorFiltro+"%");			
		}
		
		busca.setParameter("id", usuario.getId());
		return busca.getResultList();
	}
}
