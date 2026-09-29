package com.eventoapp.eventoapp.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;


@Document(collection = "convidados")
public class Convidado {
	
	@Id
	@NotBlank
	private String rg;
	@NotBlank
	private String nomeConvidado;
	private String eventoCodigo;
	
	public String getEventoCodigo() {
		return eventoCodigo;
	}
	public void setEventoCodigo(String eventoCodigo) {
		this.eventoCodigo = eventoCodigo;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getNomeConvidado() {
		return nomeConvidado;
	}
	public void setNomeConvidado(String nomeConvidado) {
		this.nomeConvidado = nomeConvidado;
	}
	
	

}
