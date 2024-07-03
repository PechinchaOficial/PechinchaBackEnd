package com.pechincha.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pechincha.domain.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Integer>{
	@Query("Select p from Pedido p where id like %:id%")
	Page<Pedido> findBynumerodopedido(@Param("id") String numerodopedido, PageRequest pagerequest);
	
}
