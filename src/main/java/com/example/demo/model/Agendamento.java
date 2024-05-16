package com.example.demo.model;

import java.sql.Time;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
@Entity
@Table(name = "agendamento")
public class Agendamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "agen_id")
	private Long id;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	@Column(name = "agen_data")
	@NotNull
	private Date data;
	
	@Column(name = "agen_hora")
	@NotNull
	private Time hora;
	
	@Column(name = "agen_status")
    @Pattern(regexp = "aberto|confirmado|cancelado")
    private String status;
	
	@Column(name = "agen_observacao")
	private String obsevação;
	
	
	@Column(name = "agen_endereco")
	private String endereco;
	
	@OneToOne
	@JoinColumn(name ="cli_id")
	private Cliente cliente;
	
	@OneToOne
	@JoinColumn(name = "fun_id")
	private Funcionario funcionario;
	
	@OneToOne
	@JoinColumn(name = "ser_id")
	private Servicos servicos;

	public Long getId() {
		return id;
	}
	public Servicos getServicos() {
		return servicos;
	}
	public void setServicos(Servicos servicos) {
		this.servicos = servicos;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Time getHora() {
		return hora;
	}
	public void setHora(Time hora) {
		this.hora = hora;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getObsevação() {
		return obsevação;
	}
	public void setObsevação(String obsevação) {
		this.obsevação = obsevação;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Funcionario getfuncionario() {
		return funcionario;
	}
	public void setfuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

}
