package com.pechincha.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private double preco;
	private String descricao;
	private String nome;
	private double desconto;
	private Date datacadastro;
	private Date datavalidade;
	private String fotoproduto;
	private String categorias;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id") // nome que será classificado a relação entre o endereço e usuario será chamado de " usuario_id "
	private Usuario usuario; // nome da classe Usuario, nome do atributo " usuario "
	
	@OneToMany(mappedBy = "pedido") // na classe oposta esse é o nome do atributo que representa a relação " usuario "
	private List<ItensDoPedido> itensdopedido; // pode ter uma lista de endereços
	
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
	public String getFotoproduto() {
		return fotoproduto;
	}
	public void setFotoproduto(String fotoproduto) {
		this.fotoproduto = fotoproduto;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<ItensDoPedido> getItensdopedido() {
		return itensdopedido;
	}
	public void setItensdopedido(List<ItensDoPedido> itensdopedido) {
		this.itensdopedido = itensdopedido;
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
	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}
	public Date getDatacadastro() {
		return datacadastro;
	}
	public void setDatacadastro(Date datacadastro) {
		this.datacadastro = datacadastro;
	}
	public Date getDatavalidade() {
		return datavalidade;
	}
	public void setDatavalidade(Date datavalidade) {
		this.datavalidade = datavalidade;
	}

}
