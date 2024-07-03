package com.pechincha.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pechincha.domain.Endereco;
import com.pechincha.dto.EnderecoDTO;
import com.pechincha.dto.EnderecoDTOAtualizar;
import com.pechincha.dto.EnderecoDTOCadastrar;
import com.pechincha.repositories.EnderecoRepository;

@Service
public class EnderecoService {
	@Autowired
	private EnderecoRepository basededados;
	@Autowired
	BCryptPasswordEncoder pe;
	// estrutura de um metodo: public / private, tipo do return, nome do metodo, parametros sempre dentro de (), {}
	// estrutura do parametro: tipo do parametro, nome do parametro
	// exemplo: Integer Id, Integer eh o tipo do parametro e Id eh o nome dele.
public Endereco getById (Integer Id) {
	return basededados.findById(Id).get();	
}
	
	public Endereco cadastro (Endereco ob) {
		return basededados.save(ob);
		
	}
	// instanciar um objeto eh: gerar um espaço em memoria para salvar um tipo de informação
	// instanciar um objeto eh: dar vida a abstração que eh a classe
	// como instanciar um objeto: Endereco obj = new Endereco();
	// new Endereco(); eh a forma como a linguagem de programação chama o construtor da classe e aloca em espaço em memoria.
	public Endereco converter(EnderecoDTO objDTO) {
		Endereco novo = new Endereco();
		
		novo.setBairro(objDTO.getBairro());
		novo.setCep(objDTO.getCep());
		novo.setCidade(objDTO.getCidade());
		novo.setComplemento(objDTO.getComplemento());
		novo.setEstado(objDTO.getEstado());
		novo.setNumero(objDTO.getNumero());
		novo.setRua(objDTO.getRua());
		novo.setId(objDTO.getId());
		
		return novo;
	}
	public Endereco converter(EnderecoDTOCadastrar objDTO) {
		Endereco novo = new Endereco();
		
		
		novo.setBairro(objDTO.getBairro());
		novo.setCep(objDTO.getCep());
		novo.setCidade(objDTO.getCidade());
		novo.setComplemento(objDTO.getComplemento());
		novo.setEstado(objDTO.getEstado());
		novo.setNumero(objDTO.getNumero());
		novo.setRua(objDTO.getRua());
		novo.setId(null);
		
		return novo;
	}	
	
	public Endereco converter(EnderecoDTOAtualizar objDTO) {
		Endereco novo = new Endereco();
		
		novo.setBairro(objDTO.getBairro());
		novo.setCep(objDTO.getCep());
		novo.setCidade(objDTO.getCidade());
		novo.setComplemento(objDTO.getComplemento());
		novo.setEstado(objDTO.getEstado());
		novo.setNumero(objDTO.getNumero());
		novo.setRua(objDTO.getRua());
		novo.setId(null);
		
		return novo;
	}
	
	public List<Endereco> buscartodos(){
		return basededados.findAll();
	}
	public Endereco atualizar(Endereco novo) {
		Endereco antigo = this.getById(novo.getId());
		this.updatedata(novo, antigo);
		return this.basededados.save(antigo);
	}
	
	public void updatedata(Endereco novo,Endereco antigo) {
		antigo.setBairro(novo.getBairro());
		antigo.setCep(novo.getCep());
		antigo.setCidade(novo.getCidade());
		antigo.setComplemento(novo.getComplemento());
		antigo.setEstado(novo.getEstado());
		antigo.setNumero(novo.getNumero());
		antigo.setRua(novo.getRua());
		
	}
	
	public Page<Endereco> search(String bairro, String cidade, String estado, String rua, Integer page,
			Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return basededados.findByBairroContainingIgnoreCaseAndCidadeContainingIgnoreCaseAndEstadoContainingIgnoreCaseAndRuaContainingIgnoreCase(bairro, cidade, estado, rua, pageRequest);
	}
}
