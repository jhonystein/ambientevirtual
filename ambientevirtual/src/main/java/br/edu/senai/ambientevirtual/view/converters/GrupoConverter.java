package br.edu.senai.ambientevirtual.view.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.edu.senai.ambientevirtual.business.GrupoBC;
import br.edu.senai.ambientevirtual.domain.Grupo;

@FacesConverter(value = "grupoConverter")
public class GrupoConverter implements Converter {
	private GrupoBC grupoBC = new GrupoBC();
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String id) {
		try {
			return this.grupoBC.load(Long.parseLong(id));
		} catch (Exception e) {
			e.getStackTrace();
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object obj) {
		try {
			Grupo g = (Grupo) obj;
			return g.getId() != null ? String.valueOf(g.getId()) : null;
		} catch (Exception e) {
			e.getStackTrace();
		}
		return null;
	}
	
}
