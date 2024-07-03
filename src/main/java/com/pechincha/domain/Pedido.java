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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Temporal (TemporalType.TIMESTAMP) // anotação para especificar a data/hora
	private Date data;
	private double valorpedido;
	
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
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public double getValorpedido() {
		return valorpedido;
	}
	public void setValorpedido(double valorpedido) {
		this.valorpedido = valorpedido;
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
	
	
}
