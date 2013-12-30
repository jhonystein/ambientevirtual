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
import javax.persistence.OneToMany;
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
	
	@ManyToMany(mappedBy="atividades")
	private List<Grupo> grupos;
	

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

}
