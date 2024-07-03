package com.pechincha.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
	
@Entity
public class TentativaLogin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String ip;
	@Column(columnDefinition = "TEXT")
	private String parametros;
	@Column(columnDefinition = "TEXT")
	private String headers;
	@Temporal(TemporalType.TIMESTAMP)	
	private Date requisicao;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getParametros() {
		return parametros;
	}
	public void setParametros(String parametros) {
		this.parametros = parametros;
	}
	public String getHeaders() {
		return headers;
	}
	public void setHeaders(String headers) {
		this.headers = headers;
	}
	public Date getRequisicao() {
		return requisicao;
	}
	public void setRequisicao(Date requisicao) {
		this.requisicao = requisicao;
	}
	

	
	

	
	
	
}

