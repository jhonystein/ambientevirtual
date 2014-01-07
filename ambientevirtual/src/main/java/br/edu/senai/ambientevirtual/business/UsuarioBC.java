package br.edu.senai.ambientevirtual.business;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

import br.edu.senai.ambientevirtual.domain.Usuario;
import br.edu.senai.ambientevirtual.persistence.UsuarioDAO;
import br.edu.senai.ambientevirtual.security.Credenciais;

@BusinessController
public class UsuarioBC extends DelegateCrud<Usuario, Long, UsuarioDAO> {
	
	private static final long serialVersionUID = 1L;
	
	public Usuario buscaUsuarioCredenciais(Credenciais credenciais) {
		return getDelegate().buscaUsuaroCredencial(credenciais);
	}
	
}
