package com.pechincha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.pechincha.domain.Usuario;
import com.pechincha.repositories.UsuarioRepository;

@SpringBootApplication
public class PechinchaApplication implements CommandLineRunner {

	@Autowired
    private UsuarioRepository userRepository;
	@Autowired
	BCryptPasswordEncoder pe;
	
	public static void main(String[] args) {
		SpringApplication.run(PechinchaApplication.class, args);
	}
	public void run(String... args) throws Exception {
	Usuario obj = new Usuario();
	/*	obj.setDocumento("10");
		obj.setTelefone("15");
		obj.setEmail("kaique@gmail.com");
		obj.setSenha(pe.encode("123"));
		obj.setStatus(1);
		userRepository.save(obj); */
		
	}
}
