package com.pechincha.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pechincha.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{
	@Transactional(readOnly=true)
    Optional<Usuario> findByEmail(String email);

    @Transactional(readOnly=true)
    Optional<Usuario> findByEmailAndStatus(String email, int status);
    
    Page<Usuario> findByDocumentoContainingIgnoreCaseAndNomeContainingIgnoreCaseAndTipoContainingIgnoreCaseAndEmailContainingIgnoreCaseAndTelefoneContainingIgnoreCase(String documento, String nome, String tipo, String email, String telefone, PageRequest pagerequest);

}
