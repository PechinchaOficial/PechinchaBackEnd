package com.pechincha.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItensDoPedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private double subtotal;
	private double quantitade;
	
	@JsonIgnore // evita de ocorrer um looping infinito
	@ManyToOne // muitos para um
	@JoinColumn(name = "pedido_id") // nome que será classificado a relação entre o endereço e usuario será chamado de " usuario_id "
	private Pedido pedido;
	
	@JsonIgnore // evita de ocorrer um looping infinito
	@ManyToOne // muitos para um
	@JoinColumn(name = "produto_id") // nome que será classificado a relação entre o endereço e usuario será chamado de " usuario_id "
	private Produto produto;
	
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
	public double getQuantitade() {
		return quantitade;
	}
	public void setQuantitade(double quantitade) {
		this.quantitade = quantitade;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}
