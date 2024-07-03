package com.pechincha.dto;

import java.text.SimpleDateFormat;

import com.pechincha.domain.ItensDoPedido;

public class ItensDoPedidoDTO {
	
	private Integer id;
	private double subtotal;
	private double quantidade;
	private PedidoDTO pedido;
	private ProdutoDTO produto;
	
	public ItensDoPedidoDTO() {
	}
	
	public ItensDoPedidoDTO(ItensDoPedido domain) { // isso é um construtor que nao pode configurar o retorno, o retorno do construtor é a própria classe(retornar o objeto da classe construida)
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		this.id = domain.getId();
		this.quantidade = domain.getQuantitade();
		this.subtotal = domain.getSubtotal();

	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public double getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}
	public PedidoDTO getPedido() {
		return pedido;
	}
	public void setPedido(PedidoDTO pedido) {
		this.pedido = pedido;
	}
	public ProdutoDTO getProduto() {
		return produto;
	}
	public void setProduto(ProdutoDTO produto) {
		this.produto = produto;
	}
	
	
}
