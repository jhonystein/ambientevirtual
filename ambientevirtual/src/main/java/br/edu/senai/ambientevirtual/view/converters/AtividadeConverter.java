package br.edu.senai.ambientevirtual.view.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.edu.senai.ambientevirtual.business.AtividadeBC;
import br.edu.senai.ambientevirtual.domain.Atividade;

@FacesConverter(value="atividadeConverter")
public class AtividadeConverter implements Converter {
	private AtividadeBC atividadeBC = new AtividadeBC();
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String id) {
		try {
			return this.atividadeBC.load(Long.parseLong(id));
		} catch (Exception e) {
			e.getStackTrace();
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object obj) {
		try {
			Atividade a = (Atividade) obj;
			return a.getId() != null ? String.valueOf(a.getId()) : null;
		} catch (Exception e) {
			e.getStackTrace();
		}
		return null;
	}

}
