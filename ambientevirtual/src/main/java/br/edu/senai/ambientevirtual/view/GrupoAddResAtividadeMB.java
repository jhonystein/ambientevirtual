package br.edu.senai.ambientevirtual.view;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import br.edu.senai.ambientevirtual.business.AtividadeBC;
import br.edu.senai.ambientevirtual.business.GrupoBC;
import br.edu.senai.ambientevirtual.domain.Aluno;
import br.edu.senai.ambientevirtual.domain.Atividade;
import br.edu.senai.ambientevirtual.domain.Grupo;
import br.gov.frameworkdemoiselle.security.RequiredRole;

@ManagedBean
@SessionScoped
@RequiredRole("tut")
public class GrupoAddResAtividadeMB {

	@Inject
	private GrupoBC grupoBC;
	
	@Inject
	private AtividadeBC atividadeBC;
	
	private String prmIdGrupo;
	private String prmIdAtividade;
	private Grupo grupo;
	private Atividade atividade;

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	
	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	public String getPrmIdGrupo() {
		return prmIdGrupo;
	}

	public void setPrmIdGrupo(String prmIdGrupo) {
		this.prmIdGrupo = prmIdGrupo;
	}
	
	public String getPrmIdAtividade() {
		return prmIdAtividade;
	}

	public void setPrmIdAtividade(String prmIdAtividade) {
		this.prmIdAtividade = prmIdAtividade;
	}

	public String addResAtividade() {
		this.atividade = atividadeBC.load(Long.valueOf(prmIdAtividade));
		return "grupo_add_res_atividade";
	}
	
	public void loadAlunosGrupo() {
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
