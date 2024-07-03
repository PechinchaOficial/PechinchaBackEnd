package com.pechincha.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pechincha.domain.ItensDoPedido;
import com.pechincha.dto.ItensDoPedidoDTO;
import com.pechincha.dto.ItensDoPedidoDTOCadastrar;
import com.pechincha.services.ItensDoPedidoService;

@RestController
@RequestMapping(value = "/ItensDoPedido")
public class ItensDoPedidoResource {
	@Autowired
	private ItensDoPedidoService services;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ItensDoPedidoDTO> find(@PathVariable Integer id) {
		ItensDoPedido obj = services.getById(id);
		return ResponseEntity.ok().body(new ItensDoPedidoDTO(obj));
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> cadastrar(@Valid @RequestBody ItensDoPedidoDTOCadastrar objDTO) {
		ItensDoPedido obj = services.converter(objDTO);
		obj = services.cadastro(obj);
		return ResponseEntity.ok().body(new ItensDoPedidoDTO(obj));
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ItensDoPedido>> buscartodos() {
		return ResponseEntity.ok().body(services.buscartodos());
	}
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	public ResponseEntity<?> atualizar(@RequestBody ItensDoPedidoDTO objDTO,@PathVariable Integer id) {
		ItensDoPedido obj = services.converter(objDTO);
		obj.setId(id);
		obj = services.atualizar(obj);
		return ResponseEntity.ok().body(new ItensDoPedidoDTO(obj));
	}
}
