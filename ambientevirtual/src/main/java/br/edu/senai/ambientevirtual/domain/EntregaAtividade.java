package br.edu.senai.ambientevirtual.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TB_ENTREGAS_ATIVIDADE")
@TableGenerator(name = "EntregaAtividadeGen", table = "TB_SEQUENCIAS", pkColumnName = "NM_CHAVE", pkColumnValue = "ENTREGAATIVIDADESEQ", valueColumnName = "DS_VALOR")
public class EntregaAtividade {

	@Id
	@Column(name = "ID_ENTREGA_ATIVIDADE")
	@GeneratedValue(generator = "EntregaAtividadeGen", strategy = GenerationType.TABLE)
	private Long id;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DT_ENTREGA", nullable=false)
	private Date entrega;
	@Lob
	@Column(name="DS_ENTREGA", nullable=false)
	private String resolucao;
	@ManyToOne
	@JoinColumn(name="ID_ATIVIDADE", nullable=false)
	private Atividade atividade;
	@ManyToOne
	@JoinColumn(name="ID_ALUNO", nullable=false)
	private Aluno aluno;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getEntrega() {
		return entrega;
	}

	public void setEntrega(Date entrega) {
		this.entrega = entrega;
	}

	public String getResolucao() {
		return resolucao;
	}

	public void setResolucao(String resolucao) {
		this.resolucao = resolucao;
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

}
