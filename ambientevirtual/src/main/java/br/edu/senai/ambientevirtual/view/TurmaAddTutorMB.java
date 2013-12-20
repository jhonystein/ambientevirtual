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
	private String parametro;
	private List<Tutor> tutoresSel = new ArrayList<Tutor>();
	Turma turma = new Turma();

	public List<Tutor> getTutoresSel() {
		return tutoresSel;
	}

	public void setTutoresSel(List<Tutor> tutoresSel) {
		this.tutoresSel = tutoresSel;
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

		//marca os tutores adcionados na turma - william
		List<Tutor> lTutoresTurma = turma.getTutores();		
		for (Tutor tutor : lTutoresTurma) {
			tutoresSel.add(tutor);
		}
		return "turma_add_tutor";
	}
	
	public List<Tutor> getTutores() {
		return tutorBC.findAll();
	}
	
	public String salve() {		
		turma.setTutores(tutoresSel);
		turmaBC.update(turma);
		return "turma_list";
	}
}
