package br.edu.senai.ambientevirtual.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import br.edu.senai.ambientevirtual.business.MensagemBC;
import br.edu.senai.ambientevirtual.business.TurmaBC;
import br.edu.senai.ambientevirtual.domain.Aluno;
import br.edu.senai.ambientevirtual.domain.Grupo;
import br.edu.senai.ambientevirtual.domain.Mensagem;
import br.edu.senai.ambientevirtual.domain.Turma;

@ManagedBean
public class MensagemEditMB {

	private Mensagem mensagem;
	@Inject
	private TurmaBC turmaBC;
	private List<Aluno> alunos;
	private List<Grupo> grupos;
	private Turma turma;
	private Grupo grupo;
	private List<Aluno> alunosSel;
	
	@Inject
	private MensagemBC mensagemBC;
	
	public MensagemEditMB() {
		this.mensagem = new Mensagem();
	}	
	public Turma getTurma() {
		return turma;
	}
	public void setTurma(Turma turma) {
		this.turma = turma;
	}
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
	public List<Aluno> getAlunos() {
		return alunos;
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
		this.mensagem.setFlTutor(1);
		this.mensagem.setTurma(this.turma);
		this.mensagem.setGrupo(this.grupo);
		this.mensagem.setAlunos(this.alunosSel);
		this.mensagem.setData(new Date());
		this.mensagemBC.insert(this.mensagem);		
		return "mensagem_list";
	}
	
}
