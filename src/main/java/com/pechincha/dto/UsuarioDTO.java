package com.pechincha.dto;

import java.text.SimpleDateFormat;
import java.util.List;

import com.pechincha.domain.Usuario;

public class UsuarioDTO {

	private Integer id;
    private String nome;
    private String senha;
    private String email;
    private String telefone;
    private String datanascimento;
    private int status; // para saber se o usuario esta ativo ou inativo
    private String documento; // tanto CPF quanto CNPJ //
    private String tipo; // fisica ou juridica " consumidor ou mercado "
    
    private List<EnderecoDTO> enderecos;
    
    public UsuarioDTO() {
	}
    
    public UsuarioDTO(Usuario domain) { // isso é um construtor que nao pode configurar o retorno, o retorno do construtor é a própria classe(retornar o objeto da classe construida)
    	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    	this.id = domain.getId();
    	this.status = domain.getStatus();
    	this.nome = domain.getNome();
    	this.email = domain.getEmail();
    	this.telefone = domain.getTelefone();
    	try {
			this.datanascimento = (sf.format(domain.getDatanascimento()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	this.documento = domain.getDocumento();
    	this.tipo = domain.getTipo();
	}
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<EnderecoDTO> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<EnderecoDTO> enderecos) {
		this.enderecos = enderecos;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getDatanascimento() {
		return datanascimento;
	}
	public void setDatanascimento(String datanascimento) {
		this.datanascimento = datanascimento;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
    
    
}
