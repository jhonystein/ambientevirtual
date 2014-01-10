package br.edu.senai.ambientevirtual.view;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.edu.senai.ambientevirtual.business.TutorBC;
import br.edu.senai.ambientevirtual.business.UsuarioBC;
import br.edu.senai.ambientevirtual.domain.Sexo;
import br.edu.senai.ambientevirtual.domain.Tutor;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.security.RequiredRole;
import br.gov.frameworkdemoiselle.security.SecurityContext;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@ViewController
@PreviousView("./tutor_list.jsf")
@RequiredRole("adm")
public class TutorEditMB extends AbstractEditPageBean<Tutor, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private TutorBC tutorBC;
	
	@Inject
	private UsuarioBC usuarioBC;
	
	@Inject
	private FacesContext facesContext;
	
	@Inject
	private SecurityContext securityContext;
	
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
	
	/**
	 * Se estiver em modo de inserção / cadastro, checa apenas se 
	 * o login já existe.
	 * 
	 * Se estiver em modo update confere primeiramente a role do usuário.
	 * Se for adm checa se o login já existe ou se ele é diferente do login 
	 * atual do usuário a ter os dados atualizados.
	 * Se não for adm checa se o login já existe ou se ele é diferente do login
	 * do usuário logado.
	 * 
	 * Se alguma das condições for verdadeira então exibe uma mensagem de 
	 * alerta e limpa o campo de login.
	 */
	@Transactional
	public void checaLogin() {
		if (!isUpdateMode()) {
			if (usuarioBC.existeLogin(this.getBean().getUsuario().getLogin())) {
				facesContext.addMessage("login", new FacesMessage(
						FacesMessage.SEVERITY_WARN, "Login já existente. Tente um login diferente.", null));
				getBean().getUsuario().setLogin("");
			} else {
				facesContext.addMessage("login", new FacesMessage("OK"));
			}
		} else {
			if (securityContext.hasRole("adm")) {
				if (usuarioBC.existeLogin(this.getBean().getUsuario().getLogin())
						&& !getBean().getUsuario().getLogin().equals(
								usuarioBC.load(getBean().getUsuario().getId()).getLogin())) {
					facesContext.addMessage("login", new FacesMessage(
							FacesMessage.SEVERITY_WARN, "Login já existente. Tente um login diferente.", null));
					getBean().getUsuario().setLogin("");
				} else {
					facesContext.addMessage("login", new FacesMessage("OK"));
				}
			} else {
				if (usuarioBC.existeLogin(this.getBean().getUsuario().getLogin())
						&& !getBean().getUsuario().getLogin().equals(
								((String) securityContext.getUser().getAttribute("login")))) {
					facesContext.addMessage("login", new FacesMessage(
							FacesMessage.SEVERITY_WARN, "Login já existente. Tente um login diferente.", null));
					getBean().getUsuario().setLogin("");
				} else {
					facesContext.addMessage("login", new FacesMessage("OK"));
				}
			}
		}
	}
	
}