package br.edu.senai.ambientevirtual.view;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import br.edu.senai.ambientevirtual.domain.Aluno;

public class AlunosMensagemTableModel extends ListDataModel<Aluno> implements SelectableDataModel<Aluno>{

	public AlunosMensagemTableModel() {}
	
	public AlunosMensagemTableModel(List<Aluno> alunos) {
		super(alunos);
	}
	
	@Override
	public Object getRowKey(Aluno aluno) {
		List<Aluno> alunos = (List<Aluno>) getWrappedData();
		return new Integer(alunos.indexOf(aluno));
	}

	@Override
	public Aluno getRowData(String rowKey) {
		List<Aluno> alunos = (List<Aluno>) getWrappedData();
		return alunos.get(Integer.parseInt(rowKey));
	}

}
