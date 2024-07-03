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
import com.pechincha.dto.EnderecoDTO;
import com.pechincha.dto.EnderecoDTOCadastrar;
import com.pechincha.services.EnderecoService;

@RestController
@RequestMapping(value = "/Endereco")
public class EnderecoResource {
	@Autowired
	private EnderecoService services;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<EnderecoDTO> find(@PathVariable Integer id) {
		Endereco obj = services.getById(id);
		return ResponseEntity.ok().body(new EnderecoDTO(obj));
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> cadastrar(@Valid @RequestBody EnderecoDTOCadastrar objDTO) {
		Endereco obj = services.converter(objDTO);
		obj = services.cadastro(obj);
		return ResponseEntity.ok().body(new EnderecoDTO(obj));
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Endereco>> buscartodos() {
		return ResponseEntity.ok().body(services.buscartodos());
	}
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	public ResponseEntity<?> atualizar(@RequestBody EnderecoDTO objDTO,@PathVariable Integer id) {
		Endereco obj = services.converter(objDTO);
		obj.setId(id);
		obj = services.atualizar(obj);
		return ResponseEntity.ok().body(new EnderecoDTO(obj));
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<EnderecoDTO>> findByEnderecoLog(
			@RequestParam(value = "bairro", defaultValue = "") String bairro,
			@RequestParam(value = "cidade", defaultValue = "") String cidade,
			@RequestParam(value = "estado", defaultValue = "") String rua,
			@RequestParam(value = "estado", defaultValue = "") String estado,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "40") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Endereco> list = services.search(bairro, cidade, estado, rua, page, linesPerPage, orderBy, direction);
		Page<EnderecoDTO> listDto = list.map(obj -> new EnderecoDTO(obj));
		System.out.println("Quantidade encontrado : " + listDto.getContent().size());
		return ResponseEntity.ok().body(listDto);
	}
}
