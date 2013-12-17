package br.edu.senai.ambientevirtual.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name="TB_USUARIOS")
@TableGenerator(name="UsuarioGen", table="TB_SEQUENCIAS", pkColumnName="NM_CHAVE", pkColumnValue="USUARIOSEQ", valueColumnName="DS_VALOR")
public class Usuario implements Serializable {

	/**
	 * @Version: 1.0-BETA
	 */
	private static final long serialVersionUID = 5305399394845599617L;
	
	@Id
	@Column(name="ID_USUARIO")
	@GeneratedValue(generator="UsuarioGen", strategy=GenerationType.TABLE)
	private Long id;
	@Column(name="DS_LOGIN", length=20, unique=true, nullable=false)
	private String login;
	@Column(name="DS_SENHA", length=20, nullable=false)
	private String senha;
	@Column(name="NM_USUARIO", length=100, nullable=false)
	private String nome;
	@Column(name="DS_EMAIL", length=100)
	private String email;
	@Column(name="DS_TELEFONE", length=20)
	private String telefone;
	@Enumerated(EnumType.STRING)
	@Column(name="DS_SEXO", length=15, nullable=false)
	private Sexo sexo;
	@Column(name="NR_RG", length=30)
	private String rg;
	@Column(name="NR_CPF", length=30)
	private String cpf;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
