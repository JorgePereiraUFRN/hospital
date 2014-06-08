package br.ufrn.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Evento {

	@Id
	@GeneratedValue
	private Long id;
	private String descricao;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar data;
	@ManyToOne 
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;
	
	public Evento() {
		
	}
	
	public Evento(String descricao, Calendar data, Paciente paciente) {
		this.descricao = descricao;
		this.data = data;
		this.paciente = paciente;
		
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

}
