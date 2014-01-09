package br.edu.senai.ambientevirtual.security;

import javax.inject.Inject;

import br.edu.senai.ambientevirtual.business.UsuarioBC;
import br.edu.senai.ambientevirtual.domain.Usuario;
import br.edu.senai.ambientevirtual.exception.LoginException;
import br.gov.frameworkdemoiselle.security.Authenticator;
import br.gov.frameworkdemoiselle.security.User;

public class Autenticador implements Authenticator {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Credenciais credenciais;
	
	@Inject
	private UsuarioBC usuarioBC;
	
	private Usuario usuario = null;
	
	@Override
	public boolean authenticate() {
		boolean autenticado = false;
		
		if(!(credenciais.getNome() == null) && !(credenciais.getSenha() == null)) {
			usuario = usuarioBC.buscaUsuarioCredenciais(credenciais);
		} 
		
		if(usuario != null) {
			autenticado = true;
		}
		else {			
			throw new LoginException();
		}
		return autenticado;
	}

	@Override
	public User getUser() {
		
		//se nao passou pela tela de login nem tenta logar
		if(!(credenciais.getNome() == null) && !(credenciais.getSenha() == null)) {
			usuario = usuarioBC.buscaUsuarioCredenciais(credenciais);
		} 
		
		//se n autenticou, volta para a tela de login
		if(usuario == null) {
			return null;
		}
		
		// TODO Auto-generated method stub
		return new User() {

			private static final long serialVersionUID = 1L;

			@Override
			public Object getAttribute(Object arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getId() {
				// TODO Auto-generated method stub
				return usuario.getId().toString();
			}

			@Override
			public void setAttribute(Object arg0, Object arg1) {
				// TODO Auto-generated method stub
				
			}
		};
	}

	@Override
	public void unAuthenticate() {}
	
}
