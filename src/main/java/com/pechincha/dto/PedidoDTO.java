package com.pechincha.dto;

import java.text.SimpleDateFormat;
import java.util.List;

import com.pechincha.domain.Pedido;

public class PedidoDTO {
	
	private Integer id;
	private String data;
	private double valorpedido;
	private UsuarioDTO usuario;
	private List<ItensDoPedidoDTO> itensdopedido;
	
	public PedidoDTO() {
	}
	
	public PedidoDTO(Pedido domain) { // isso é um construtor que nao pode configurar o retorno, o retorno do construtor é a própria classe(retornar o objeto da classe construida)
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		this.id = domain.getId();
		try {
			this.data = (sf.format(domain.getData()));
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
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public double getValorpedido() {
		return valorpedido;
	}
	public void setValorpedido(double valorpedido) {
		this.valorpedido = valorpedido;
	}
	public UsuarioDTO getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
	public List<ItensDoPedidoDTO> getItensdopedido() {
		return itensdopedido;
	}
	public void setItensdopedido(List<ItensDoPedidoDTO> itensdopedido) {
		this.itensdopedido = itensdopedido;
	}
	
}
