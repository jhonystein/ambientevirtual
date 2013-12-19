package br.edu.senai.ambientevirtual.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "TB_GRUPOS")
@TableGenerator(name = "GrupoGen", table = "TB_SEQUENCIAS", pkColumnName = "NM_CHAVE", pkColumnValue = "GRUPOSEQ", valueColumnName = "DS_VALOR")
public class Grupo {

	@Id
	@Column(name="ID_GRUPO")
	@GeneratedValue(generator="GrupoGen", strategy=GenerationType.TABLE)
	private Long id;
	@Column(name="NM_GRUPO", length=50, nullable=false)
	private String nome;
	@ManyToMany
	@JoinTable(name="TB_ALUNOS_TB_GRUPOS", 
		joinColumns={@JoinColumn(referencedColumnName="ID_GRUPO")}, 
		inverseJoinColumns={@JoinColumn(referencedColumnName="ID_ALUNO")})
	private List<Aluno> alunos;
	@ManyToOne
	@JoinColumn(name="ID_TUTOR", nullable=false)
	private Tutor tutor;
	@ManyToOne
	@JoinColumn(name="ID_TURMA", nullable=false)
	private Turma turma;	

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

	public List<Aluno> getAlunos() {
		return alunos;
	}
	
	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public Tutor getTutor() {
		return tutor;
	}

	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}
	
}
