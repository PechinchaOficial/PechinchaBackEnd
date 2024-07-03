package com.pechincha.dto;

import com.pechincha.domain.Endereco;

public class EnderecoDTO {
	
	private Integer id;
	private String cidade;
	private String estado;
	private String cep;
	private String bairro;
	private String rua;
	private String numero;
	private String complemento;
	private UsuarioDTO usuario;
	
	public EnderecoDTO() {
	}
	
	public EnderecoDTO(Endereco domain) { // isso é um construtor que nao pode configurar o retorno, o retorno do construtor é a própria classe(retornar o objeto da classe construida)
    	this.id = domain.getId();
    	this.bairro = domain.getBairro();
    	this.cep = domain.getCep();
    	this.cidade = domain.getCidade();
    	this.complemento = domain.getComplemento();
    	this.estado = domain.getEstado();
    	this.numero = domain.getNumero();
    	this.rua = domain.getRua();
	}
	
	public UsuarioDTO getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
}
