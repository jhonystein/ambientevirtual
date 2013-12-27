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
	private String prmIdGrupo;
	private Grupo grupo;

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	
	public String getPrmIdGrupo() {
		return prmIdGrupo;
	}

	public void setPrmIdGrupo(String prmIdGrupo) {
		this.prmIdGrupo = prmIdGrupo;
	}

	public void outcome() {
		this.grupo = grupoBC.load(Long.valueOf(prmIdGrupo));
		getAlunosGrupo();
	}

	public List<Aluno> getAlunosGrupo() {
		if (this.grupo != null) {
			return this.grupo.getAlunos();
		}
		return null;
	}
}
