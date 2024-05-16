package com.example.demo.model;

import java.sql.Date;

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
@Table(name = "ordemservico")
public class OrdemServico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "os_id")
	private Long id;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	@Column(name = "os_datahorainicio")
	@NotNull
	private Date dataHoraInicio;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	@Column(name = "os_datahoratermino")
	@NotNull
	private Date dataHoraTermino;
	
	@Column(name = "os_status")
    @Pattern(regexp = "aberto|confirmado|cancelado|em execução")
    private String status;
	
	@Column(name = "os_resp")
	@NotNull
	private String respOs;
	
	
	
	@OneToOne
	@JoinColumn(name ="os_agendamento_id")
	private Agendamento agendamento;
	
	@OneToOne
	@JoinColumn(name ="os_exeoservico_id")
	private Servicos servicos;
	
	@Column(name = "os_valor")
	@NotNull
	private float valor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataHoraInicio() {
		return dataHoraInicio;
	}

	public void setDataHoraInicio(Date dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

	public Date getDataHoraTermino() {
		return dataHoraTermino;
	}

	public void setDataHoraTermino(Date dataHoraTermino) {
		this.dataHoraTermino = dataHoraTermino;
	}

	public Agendamento getAgendamento() {
		return agendamento;
	}

	public void setAgendamento(Agendamento agendamento) {
		this.agendamento = agendamento;
	}

	//public Funcionario getFuncionario() {
		//return funcionario;
	//}

	//public void setFuncionario(Funcionario funcionario) {
		//this.funcionario = funcionario;
	//}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}
	
	

}
