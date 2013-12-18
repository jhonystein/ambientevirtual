package br.edu.senai.ambientevirtual.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "TB_ALUNOS")
@TableGenerator(name = "AlunoGen", table = "TB_SEQUENCIAS", pkColumnName = "NM_CHAVE", pkColumnValue = "ALUNOSEQ", valueColumnName = "DS_VALOR")
public class Aluno {

	@Id
	@Column(name="ID_ALUNO")
	@GeneratedValue(generator="AlunoGen", strategy=GenerationType.TABLE)
	private Long id;
	@Column(name="NR_MATRICULA", length=20)
	private String matricula;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ID_USUARIO", nullable=false)
	private Usuario usuario;
	@OneToMany(cascade=CascadeType.ALL, mappedBy="aluno")
	@Column(name="ID_ACOMPANHAMENTO", nullable=true)
	private List<Acompanhamento> acompanhamentos;
	
	public Aluno() {
		usuario = new Usuario();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Acompanhamento> getAcompanhamentos() {
		return acompanhamentos;
	}
	
	public void setAcompanhamentos(List<Acompanhamento> acompanhamentos) {
		this.acompanhamentos = acompanhamentos;
	}
	
}
