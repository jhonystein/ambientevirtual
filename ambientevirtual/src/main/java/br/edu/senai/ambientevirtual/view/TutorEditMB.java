package br.edu.senai.ambientevirtual.view;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.edu.senai.ambientevirtual.business.TutorBC;
import br.edu.senai.ambientevirtual.business.UsuarioBC;
import br.edu.senai.ambientevirtual.domain.Sexo;
import br.edu.senai.ambientevirtual.domain.Tutor;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@ViewController
@PreviousView("./tutor_list.jsf")
public class TutorEditMB extends AbstractEditPageBean<Tutor, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private TutorBC tutorBC;
	
	@Inject
	private UsuarioBC usuarioBC;
	
	@Inject
	private FacesContext facesContext;
	
	public Sexo[] getSexoValues() {
		return Sexo.values();
	}

	@Override
	@Transactional
	public String delete() {
		this.tutorBC.delete(getId());
		return getPreviousView();
	}

	@Override
	@Transactional
	public String insert() {
		getBean().getUsuario().setTipoUsu("tut");
		this.tutorBC.insert(getBean());
		return getPreviousView();
	}

	@Override
	@Transactional
	public String update() {
		getBean().getUsuario().setTipoUsu("tut");
		this.tutorBC.update(getBean());
		return getPreviousView();
	}

	@Override
	protected void handleLoad() {
		setBean(this.tutorBC.load(getId()));
	}
	
	@Transactional
	public void checaLogin() {
		if (usuarioBC.existeLogin(this.getBean().getUsuario().getLogin())) {
			facesContext.addMessage("login", new FacesMessage(
					FacesMessage.SEVERITY_WARN, "Login já existente. Tente um login diferente.", null));
			getBean().getUsuario().setLogin("");
		} else {
			facesContext.addMessage("login", new FacesMessage("OK"));
		}
	}
	
}