package br.edu.senai.ambientevirtual.view.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.edu.senai.ambientevirtual.business.TutorBC;
import br.edu.senai.ambientevirtual.domain.Tutor;

@FacesConverter(value = "tutorConverter")
public class TutorConverter implements Converter {
private TutorBC tutorBC = new TutorBC();
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String id) {
		try {
			return this.tutorBC.load(Long.parseLong(id));
		} catch (Exception e) {
			e.getStackTrace();
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		try {
			Tutor t = (Tutor) obj;
			return t.getId() != null ? String.valueOf(t.getId()) : null;
		} catch (Exception e) {
			e.getStackTrace();
		}
		return null;
	}
}
