package com.pechincha.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pechincha.domain.Pedido;
import com.pechincha.dto.PedidoDTO;
import com.pechincha.dto.PedidoDTOAtualizar;
import com.pechincha.dto.PedidoDTOCadastrar;
import com.pechincha.repositories.PedidoRepository;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository basededados;
	@Autowired
	BCryptPasswordEncoder pe;
	// estrutura de um metodo: public / private, tipo do return, nome do metodo, parametros sempre dentro de (), {}
	// estrutura do parametro: tipo do parametro, nome do parametro
	// exemplo: Integer Id, Integer eh o tipo do parametro e Id eh o nome dele.
public Pedido getById (Integer Id) {
	return basededados.findById(Id).get();	
}
	
	public Pedido cadastro (Pedido ob) {
		return basededados.save(ob);
		
	}
	// instanciar um objeto eh: gerar um espaço em memoria para salvar um tipo de informação
	// instanciar um objeto eh: dar vida a abstração que eh a classe
	// como instanciar um objeto: Pedido obj = new Pedido();
	// new Pedido(); eh a forma como a linguagem de programação chama o construtor da classe e aloca em espaço em memoria.
	public Pedido converter(PedidoDTO objDTO) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Pedido novo = new Pedido();
		
		try {
			novo.setData(sf.parse(objDTO.getData()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		novo.setId(objDTO.getId());
		
		
		return novo;
	}
	public Pedido converter(PedidoDTOCadastrar objDTO) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Pedido novo = new Pedido();
		try {
			novo.setData(sf.parse(objDTO.getData()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		novo.setId(null);
		novo.setValorpedido(objDTO.getValorpedido());
		
		
		return novo;
	}	
	
	public Pedido converter(PedidoDTOAtualizar objDTO) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Pedido novo = new Pedido();
		
		try {
			novo.setData(sf.parse(objDTO.getData()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return novo;
	}
	
	public List<Pedido> buscartodos(){
		return basededados.findAll();
	}
	public Pedido atualizar(Pedido novo) {
		Pedido antigo = this.getById(novo.getId());
		this.updatedata(novo, antigo);
		return this.basededados.save(antigo);
	}
	
	public void updatedata(Pedido novo,Pedido antigo) {
		antigo.setData(novo.getData());
	}
	
	public Page<Pedido> search(String numerodopedido, Integer page,
			Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return basededados.findBynumerodopedido(numerodopedido, pageRequest);
	}
}
