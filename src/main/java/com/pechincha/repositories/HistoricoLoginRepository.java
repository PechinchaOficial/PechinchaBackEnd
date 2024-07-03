package com.pechincha.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pechincha.domain.HistoricoLogin;


@Repository
public interface HistoricoLoginRepository extends JpaRepository<HistoricoLogin, Integer>{

	@Transactional(readOnly=true)
	List<HistoricoLogin> findByIp(String ip);
}
