package br.edu.senai.ambientevirtual.view.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.edu.senai.ambientevirtual.business.TurmaBC;
import br.edu.senai.ambientevirtual.domain.Turma;

@FacesConverter(value = "turmaConverter")
public class TurmaConverter implements Converter {
private TurmaBC turmaBC = new TurmaBC();
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String id) {
		try {
			return this.turmaBC.load(Long.parseLong(id));
		} catch (Exception e) {
			e.getStackTrace();
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		try {
			Turma t = (Turma) obj;
			return t.getId() != null ? String.valueOf(t.getId()) : null;
		} catch (Exception e) {
			e.getStackTrace();
		}
		return null;
	}
}
