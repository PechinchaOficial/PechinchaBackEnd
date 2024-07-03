package com.pechincha.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pechincha.domain.ItensDoPedido;
import com.pechincha.dto.ItensDoPedidoDTO;
import com.pechincha.dto.ItensDoPedidoDTOAtualizar;
import com.pechincha.dto.ItensDoPedidoDTOCadastrar;
import com.pechincha.repositories.ItensDoPedidoRepository;

@Service
public class ItensDoPedidoService {
	@Autowired
	private ItensDoPedidoRepository basededados;
	@Autowired
	BCryptPasswordEncoder pe;
	// estrutura de um metodo: public / private, tipo do return, nome do metodo, parametros sempre dentro de (), {}
	// estrutura do parametro: tipo do parametro, nome do parametro
	// exemplo: Integer Id, Integer eh o tipo do parametro e Id eh o nome dele.
public ItensDoPedido getById (Integer Id) {
	return basededados.findById(Id).get();	
}
	
	public ItensDoPedido cadastro (ItensDoPedido ob) {
		return basededados.save(ob);
		
	}
	// instanciar um objeto eh: gerar um espaço em memoria para salvar um tipo de informação
	// instanciar um objeto eh: dar vida a abstração que eh a classe
	// como instanciar um objeto: ItensDoPedido obj = new ItensDoPedido();
	// new ItensDoPedido(); eh a forma como a linguagem de programação chama o construtor da classe e aloca em espaço em memoria.
	public ItensDoPedido converter(ItensDoPedidoDTO objDTO) {
		ItensDoPedido novo = new ItensDoPedido();
		novo.setQuantitade(objDTO.getQuantidade());
		novo.setSubtotal(objDTO.getSubtotal());
		novo.setId(objDTO.getId());
		return novo;
	}
	public ItensDoPedido converter(ItensDoPedidoDTOCadastrar objDTO) {
		ItensDoPedido novo = new ItensDoPedido();
		novo.setId(null);
		novo.setQuantitade(objDTO.getQuantidade());
		novo.setSubtotal(objDTO.getSubtotal());

		return novo;
	}	
	
	public ItensDoPedido converter(ItensDoPedidoDTOAtualizar objDTO) {
		ItensDoPedido novo = new ItensDoPedido();
		novo.setQuantitade(objDTO.getQuantidade());
		novo.setSubtotal(objDTO.getSubtotal());
		return novo;
	}
	
	public List<ItensDoPedido> buscartodos(){
		return basededados.findAll();
	}
	public ItensDoPedido atualizar(ItensDoPedido novo) {
		ItensDoPedido antigo = this.getById(novo.getId());
		this.updatedata(novo, antigo);
		return this.basededados.save(antigo);
	}
	
	public void updatedata(ItensDoPedido novo,ItensDoPedido antigo) {
		antigo.setQuantitade(novo.getQuantitade());
		antigo.setSubtotal(novo.getSubtotal());
		
	}
}
