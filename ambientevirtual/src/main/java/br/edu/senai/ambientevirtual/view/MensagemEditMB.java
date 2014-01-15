/*
* Ambiente Virtual
* Copyright (C) 2014 SENAI
* ----------------------------------------------------------------------------
* Este arquivo é parte do Sistema Ambiente Virtual.
* Finalidade - Bean de mensagem
* 
* Criado por - William Chenta
*/
package br.edu.senai.ambientevirtual.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import br.edu.senai.ambientevirtual.business.AlunoBC;
import br.edu.senai.ambientevirtual.business.MensagemBC;
import br.edu.senai.ambientevirtual.business.TurmaBC;
import br.edu.senai.ambientevirtual.business.TutorBC;
import br.edu.senai.ambientevirtual.domain.Aluno;
import br.edu.senai.ambientevirtual.domain.Grupo;
import br.edu.senai.ambientevirtual.domain.Mensagem;
import br.edu.senai.ambientevirtual.domain.Turma;
import br.edu.senai.ambientevirtual.domain.Tutor;
import br.edu.senai.ambientevirtual.domain.Usuario;
import br.edu.senai.ambientevirtual.security.InfoUsuario;
import br.gov.frameworkdemoiselle.security.RequiredRole;

@ManagedBean
@RequiredRole({"tut","alu"})
public class MensagemEditMB {

	private Mensagem mensagem;
	@Inject
	private TurmaBC turmaBC;
	@Inject
	private TutorBC tutorBC;
	@Inject
	private AlunoBC alunoBC;
	private List<Aluno> alunos;
	private List<Grupo> grupos;
	private Turma turma;
	private Tutor tutor;
	
	@Inject
	private MensagemBC mensagemBC;
	
	@Inject
	private InfoUsuario infoUsuario;
	
	public MensagemEditMB() {
		this.mensagem = new Mensagem();
	}	
	public Turma getTurma() {
		return turma;
	}
	public void setTurma(Turma turma) {
		this.turma = turma;
	}
	public List<Aluno> getAlunos() {
		return alunos;
	}
	public List<Tutor> getTutores() {
		return tutorBC.findAll();
	}
	public void setAlunos(Turma t) {
		this.alunos = t.getAlunos();
	}	
	public List<Grupo> getGrupos() {
		return grupos;
	}
	public void setGrupos(Turma t) {
		this.grupos = t.getGrupos();
	}	
	public Mensagem getMensagem() {
		return mensagem;
	}
	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}
	public List<Turma> getTurmas() {
		return turmaBC.findAll();
	}
	public Tutor getTutor() {
		return tutor;
	}
	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}
	public void changeTurma() {
		if (turma != null && !turma.equals("")) {
			alunos = turma.getAlunos();
			grupos = turma.getGrupos();
		} else {
			alunos = new ArrayList<Aluno>();
			grupos = new ArrayList<Grupo>();
		}
	}
	
	public String enviar() {
		Usuario usuario = infoUsuario.retInfo();
		
		if(usuario.getTipoUsu().equals("tut")) {
			Tutor tutor = tutorBC.loadTutor(usuario.getId());
			this.mensagem.setTutor(tutor);
			this.mensagem.setFlTutor(1);
		}
		if(usuario.getTipoUsu().equals("alu")) {
			List<Aluno> lAlunos = new ArrayList<Aluno>();
			Aluno aluno = alunoBC.loadAluno(usuario.getId());
			lAlunos.add(aluno);
			this.mensagem.setAlunos(lAlunos);
			this.mensagem.setTutor(this.tutor);
			this.mensagem.setFlTutor(0);
		}
		
		this.mensagem.setTurma(this.turma);
		this.mensagem.setData(new Date());
		this.mensagemBC.insert(this.mensagem);		
		return "mensagem_list";
	}
	
}
