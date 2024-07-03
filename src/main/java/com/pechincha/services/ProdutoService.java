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

import com.pechincha.domain.Produto;
import com.pechincha.dto.ProdutoDTO;
import com.pechincha.dto.ProdutoDTOAtualizar;
import com.pechincha.dto.ProdutoDTOCadastrar;
import com.pechincha.dto.ProdutoDTODeletar;
import com.pechincha.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository basededados;
	@Autowired
	BCryptPasswordEncoder pe;
	// estrutura de um metodo: public / private, tipo do return, nome do metodo, parametros sempre dentro de (), {}
	// estrutura do parametro: tipo do parametro, nome do parametro
	// exemplo: Integer Id, Integer eh o tipo do parametro e Id eh o nome dele.
public Produto getById (Integer Id) {
	return basededados.findById(Id).get();	
}
	
	public Produto cadastro (Produto ob) {
		return basededados.save(ob);
		
	}
	// instanciar um objeto eh: gerar um espaço em memoria para salvar um tipo de informação
	// instanciar um objeto eh: dar vida a abstração que eh a classe
	// como instanciar um objeto: Produto obj = new Produto();
	// new Produto(); eh a forma como a linguagem de programação chama o construtor da classe e aloca em espaço em memoria.
	public Produto converter(ProdutoDTO objDTO) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Produto novo = new Produto();
		
		try {
			novo.setDatacadastro(sf.parse(objDTO.getDatacadastro()));
			novo.setDatavalidade(sf.parse(objDTO.getDatavalidade()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		novo.setDesconto(objDTO.getDesconto());
		novo.setDescricao(objDTO.getDescricao());
		novo.setNome(objDTO.getNome());
		novo.setPreco(objDTO.getPreco());
		novo.setId(objDTO.getId());
		novo.setFotoproduto(objDTO.getFotoproduto());
		novo.setCategorias(objDTO.getCategorias());
		
		return novo;
	}
	public Produto converter(ProdutoDTOCadastrar objDTO) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Produto novo = new Produto();
		
		try {
			novo.setDatacadastro(sf.parse(objDTO.getDatacadastro()));
			novo.setDatavalidade(sf.parse(objDTO.getDatavalidade()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		novo.setDesconto(objDTO.getDesconto());
		novo.setDescricao(objDTO.getDescricao());
		novo.setNome(objDTO.getNome());
		novo.setPreco(objDTO.getPreco());
		novo.setId(null);
		novo.setFotoproduto(objDTO.getFotoproduto());
		novo.setCategorias(objDTO.getCategorias());
		
		return novo;
	}	
	
	public Produto converter(ProdutoDTOAtualizar objDTO) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Produto novo = new Produto();
		
		try {
			novo.setDatacadastro(sf.parse(objDTO.getDatacadastro()));
			novo.setDatavalidade(sf.parse(objDTO.getDatavalidade()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		novo.setDesconto(objDTO.getDesconto());
		novo.setDescricao(objDTO.getDescricao());
		novo.setNome(objDTO.getNome());
		novo.setPreco(objDTO.getPreco());
		novo.setId(null);
		novo.setFotoproduto(objDTO.getFotoproduto());
		novo.setCategorias(objDTO.getCategorias());
		
		return novo;
	}
	
	public Produto converter(ProdutoDTODeletar objDTO) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Produto novo = new Produto();
		
		try {
			novo.setDatacadastro(sf.parse(objDTO.getDatacadastro()));
			novo.setDatavalidade(sf.parse(objDTO.getDatavalidade()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		novo.setDesconto(objDTO.getDesconto());
		novo.setDescricao(objDTO.getDescricao());
		novo.setNome(objDTO.getNome());
		novo.setPreco(objDTO.getPreco());
		novo.setId(null);
		novo.setFotoproduto(objDTO.getFotoproduto());
		novo.setCategorias(objDTO.getCategorias());
		
		return novo;
	}
	
	public List<Produto> buscartodos(){
		return basededados.findAll();
	}
	public Produto atualizar(Produto novo) {
		Produto antigo = this.getById(novo.getId());
		this.updatedata(novo, antigo);
		return this.basededados.save(antigo);
	}
	
	public void updatedata(Produto novo,Produto antigo) {
		antigo.setDatacadastro(novo.getDatacadastro());
		antigo.setDatavalidade(novo.getDatavalidade());
		antigo.setDesconto(novo.getDesconto());
		antigo.setDescricao(novo.getDescricao());
		antigo.setNome(novo.getNome());
		antigo.setPreco(novo.getPreco());
		antigo.setFotoproduto(novo.getFotoproduto());
		antigo.setCategorias(novo.getCategorias());
	}
	
	public void deletar(Integer id) {
		basededados.deleteById(id);
	}
	
	public Page<Produto> search(String nome, String descricao,String categorias, Integer page,
			Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return basededados.findByNomeContainingIgnoreCaseAndDescricaoContainingIgnoreCaseAndCategoriasContainingIgnoreCase(nome, descricao, categorias, pageRequest);
	}
}
