package com.pechincha.validators;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.pechincha.dto.UsuarioDTOCadastrar;
import com.pechincha.excpetions.AuthorizationException;
import com.pechincha.excpetions.FieldMessage;
import com.pechincha.repositories.UsuarioRepository;
import com.pechincha.security.UserSS;
import com.pechincha.services.UserService;
import com.pechincha.services.UsuarioService;
import com.pechincha.validations.UsuarioCadastrar;

public class UsuarioCadastrarValidator implements ConstraintValidator<UsuarioCadastrar, UsuarioDTOCadastrar> {

	@Autowired
	UsuarioService service;
	@Autowired
	private UsuarioRepository repo;

	@Override
	public void initialize(UsuarioCadastrar ann) {
	}

	@Override
	public boolean isValid(UsuarioDTOCadastrar objDto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();

		if (objDto.getEmail().equals("")) {
			list.add(new FieldMessage("email", "É Necessário digitar o usuário!"));
		} else {
			if (repo.findByEmail(objDto.getEmail()).isPresent()) {
				list.add(new FieldMessage("email", "Usuário já cadastrado utilize outro nome de usuário!"));
			}
		}
		if (objDto.getSenha().equals("")) {
			list.add(new FieldMessage("senha", "É Necessário digitar a senha!"));
		}
		if (objDto.getNome().equals("")) {
			list.add(new FieldMessage("nome", "É Necessário digitar o nome do usuário!"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();

	}
}
