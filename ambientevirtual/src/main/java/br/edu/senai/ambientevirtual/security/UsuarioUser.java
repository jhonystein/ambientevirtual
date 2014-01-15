/*
* Ambiente Virtual
* Copyright (C) 2014 SENAI
* ----------------------------------------------------------------------------
* Este arquivo é parte do Sistema Ambiente Virtual.
* Finalidade - 
* 
* Criado por - Marcelo
*/
package br.edu.senai.ambientevirtual.security;

import br.edu.senai.ambientevirtual.domain.Sexo;
import br.edu.senai.ambientevirtual.domain.Usuario;
import br.gov.frameworkdemoiselle.security.User;

public class UsuarioUser implements User {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	
	public UsuarioUser(Usuario usuario) {
		this.usuario = usuario;
	}
	
	/**
	 * Retorna uma String contendo o id deste usuário.
	 * 
	 * @return uma String com o id do usuário.
	 */
	@Override
	public String getId() {
		return String.valueOf(usuario.getId());
	}

	/**
	 * Recupera o valor de um atributo do usuário.
	 * 
	 * @param key
	 * 				uma String contendo o nome do atributo
	 * @return o valor do atributo ou null caso 
	 * key não seja o nome de um atributo conhecido
	 */
	@Override
	public Object getAttribute(Object key) {
		String chave = (String) key;
		if (chave.equals("id")) {
			return this.usuario.getId();
		} else if (chave.equals("login")) {
			return this.usuario.getLogin(); 
		} else if (chave.equals("senha")) {
			return this.usuario.getSenha();
		} else if (chave.equals("nome")) {
			return this.usuario.getNome();
		} else if (chave.equals("email")) {
			return this.usuario.getEmail();
		} else if (chave.equals("telefone")) {
			return this.usuario.getTelefone();
		} else if (chave.equals("sexo")) {
			return this.usuario.getSexo();
		} else if (chave.equals("rg")) {
			return this.usuario.getRg();
		} else if (chave.equals("cpf")) {
			return this.usuario.getCpf();
		}
		return null;
	}

	/**
	 * Atribui valor a um atributo do usuário.
	 * 
	 * @param key
	 * 				uma String contendo o nome do atributo.
	 * @param value
	 * 				o vamor a ser atribuído ao atributo.
	 */
	@Override
	public void setAttribute(Object key, Object value) {
		String chave = (String) key;
		if (chave.equals("id")) {
			this.usuario.setId((Long) value);
		} else if (chave.equals("login")) {
			this.usuario.setLogin((String) value); 
		} else if (chave.equals("senha")) {
			this.usuario.setSenha((String) value);
		} else if (chave.equals("nome")) {
			this.usuario.setNome((String) value);
		} else if (chave.equals("email")) {
			this.usuario.setEmail((String) value);
		} else if (chave.equals("telefone")) {
			this.usuario.setTelefone((String) value);
		} else if (chave.equals("sexo")) {
			this.usuario.setSexo((Sexo) value);
		} else if (chave.equals("rg")) {
			this.usuario.setRg((String) value);
		} else if (chave.equals("cpf")) {
			this.usuario.setCpf((String) value);
		}
	
	}

}