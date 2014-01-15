/*
* Ambiente Virtual
* Copyright (C) 2014 SENAI
* ----------------------------------------------------------------------------
* Este arquivo é parte do Sistema Ambiente Virtual.
* Finalidade - Converter de alunos
* 
* Criado por - William Chenta
*/
package br.edu.senai.ambientevirtual.view.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.edu.senai.ambientevirtual.business.AlunoBC;
import br.edu.senai.ambientevirtual.domain.Aluno;

@FacesConverter(value = "alunoConverter")
public class AlunoConverter implements Converter {
private AlunoBC alunoBC = new AlunoBC();
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String id) {
		try {
			return this.alunoBC.load(Long.parseLong(id));
		} catch (Exception e) {
			e.getStackTrace();
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		try {
			Aluno a = (Aluno) obj;
			return a.getId() != null ? String.valueOf(a.getId()) : null;
		} catch (Exception e) {
			e.getStackTrace();
		}
		return null;
	}
}
