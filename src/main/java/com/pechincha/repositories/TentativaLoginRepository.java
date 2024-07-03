package com.pechincha.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pechincha.domain.TentativaLogin;


@Repository
public interface TentativaLoginRepository extends JpaRepository<TentativaLogin, Integer>{


}
