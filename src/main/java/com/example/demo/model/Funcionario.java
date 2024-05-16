package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
@Entity
@Table(name = "funcionario")
public class Funcionario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fun_id")
	private Long id;
	
	@Column(name = "fun_login")
	@NotNull
	@Size(min = 5, max = 20)
	private String login;
	
	@Column(name = "fun_senha")
	@NotNull
	@Pattern(regexp = "(?=^.{6,}$)((?=.*\\d)(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$", message = 
	"A senha deve conter 1 letra Maiúscula, números e caracteres especiais e no mínimo 6 caracteres!")
	private String senha;
	
	@Column(name = "fun_email")
	@NotNull
	@Email
	private String email;
	
	@Column(name = "fun_fone")
	@NotNull
	@Pattern(regexp = "^(\\(\\d{2}\\)\\d{4}\\-\\d{4})|(\\(\\d{2}\\)\\d{5}\\-\\d{4})", message = "Telefone deve ter o formato= (99)9999-9999 ou (99)99999-9999")
	private String fone;
	
	@Column(name = "fun_comissao")
	@NotNull
	private Float comissao;
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFone() {
		return fone;
	}
	public void setFone(String fone) {
		this.fone = fone;
	}
	public Float getComissao() {
		return comissao;
	}
	public void setComissao(Float comissao) {
		this.comissao = comissao;
	}
	
}
