package com.pechincha.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pechincha.domain.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco,Integer>{
	
	Page<Endereco> findByBairroContainingIgnoreCaseAndCidadeContainingIgnoreCaseAndEstadoContainingIgnoreCaseAndRuaContainingIgnoreCase(String bairro, String cidade, String estado, String rua, PageRequest pagerequest);

}
