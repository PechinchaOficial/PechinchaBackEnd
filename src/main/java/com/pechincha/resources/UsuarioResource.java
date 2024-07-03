package com.pechincha.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pechincha.domain.Endereco;
import com.pechincha.domain.Usuario;
import com.pechincha.dto.UsuarioDTO;
import com.pechincha.dto.UsuarioDTOCadastrar;
import com.pechincha.services.EnderecoService;
import com.pechincha.services.UserService;
import com.pechincha.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioResource {
	@Autowired
	private UsuarioService services;
	@Autowired 
	private EnderecoService servicesEndereco;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UsuarioDTO> find(@PathVariable Integer id) {
		Usuario obj = services.getById(id);
		return ResponseEntity.ok().body(new UsuarioDTO(obj));
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/logado", method = RequestMethod.GET)
	public ResponseEntity<UsuarioDTO> findLogado() {
		Usuario logado = services.getById(UserService.authenticated().getId());
		return ResponseEntity.ok().body(new UsuarioDTO(logado));
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/cadastro", method = RequestMethod.POST) // value = altera a url
	public ResponseEntity<?> cadastrar(@Valid @RequestBody UsuarioDTOCadastrar objDTO) {
		Usuario obj = services.converter(objDTO);
		obj = services.cadastro(obj);
		for (int i = 0; i < objDTO.getEnderecos().size(); i++) {
			Endereco endereco = servicesEndereco.converter(objDTO.getEnderecos().get(i)); // para cada endereço que estiver dentro de usuarioDTO será feito a conversação para salvar os endereços
			endereco.setUsuario(obj);
			endereco =servicesEndereco.cadastro(endereco);
		}
		return ResponseEntity.ok().body(new UsuarioDTO(obj));
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>> buscartodos() {
		return ResponseEntity.ok().body(services.buscartodos());
	}
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	public ResponseEntity<?> atualizar(@RequestBody UsuarioDTO objDTO,@PathVariable Integer id) {
		Usuario obj = services.converter(objDTO);
		obj.setId(id);
		obj = services.atualizar(obj);
		return ResponseEntity.ok().body(new UsuarioDTO(obj));
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<UsuarioDTO>> findByUsuarioLog(
			@RequestParam(value = "documento", defaultValue = "") String documento,
			@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "tipo", defaultValue = "") String tipo,
			@RequestParam(value = "email", defaultValue = "") String email,
			@RequestParam(value = "telefone", defaultValue = "") String telefone,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "40") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Usuario> list = services.search(documento, nome, tipo, telefone, email, page, linesPerPage, orderBy, direction);
		Page<UsuarioDTO> listDto = list.map(obj -> new UsuarioDTO(obj));
		System.out.println("Quantidade encontrado : " + listDto.getContent().size());
		return ResponseEntity.ok().body(listDto);
	}
}
