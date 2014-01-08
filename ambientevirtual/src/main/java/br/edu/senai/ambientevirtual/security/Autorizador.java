package br.edu.senai.ambientevirtual.security;

import br.edu.senai.ambientevirtual.exception.AccessException;
import br.gov.frameworkdemoiselle.security.Authorizer;

public class Autorizador implements Authorizer {

	private static final long serialVersionUID = 1L;

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

		if (arg0.equals("administrador")) {
			throw new AccessException();
		}
		
		return autorizado;
	}

}
