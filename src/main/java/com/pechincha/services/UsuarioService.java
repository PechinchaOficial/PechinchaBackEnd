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

import com.pechincha.domain.Usuario;
import com.pechincha.dto.UsuarioDTO;
import com.pechincha.dto.UsuarioDTOAtualizar;
import com.pechincha.dto.UsuarioDTOCadastrar;
import com.pechincha.enums.Perfil;
import com.pechincha.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository basededados;
	@Autowired
	BCryptPasswordEncoder pe;
	// estrutura de um metodo: public / private, tipo do return, nome do metodo, parametros sempre dentro de (), {}
	// estrutura do parametro: tipo do parametro, nome do parametro
	// exemplo: Integer Id, Integer eh o tipo do parametro e Id eh o nome dele.
public Usuario getById (Integer Id) {
	return basededados.findById(Id).get();	
}
	
	public Usuario cadastro (Usuario ob) {
		return basededados.save(ob);
		
	}
	// instanciar um objeto eh: gerar um espaço em memoria para salvar um tipo de informação
	// instanciar um objeto eh: dar vida a abstração que eh a classe
	// como instanciar um objeto: Usuario obj = new Usuario();
	// new Usuario(); eh a forma como a linguagem de programação chama o construtor da classe e aloca em espaço em memoria.
	public Usuario converter(UsuarioDTO objDTO) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Usuario novo = new Usuario();
		
		try {
			novo.setDatanascimento(sf.parse(objDTO.getDatanascimento()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		novo.setEmail(objDTO.getEmail());
		novo.setSenha(pe.encode(objDTO.getSenha()));
		novo.setNome(objDTO.getNome());
		novo.setStatus(objDTO.getStatus());
		novo.setDocumento(objDTO.getDocumento());
		novo.setId(null);
		novo.setTelefone(objDTO.getTelefone());
		novo.setTipo(objDTO.getTipo());
		
		if(objDTO.getTipo().equals("Consumidor")) {
			novo.addPerfil(Perfil.CONSUMIDOR);
		}else {
			novo.addPerfil(Perfil.MERCADO);
		}
		
		return novo;
	}
	public Usuario converter(UsuarioDTOCadastrar objDTO) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Usuario novo = new Usuario();
		try {
			novo.setDatanascimento(sf.parse(objDTO.getDatanascimento()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		novo.setEmail(objDTO.getEmail());
		novo.setSenha(pe.encode(objDTO.getSenha()));
		novo.setNome(objDTO.getNome());
		novo.setStatus(1);
		novo.setDocumento(objDTO.getDocumento());
		novo.setId(null);
		novo.setTelefone(objDTO.getTelefone());
		novo.setTipo(objDTO.getTipo());
		
		if(objDTO.getTipo().equals("Consumidor")) {
			novo.addPerfil(Perfil.CONSUMIDOR);
		}else {
			novo.addPerfil(Perfil.MERCADO);
		}
		
		return novo;
	}	
	
	public Usuario converter(UsuarioDTOAtualizar objDTO) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Usuario novo = new Usuario();
		
		try {
			novo.setDatanascimento(sf.parse(objDTO.getDatanascimento()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		novo.setEmail(objDTO.getEmail());
		novo.setSenha(pe.encode(objDTO.getSenha()));
		novo.setNome(objDTO.getNome());
		novo.setStatus(objDTO.getStatus());
		novo.setDocumento(objDTO.getDocumento());
		novo.setTelefone(objDTO.getTelefone());
		novo.setTipo(objDTO.getTipo());
		return novo;
	}
	
	public List<Usuario> buscartodos(){
		return basededados.findAll();
	}
	public Usuario atualizar(Usuario novo) {
		Usuario antigo = this.getById(novo.getId());
		this.updatedata(novo, antigo);
		return this.basededados.save(antigo);
	}
	
	public void updatedata(Usuario novo,Usuario antigo) {
		antigo.setEmail(novo.getEmail());
		antigo.setNome(novo.getNome());
		antigo.setStatus(novo.getStatus());
		antigo.setTelefone(novo.getTelefone());
	}
	
	public Page<Usuario> search(String documento, String nome, String tipo, String telefone, String email, Integer page,
			Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return basededados.findByDocumentoContainingIgnoreCaseAndNomeContainingIgnoreCaseAndTipoContainingIgnoreCaseAndEmailContainingIgnoreCaseAndTelefoneContainingIgnoreCase(documento, nome, tipo, email, telefone, pageRequest);
	}
}
