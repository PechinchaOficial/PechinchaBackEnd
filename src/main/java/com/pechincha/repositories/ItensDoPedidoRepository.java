package com.pechincha.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pechincha.domain.ItensDoPedido;

@Repository
public interface ItensDoPedidoRepository extends JpaRepository<ItensDoPedido,Integer>{
	
	
}
