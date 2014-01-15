/*
* Ambiente Virtual
* Copyright (C) 2014 SENAI
* ----------------------------------------------------------------------------
* Este arquivo é parte do Sistema Ambiente Virtual.
* Finalidade - Bean de turma/aluno
* 
* Criado por - William Chenta
*/
package br.edu.senai.ambientevirtual.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import br.edu.senai.ambientevirtual.business.AlunoBC;
import br.edu.senai.ambientevirtual.business.TurmaBC;
import br.edu.senai.ambientevirtual.domain.Aluno;
import br.edu.senai.ambientevirtual.domain.Turma;
import br.gov.frameworkdemoiselle.security.RequiredRole;

@ManagedBean
@SessionScoped
@RequiredRole("adm")
public class TurmaAddAlunoMB {

	@Inject
	private TurmaBC turmaBC;
	
	@Inject
	private AlunoBC alunoBC;
	private String parametro;
	private List<Aluno> alunosSel = new ArrayList<Aluno>();
	Turma turma = new Turma();

	public List<Aluno> getAlunosSel() {
		return alunosSel;
	}

	public void setAlunosSel(List<Aluno> alunosSel) {
		this.alunosSel = alunosSel;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public String getParametro() {
		return parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	public String outcome(){
		turma = turmaBC.load(Long.valueOf(parametro));

		//marca os alunos adcionados na turma - william
		List<Aluno> lAlunosTurma = turma.getAlunos();		
		for (Aluno aluno : lAlunosTurma) {
			alunosSel.add(aluno);
		}
		return "turma_add_aluno";
	}
	
	public List<Aluno> getAlunos() {
		return alunoBC.findAll();
	}
	
	public String salve() {		
		turma.setAlunos(alunosSel);
		turmaBC.update(turma);
		return "turma_list";
	}
	
}
