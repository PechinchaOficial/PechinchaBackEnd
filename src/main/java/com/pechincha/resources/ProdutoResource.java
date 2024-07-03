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

import com.pechincha.domain.Produto;
import com.pechincha.domain.Usuario;
import com.pechincha.dto.ProdutoDTO;
import com.pechincha.dto.ProdutoDTOCadastrar;
import com.pechincha.services.ProdutoService;
import com.pechincha.services.UserService;
import com.pechincha.services.UsuarioService;

@RestController
@RequestMapping(value = "/Produto")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService services;
	
	@Autowired
	private UsuarioService servicesLogado;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ProdutoDTO> find(@PathVariable Integer id) {
		Produto obj = services.getById(id);
		return ResponseEntity.ok().body(new ProdutoDTO(obj));
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> cadastrar(@Valid @RequestBody ProdutoDTOCadastrar objDTO) {
		Usuario logado = servicesLogado.getById(UserService.authenticated().getId());
		Produto obj = services.converter(objDTO);
		obj.setUsuario(logado);
		obj = services.cadastro(obj);
		return ResponseEntity.ok().body(new ProdutoDTO(obj));
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Produto>> buscartodos() {
		return ResponseEntity.ok().body(services.buscartodos());
	}
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	public ResponseEntity<?> atualizar(@RequestBody ProdutoDTO objDTO,@PathVariable Integer id) {
		Produto obj = services.converter(objDTO);
		obj.setId(id);
		obj = services.atualizar(obj);
		return ResponseEntity.ok().body(new ProdutoDTO(obj));
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<?> deletar (@PathVariable Integer id) {
		services.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findByProdutoLog(
			@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "descricao", defaultValue = "") String descricao,
			@RequestParam(value = "categorias", defaultValue = "") String categorias,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "40") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Produto> list = services.search(nome, descricao, categorias, page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listDto = list.map(obj -> new ProdutoDTO(obj));
		System.out.println("Quantidade encontrado : " + listDto.getContent().size());
		return ResponseEntity.ok().body(listDto);
	}
}
