/*
* Ambiente Virtual
* Copyright (C) 2014 SENAI
* ----------------------------------------------------------------------------
* Este arquivo é parte do Sistema Ambiente Virtual.
* Finalidade - Model de atividades
* 
* Criado por - William Chenta
*/ 
package br.edu.senai.ambientevirtual.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "TB_ATIVIDADES")
@TableGenerator(name = "AtividadeGen", table = "TB_SEQUENCIAS", pkColumnName = "NM_CHAVE", pkColumnValue = "ATIVIDADESEQ", valueColumnName = "DS_VALOR")
public class Atividade {

	@Id
	@Column(name = "ID_ATIVIDADE")
	@GeneratedValue(generator = "AtividadeGen", strategy = GenerationType.TABLE)
	private Long id;
	@Column(name="NM_ATIVIDADE", length=100)
	private String nome;
	@Lob
	@Column(name="DS_ATIVIDADE", nullable=false)
	private String descricao;
	@ManyToOne
	@JoinColumn(name="ID_TUTOR", nullable=false)
	private Tutor tutor;	
	@ManyToMany
	@JoinTable(name="TB_ATIVIDADES_TB_GRUPOS", 
		joinColumns={@JoinColumn(referencedColumnName="ID_ATIVIDADE")}, 
		inverseJoinColumns={@JoinColumn(referencedColumnName="ID_GRUPO")})
	private List<Grupo> grupos;	
	@ManyToMany
	@JoinTable(name="TB_ATIVIDADES_TB_TURMAS", 
		joinColumns={@JoinColumn(referencedColumnName="ID_ATIVIDADE")}, 
		inverseJoinColumns={@JoinColumn(referencedColumnName="ID_TURMA")})
	private List<Turma> turmas;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Tutor getTutor() {
		return tutor;
	}

	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}
	
	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atividade other = (Atividade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
