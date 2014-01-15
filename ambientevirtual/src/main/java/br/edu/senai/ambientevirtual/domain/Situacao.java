/*
* Ambiente Virtual
* Copyright (C) 2014 SENAI
* ----------------------------------------------------------------------------
* Este arquivo é parte do Sistema Ambiente Virtual.
* Finalidade - Enum de situações
* 
* Criado por - Marcelo
*/ 
package br.edu.senai.ambientevirtual.domain;

public enum Situacao {
	CHEGADA_TARDIA,
	SAIDA_ANTECIPADA,
	IRREGULAR;
	
	public String toString() {
		switch (this) {
		case CHEGADA_TARDIA:
			return "Chegada tardia";
		case SAIDA_ANTECIPADA:
			return "Saída antecipada";
		default:
			return "Irregular";
		}
		
	};
}
