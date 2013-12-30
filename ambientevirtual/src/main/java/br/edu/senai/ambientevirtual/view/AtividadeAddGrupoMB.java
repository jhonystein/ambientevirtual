package br.edu.senai.ambientevirtual.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import br.edu.senai.ambientevirtual.business.AtividadeBC;
import br.edu.senai.ambientevirtual.business.GrupoBC;
import br.edu.senai.ambientevirtual.domain.Atividade;
import br.edu.senai.ambientevirtual.domain.Grupo;

@ManagedBean
@SessionScoped
public class AtividadeAddGrupoMB {

	@Inject
	private GrupoBC grupoBC;
	
	@Inject
	private AtividadeBC atividadeBC;
	private String parametro;
	private List<Grupo> gruposSel = new ArrayList<Grupo>();
	private Atividade atividade = new Atividade();
	public String getParametro() {
		return parametro;
	}
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
	public List<Grupo> getGruposSel() {
		return gruposSel;
	}
	public void setGruposSel(List<Grupo> gruposSel) {
		this.gruposSel = gruposSel;
	}
	public Atividade getAtividade() {
		return atividade;
	}
	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}
	
	public String outcome(){
		atividade = atividadeBC.load(Long.valueOf(parametro));

		//marca os grupos adcionados na atividade - william
		List<Grupo> lGruposAtividade = atividade.getGrupos();		
		for (Grupo grupo : lGruposAtividade) {
			gruposSel.add(grupo);
		}
		return "atividade_add_grupo";
	}
	
	public List<Grupo> getGrupos() {
		return grupoBC.findAll();
	}
	
	public String salve() {		
		atividade.setGrupos(gruposSel);
		atividadeBC.update(atividade);
		return "atividade_list";
	}
	
}
