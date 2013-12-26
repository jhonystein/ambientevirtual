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
public class grupoAddAtividadeMB {
	@Inject
	private GrupoBC grupoBC;
	
	@Inject
	private AtividadeBC atividadeBC;
	private String parametro;
	private List<Atividade> atividadesSel = new ArrayList<Atividade>();
	Grupo grupo = new Grupo();

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public List<Atividade> getAtividadesSel() {
		return atividadesSel;
	}

	public void setAtividadesSel(List<Atividade> atividadesSel) {
		this.atividadesSel = atividadesSel;
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
		List<Atividade> lAtividadesGrupo = grupo.getAtividades();		
		for (Atividade atividade : lAtividadesGrupo) {
			atividadesSel.add(atividade);
		}
		return "grupo_add_atividade";
	}
	
	public List<Atividade> getAtividades() {
		return atividadeBC.findAll();
	}
	
	public String salve() {		
		grupo.setAtividades(atividadesSel);
		grupoBC.update(grupo);
		return "grupo_list";
	}
}
