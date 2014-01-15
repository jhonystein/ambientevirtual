/*
* Ambiente Virtual
* Copyright (C) 2014 SENAI
* ----------------------------------------------------------------------------
* Este arquivo é parte do Sistema Ambiente Virtual.
* Finalidade - Model de acompanhamento
* 
* Criado por - William Chenta
*/ 
package br.edu.senai.ambientevirtual.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "TB_ACOMPANHAMENTOS")
@TableGenerator(name = "AcompanhamentoGen", table = "TB_SEQUENCIAS", pkColumnName = "NM_CHAVE", pkColumnValue = "ACOMPANHAMENTOSEQ", valueColumnName = "DS_VALOR")
public class Acompanhamento {

	@Id
	@Column(name="ID_ACOMPANHAMENTO")
	@GeneratedValue(generator="AcompanhamentoGen", strategy=GenerationType.TABLE)
	private Long id;
	@ManyToOne
	@JoinColumn(name="ID_TURMA", nullable=false)
	private Turma turma;
	@ManyToOne
	@JoinColumn(name="ID_ALUNO", nullable=false)
	private Aluno aluno;	
	@ManyToOne
	@JoinColumn(name="ID_TUTOR", nullable=false)
	private Tutor tutor;	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DT_OCORRENCIA", nullable=false)
	private Date ocorrencia;	
	@Lob
	@Column(name="DS_ACOMPANHAMENTO", nullable=false)
	private String acompanhamento;
	@Enumerated(EnumType.STRING)
	private Situacao situacao;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Date getOcorrencia() {
		return ocorrencia;
	}

	public void setOcorrencia(Date ocorrencia) {
		this.ocorrencia = ocorrencia;
	}

	public String getAcompanhamento() {
		return acompanhamento;
	}

	public void setAcompanhamento(String acompanhamento) {
		this.acompanhamento = acompanhamento;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public Tutor getTutor() {
		return tutor;
	}

	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}

}
