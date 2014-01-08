package br.edu.senai.ambientevirtual.persistence;

import javax.persistence.TypedQuery;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.edu.senai.ambientevirtual.domain.Usuario;
import br.edu.senai.ambientevirtual.security.Credenciais;

@PersistenceController
public class UsuarioDAO extends JPACrud<Usuario, Long> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Busca o usuário com as dadas credenciais.
	 * 
	 * @param credenciais
	 * 				as credenciais do usuário
	 * @return o usuário encontrado ou null caso nenhum 
	 * usuário tenha sido encontrado
	 */
	public Usuario buscaUsuaroCredencial(Credenciais credenciais) {

		String jpql = "select u from Usuario u where u.login = :login and u.senha = :senha";
		TypedQuery<Usuario> busca = getEntityManager().createQuery(jpql,
				getBeanClass());
		busca.setParameter("login", credenciais.getNome());
		busca.setParameter("senha", credenciais.getSenha());
		
		try {
			return busca.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

}
