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

import com.pechincha.domain.ItensDoPedido;
import com.pechincha.domain.Pedido;
import com.pechincha.domain.Usuario;
import com.pechincha.dto.PedidoDTO;
import com.pechincha.dto.PedidoDTOCadastrar;
import com.pechincha.services.ItensDoPedidoService;
import com.pechincha.services.PedidoService;
import com.pechincha.services.ProdutoService;
import com.pechincha.services.UserService;
import com.pechincha.services.UsuarioService;

@RestController
@RequestMapping(value = "/Pedido")
public class PedidoResource {
	@Autowired
	private PedidoService services;
	
	@Autowired
	private ItensDoPedidoService servicesItensDoPedido;
	
	@Autowired
	private UsuarioService servicesLogado;
	
	@Autowired
	private ProdutoService servicesProduto;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<PedidoDTO> find(@PathVariable Integer id) {
		Pedido obj = services.getById(id);
		return ResponseEntity.ok().body(new PedidoDTO(obj));
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> cadastrar(@Valid @RequestBody PedidoDTOCadastrar objDTO) {
		Usuario logado = servicesLogado.getById(UserService.authenticated().getId()); // para saber qual usuario fez o pedido
		Pedido obj = services.converter(objDTO);
		obj.setUsuario(logado);
		obj = services.cadastro(obj);
		for (int i = 0; i < objDTO.getItensdopedido().size(); i++) {
			ItensDoPedido itensdopedido = servicesItensDoPedido.converter(objDTO.getItensdopedido().get(i)); // para cada endereço que estiver dentro de usuarioDTO será feito a conversação para salvar os endereços
			itensdopedido.setProduto(servicesProduto.getById(objDTO.getItensdopedido().get(i).getProduto().getId()));
			itensdopedido.setPedido(obj);
			itensdopedido = servicesItensDoPedido.cadastro(itensdopedido);
			
			
		}
		return ResponseEntity.ok().body(new PedidoDTO(obj));
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Pedido>> buscartodos() {
		return ResponseEntity.ok().body(services.buscartodos());
	}
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	public ResponseEntity<?> atualizar(@RequestBody PedidoDTO objDTO,@PathVariable Integer id) {
		Pedido obj = services.converter(objDTO);
		obj.setId(id);
		obj = services.atualizar(obj);
		return ResponseEntity.ok().body(new PedidoDTO(obj));
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<PedidoDTO>> findByPedidoLog(
			@RequestParam(value = "numerodopedido", defaultValue = "") String numerodopedido,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "40") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Pedido> list = services.search(numerodopedido, page, linesPerPage, orderBy, direction);
		Page<PedidoDTO> listDto = list.map(obj -> new PedidoDTO(obj));
		System.out.println("Quantidade encontrado : " + listDto.getContent().size());
		return ResponseEntity.ok().body(listDto);
	}
}
