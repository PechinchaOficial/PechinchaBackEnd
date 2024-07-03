package com.pechincha.dto;

import java.text.SimpleDateFormat;

import com.pechincha.domain.Produto;

public class ProdutoDTO {
	
	private Integer id;
	private double preco;
	private String descricao;
	private String nome;
	private double desconto;
	private String datacadastro;
	private String datavalidade;
	private String fotoproduto;
	private String categorias;
	private UsuarioDTO usuario;
	
	public ProdutoDTO() {
	}
	
	public ProdutoDTO(Produto domain) { // isso é um construtor que nao pode configurar o retorno, o retorno do construtor é a própria classe(retornar o objeto da classe construida)
    	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    	this.id = domain.getId();
    	this.nome = domain.getNome();
    	this.descricao = domain.getDescricao();
    	this.desconto = domain.getDesconto();
    	this.preco = domain.getPreco();
    	this.fotoproduto = domain.getFotoproduto();
    	this.categorias = domain.getCategorias();
    	try {
			this.datacadastro = (sf.format(domain.getDatacadastro()));
			this.datavalidade = (sf.format(domain.getDatavalidade()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCategorias() {
		return categorias;
	}

	public void setCategorias(String categorias) {
		this.categorias = categorias;
	}

	public double getDesconto() {
		return desconto;
	}
	public String getFotoproduto() {
		return fotoproduto;
	}

	public void setFotoproduto(String fotoproduto) {
		this.fotoproduto = fotoproduto;
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}
	public String getDatacadastro() {
		return datacadastro;
	}
	public void setDatacadastro(String datacadastro) {
		this.datacadastro = datacadastro;
	}
	public String getDatavalidade() {
		return datavalidade;
	}
	public void setDatavalidade(String datavalidade) {
		this.datavalidade = datavalidade;
	}
	public UsuarioDTO getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
	
	
}
