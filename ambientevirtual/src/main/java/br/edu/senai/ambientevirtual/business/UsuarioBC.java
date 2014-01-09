package br.edu.senai.ambientevirtual.business;

import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.edu.senai.ambientevirtual.domain.Sexo;
import br.edu.senai.ambientevirtual.domain.Usuario;
import br.edu.senai.ambientevirtual.persistence.UsuarioDAO;
import br.edu.senai.ambientevirtual.security.Credenciais;

@BusinessController
public class UsuarioBC extends DelegateCrud<Usuario, Long, UsuarioDAO> {
	
	private static final long serialVersionUID = 1L;
	
	public Usuario buscaUsuarioCredenciais(Credenciais credenciais) {
		return getDelegate().buscaUsuaroCredencial(credenciais);
	}	
	
	/**
	 * William
	 * Conta de Usuário inicial do sistema - administrador
	 * */
	@Startup
	@Transactional
	public void load() {
		if (findAll().isEmpty()) {
			Usuario usu = new Usuario();
			usu.setNome("Administrador");
			usu.setLogin("adm");
			usu.setSenha("123");
			usu.setTipoUsu("adm");
			usu.setSexo(Sexo.MASCULINO);
			insert(usu);
		}
	}	
}
