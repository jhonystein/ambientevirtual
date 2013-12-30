package br.edu.senai.ambientevirtual.view;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import br.edu.senai.ambientevirtual.business.AlunoBC;
import br.edu.senai.ambientevirtual.business.AtividadeBC;
import br.edu.senai.ambientevirtual.business.EntregaAtividadeBC;
import br.edu.senai.ambientevirtual.domain.Aluno;
import br.edu.senai.ambientevirtual.domain.Atividade;
import br.edu.senai.ambientevirtual.domain.EntregaAtividade;

@ManagedBean
@SessionScoped
public class EntregaAtividadeEditMB {

	@Inject
	private EntregaAtividadeBC entregaAtividadeBC;
	
	@Inject
	private AlunoBC alunoBC;
	
	@Inject
	private AtividadeBC atividadeBC;
	private String prmIdAtividade;
	private Atividade atividade;
	private Aluno aluno;
	private String resolucao;
	private EntregaAtividade entregaAtividade;
	
	public String getResolucao() {
		return resolucao;
	}

	public void setResolucao(String resolucao) {
		this.resolucao = resolucao;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public String getPrmIdAtividade() {
		return prmIdAtividade;
	}

	public void setPrmIdAtividade(String prmIdAtividade) {
		this.prmIdAtividade = prmIdAtividade;
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	public AlunoBC getAlunoBC() {
		return alunoBC;
	}

	public void setAlunoBC(AlunoBC alunoBC) {
		this.alunoBC = alunoBC;
	}
	
	public List<Aluno> getAlunos() {
		return alunoBC.findAll();
	}
	
	public String addResAtividade() {
		this.atividade = atividadeBC.load(Long.valueOf(prmIdAtividade));		
		return "aluno_add_res_atividade";
	}
	
	public String salve() {		
		
		if(entregaAtividade == null) {
			entregaAtividade = new EntregaAtividade();
		}
		
		entregaAtividade.setAluno(aluno);
		entregaAtividade.setEntrega(new Date());
		entregaAtividade.setAtividade(atividade);
		entregaAtividade.setResolucao(resolucao);		
		entregaAtividadeBC.insert(entregaAtividade);		
		return "atividade_list";
	}	
}