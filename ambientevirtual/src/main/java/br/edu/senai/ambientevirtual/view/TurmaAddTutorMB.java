package br.edu.senai.ambientevirtual.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import br.edu.senai.ambientevirtual.business.TurmaBC;
import br.edu.senai.ambientevirtual.business.TutorBC;
import br.edu.senai.ambientevirtual.domain.Turma;
import br.edu.senai.ambientevirtual.domain.Tutor;

@ManagedBean
@SessionScoped
public class TurmaAddTutorMB {

	@Inject
	private TurmaBC turmaBC;
	
	@Inject
	private TutorBC tutorBC;
	
	private List<Tutor> tutor = new ArrayList<Tutor>();
	
	private String parametro;
	
	Turma turma = new Turma();
	
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
		return "turma_add_tutor";
	}
	
	public List<Tutor> getTutor() {
		return tutor;
	}

	public void setTutor(List<Tutor> tutor) {
		this.tutor = tutor;
	}

	public List<Tutor> getTutores() {
		return tutorBC.findAll();
	}
	
	public String salve() {		
		turma.setTutores(tutor);
		turmaBC.update(turma);
		return "turma_list";
	}
}
