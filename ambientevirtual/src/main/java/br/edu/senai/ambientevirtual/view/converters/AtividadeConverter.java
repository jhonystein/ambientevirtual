package br.edu.senai.ambientevirtual.view.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.edu.senai.ambientevirtual.domain.Atividade;

@FacesConverter(forClass=Atividade.class, value="atividadeConverter")
public class AtividadeConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		return ((Atividade)value).getNome();
	}

}
