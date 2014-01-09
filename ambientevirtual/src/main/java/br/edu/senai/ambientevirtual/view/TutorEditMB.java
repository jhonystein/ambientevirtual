package br.edu.senai.ambientevirtual.view;

import javax.inject.Inject;

import br.edu.senai.ambientevirtual.business.TutorBC;
import br.edu.senai.ambientevirtual.business.UsuarioBC;
import br.edu.senai.ambientevirtual.domain.Sexo;
import br.edu.senai.ambientevirtual.domain.Tutor;
import br.edu.senai.ambientevirtual.domain.Usuario;
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
	
	public String printIdUsuario() {		
		String idUsu = this.tutorBC.getSecurityContext().getUser().getId();
		Usuario usu = this.usuarioBC.load(Long.valueOf(idUsu));		
		return usu.getNome();
	}

}