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
