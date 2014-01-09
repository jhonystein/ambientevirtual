package br.edu.senai.ambientevirtual.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import br.edu.senai.ambientevirtual.business.AlunoBC;
import br.edu.senai.ambientevirtual.business.GrupoBC;
import br.edu.senai.ambientevirtual.domain.Aluno;
import br.edu.senai.ambientevirtual.domain.Grupo;
import br.gov.frameworkdemoiselle.security.RequiredRole;

@ManagedBean
@SessionScoped
@RequiredRole("adm")
public class grupoAddAlunoMB {
	@Inject
	private GrupoBC grupoBC;
	
	@Inject
	private AlunoBC alunoBC;
	private String parametro;
	private List<Aluno> alunosSel = new ArrayList<Aluno>();
	Grupo grupo = new Grupo();

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public List<Aluno> getAlunosSel() {
		return alunosSel;
	}

	public void setAlunosSel(List<Aluno> alunosSel) {
		this.alunosSel = alunosSel;
	}

	public String getParametro() {
		return parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	public String outcome(){
		grupo = grupoBC.load(Long.valueOf(parametro));

		//marca os alunos adcionados no grupo - william
		List<Aluno> lAlunosGrupo = grupo.getAlunos();		
		for (Aluno aluno : lAlunosGrupo) {
			alunosSel.add(aluno);
		}
		return "grupo_add_aluno";
	}
	
	public List<Aluno> getAlunos() {
		return alunoBC.findAll();
	}
	
	public String salve() {		
		grupo.setAlunos(alunosSel);
		grupoBC.update(grupo);
		return "grupo_list";
	}
}
