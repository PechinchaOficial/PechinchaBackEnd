package com.pechincha.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pechincha.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Integer>{

	
	Page<Produto> findByNomeContainingIgnoreCaseAndDescricaoContainingIgnoreCaseAndCategoriasContainingIgnoreCase(String nome, String descricao, String categorias, PageRequest pagerequest);
}
