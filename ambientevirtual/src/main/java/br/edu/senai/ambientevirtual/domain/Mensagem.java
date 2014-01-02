package br.edu.senai.ambientevirtual.domain;

import java.util.Date;
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
@Table(name = "TB_MENSAGENS")
@TableGenerator(name = "MensagenGen", table = "TB_SEQUENCIAS", pkColumnName = "NM_CHAVE", pkColumnValue = "MENSAGEMSEQ", valueColumnName = "DS_VALOR")
public class Mensagem {
	
	@Id
	@Column(name="ID_MENSAGEM")
	@GeneratedValue(generator="MensagenGen", strategy=GenerationType.TABLE)
	private Long id;	
	@Column(name="DS_ASSUNTO", length=50, nullable=false)
	private String assunto;	
	@ManyToMany
	@JoinTable(name="TB_ALUNOS_TB_MENSAGENS", 
		joinColumns={@JoinColumn(referencedColumnName="ID_MENSAGEM")}, 
		inverseJoinColumns={@JoinColumn(referencedColumnName="ID_ALUNO")})
	private List<Aluno> alunos;	
	@ManyToOne
	@JoinColumn(name="ID_TUTOR", nullable=false)
	private Tutor tutor;
	@ManyToOne
	@JoinColumn(name="ID_TURMA", nullable=false)
	private Turma turma;
	@ManyToOne
	@JoinColumn(name="ID_GRUPO", nullable=true)
	private Grupo grupo;
	@Column(name="DT_MENSAGEM", nullable=false)
	private Date data;	
	/** 
	 * FLAG PARA INFORMAR SE MENSAGEM FOI ENVIADO POR TUTOR OU ALUNO. 
	 * SE VALOR = 1 FOI ENVIADA POR TUTOR PARA ALUNO(S) OU TURMA OU GRUPO
	 * SE VALOR = 0 FOI ENVIADA POR ALUNO PARA TUTOR
	 * */
	@Column(name="FL_TUTOR", nullable=false)
	private Integer flTutor;
}
