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
import java.util.HashMap;
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
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;

@ManagedBean
@RequiredRole({"tut","alu"})
public class MensagemEditMB extends AbstractEditPageBean<Mensagem, Long> {

	private static final long serialVersionUID = 1L;
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
	private Grupo grupo;
	private List<Aluno> lAlunos;
	
	@Inject
	private MensagemBC mensagemBC;
	
	@Inject
	private InfoUsuario infoUsuario;
	
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
	public List<Turma> getTurmas() {
		return turmaBC.filtrarQuery("", new HashMap<String, String>());
	}
	public Tutor getTutor() {
		return tutor;
	}
	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}
	public Grupo getGrupo() {
		return grupo;
	}
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}
	public List<Aluno> getlAlunos() {
		return lAlunos;
	}
	public void setlAlunos(List<Aluno> lAlunos) {
		this.lAlunos = lAlunos;
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
			getBean().setTutor(tutor);
			getBean().setFlTutor(1);
			getBean().setAlunos(lAlunos);
		}
		if(usuario.getTipoUsu().equals("alu")) {
			List<Aluno> lAlunos = new ArrayList<Aluno>();
			Aluno aluno = alunoBC.loadAluno(usuario.getId());
			lAlunos.add(aluno);
			getBean().setAlunos(lAlunos);
			getBean().setTutor(this.tutor);
			getBean().setFlTutor(0);
		}
		
		getBean().setTurma(this.turma);
		getBean().setGrupo(this.grupo);
		
		getBean().setData(new Date());
		this.mensagemBC.insert(getBean());		
		return "mensagem_list";
	}
	
	
	
	
	
	
	@Override
	public String delete() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String insert() {
		// TODO Auto-generated method stub
		return this.enviar();
	}
	@Override
	public String update() {
		// TODO Auto-generated method stub
		return this.enviar();
	}
	@Override
	protected void handleLoad() {
		// TODO Auto-generated method stub		
	}
	
}
