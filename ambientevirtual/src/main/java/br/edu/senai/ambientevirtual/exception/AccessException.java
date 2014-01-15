/*
* Ambiente Virtual
* Copyright (C) 2014 SENAI
* ----------------------------------------------------------------------------
* Este arquivo é parte do Sistema Ambiente Virtual.
* Finalidade - Tratamento de exceções de acesso.
* 
* Criado por - William Chenta
*/ 
package br.edu.senai.ambientevirtual.exception;

import br.gov.frameworkdemoiselle.exception.ApplicationException;
import br.gov.frameworkdemoiselle.message.SeverityType;

@ApplicationException(severity = SeverityType.INFO)
public class AccessException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public AccessException() {
		super("");
	}
	
}
