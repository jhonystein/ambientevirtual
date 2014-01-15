/*
* Ambiente Virtual
* Copyright (C) 2014 SENAI
* ----------------------------------------------------------------------------
* Este arquivo é parte do Sistema Ambiente Virtual.
* Finalidade - Bean de acompanhamentos
* 
* Criado por - William Chenta
*/
package br.edu.senai.ambientevirtual.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.inject.Inject;

import br.edu.senai.ambientevirtual.business.AcompanhamentoBC;
import br.edu.senai.ambientevirtual.business.AlunoBC;
import br.edu.senai.ambientevirtual.business.TurmaBC;
import br.edu.senai.ambientevirtual.business.TutorBC;
import br.edu.senai.ambientevirtual.domain.Acompanhamento;
import br.edu.senai.ambientevirtual.domain.Aluno;
import br.edu.senai.ambientevirtual.domain.Situacao;
import br.edu.senai.ambientevirtual.domain.Turma;
import br.edu.senai.ambientevirtual.domain.Tutor;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.security.RequiredRole;
import br.gov.frameworkdemoiselle.security.SecurityContext;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@ViewController
@PreviousView("./acompanhamento_list.jsf")
@RequiredRole("tut")
public class AcompanhamentoEditMB extends
		AbstractEditPageBean<Acompanhamento, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private AcompanhamentoBC acompanhamentoBC;

	@Inject
	private TurmaBC turmaBC;

	@Inject
	private AlunoBC alunoBC;
	
	@Inject
	private SecurityContext securityContext;
	
	@Inject
	private TutorBC tutorBC;

	private Turma turma;

	private List<SelectItem> situacoes;
	private List<Aluno> alunos;
	private Long idAluno;

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
		super.getBean().setTurma(turma);
	}

	public Long getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(Long idAluno) {
		this.idAluno = idAluno;
	}

	public List<Turma> getTurmas() {
		return turmaBC.findAll();
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(Turma t) {
		this.alunos = t.getAlunos();
	}

	public void changeTurma() {
		if (turma != null && !turma.equals("")) {
			alunos = turma.getAlunos();
		} else {
			alunos = new ArrayList<Aluno>();
		}
	}

	public AcompanhamentoEditMB() {
		situacoes = new ArrayList<SelectItem>();
		situacoes.add(new SelectItem(Situacao.CHEGADA_TARDIA));
		situacoes.add(new SelectItem(Situacao.SAIDA_ANTECIPADA));
		situacoes.add(new SelectItem(Situacao.IRREGULAR));
	}

	@Override
	@Transactional
	public String delete() {
		this.acompanhamentoBC.delete(getId());
		return getPreviousView();
	}

	@Override
	@Transactional
	public String insert() {
		Long id = Long.valueOf(securityContext.getUser().getId());
		Tutor tutor = tutorBC.loadTutor(id);
		getBean().setTutor(tutor);		
		getBean().setAluno(alunoBC.load(idAluno));
		this.acompanhamentoBC.insert(getBean());
		return getPreviousView();
	}

	@Override
	@Transactional
	public String update() {
		Long id = Long.valueOf(securityContext.getUser().getId());
		Tutor tutor = tutorBC.loadTutor(id);
		getBean().setTutor(tutor);
		getBean().setAluno(alunoBC.load(idAluno));
		this.acompanhamentoBC.update(getBean());
		return getPreviousView();
	}

	@Override
	protected void handleLoad() {
		setBean(this.acompanhamentoBC.load(getId()));
	}

	public List<SelectItem> getSituacoes() {
		return situacoes;
	}

	public void setSituacoes(List<SelectItem> situacoes) {
		this.situacoes = situacoes;
	}

}