package br.edu.senai.ambientevirtual.view;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import br.edu.senai.ambientevirtual.business.GrupoBC;
import br.edu.senai.ambientevirtual.domain.Aluno;
import br.edu.senai.ambientevirtual.domain.Grupo;

@ManagedBean
@SessionScoped
public class GrupoAddResAtividadeMB {

	@Inject
	private GrupoBC grupoBC;
	private String parametro;
	private Grupo grupo = new Grupo();

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public String getParametro() {
		return parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	public void outcome() {
		this.grupo = grupoBC.load(Long.valueOf(parametro));
	}

	public List<Aluno> alunosGrupo() {
		if (this.grupo != null) {
			return this.grupo.getAlunos();
		}
		return null;
	}
}
