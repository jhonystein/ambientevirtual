package br.edu.senai.ambientevirtual.security;

import javax.inject.Inject;

import br.edu.senai.ambientevirtual.domain.Usuario;
import br.edu.senai.ambientevirtual.exception.AccessException;
import br.edu.senai.ambientevirtual.persistence.UsuarioDAO;
import br.gov.frameworkdemoiselle.security.Authorizer;
import br.gov.frameworkdemoiselle.security.SecurityContext;

public class Autorizador implements Authorizer {

	private static final long serialVersionUID = 1L;
	@Inject
	private SecurityContext securityContext;
	@Inject
	private UsuarioDAO usuarioDAO;

	@Override
	public boolean hasPermission(String arg0, String arg1) {
		boolean autorizado = true;

		if (arg0.equals("bookmark") && arg1.equals("delete")) {
			autorizado = false;
		}
		return autorizado;
	}

	@Override
	public boolean hasRole(String arg0) {
		boolean autorizado = true;

		Long id = Long.valueOf(securityContext.getUser().getId());
		Usuario usuario = usuarioDAO.load(id);
		
		if (!arg0.equals(usuario.getTipoUsu())) {
			throw new AccessException();
		}
		
		return autorizado;
	}

}
