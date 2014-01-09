package br.edu.senai.ambientevirtual.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import br.edu.senai.ambientevirtual.business.AtividadeBC;
import br.edu.senai.ambientevirtual.business.TurmaBC;
import br.edu.senai.ambientevirtual.domain.Atividade;
import br.edu.senai.ambientevirtual.domain.Turma;
import br.gov.frameworkdemoiselle.security.RequiredRole;

@ManagedBean
@SessionScoped
@RequiredRole("adm")
public class AtividadeAddTurmaMB {
	
	@Inject
	private TurmaBC turmaBC;
	
	@Inject
	private AtividadeBC atividadeBC;
	private String parametro;
	private List<Turma> turmasSel = new ArrayList<Turma>();
	private Atividade atividade = new Atividade();
	
	public String getParametro() {
		return parametro;
	}
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
	public List<Turma> getTurmasSel() {
		return turmasSel;
	}
	public void setTurmasSel(List<Turma> turmasSel) {
		this.turmasSel = turmasSel;
	}
	public Atividade getAtividade() {
		return atividade;
	}
	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}
	
	public String outcome(){
		atividade = atividadeBC.load(Long.valueOf(parametro));
		//marca as turmas adcionadas na atividade - william
		List<Turma> lTurmasAtividade = atividade.getTurmas();		
		for (Turma turma : lTurmasAtividade) {
			turmasSel.add(turma);
		}
		return "atividade_add_turma";
	}
	
	public List<Turma> getturmas() {
		return turmaBC.findAll();
	}
	
	public String salve() {		
		atividade.setTurmas(turmasSel);
		atividadeBC.update(atividade);
		return "atividade_list";
	}
	
}
