package com.pechincha.validators;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.pechincha.dto.EnderecoDTOCadastrar;
import com.pechincha.excpetions.AuthorizationException;
import com.pechincha.excpetions.FieldMessage;
import com.pechincha.repositories.EnderecoRepository;
import com.pechincha.security.UserSS;
import com.pechincha.services.EnderecoService;
import com.pechincha.services.UserService;
import com.pechincha.validations.EnderecoCadastrar;

public class EnderecoCadastrarValidator implements ConstraintValidator<EnderecoCadastrar, EnderecoDTOCadastrar> {

	@Autowired
	EnderecoService service;
	@Autowired
	private EnderecoRepository repo;

	@Override
	public void initialize(EnderecoCadastrar ann) {
	}

	@Override
	public boolean isValid(EnderecoDTOCadastrar objDto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();

		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso Negado");
		}
		if (objDto.getCidade().equals("")) {
			list.add(new FieldMessage("nome", "É Necessário digitar a cidade!"));
		} else {
			/*Optional<Endereco> obj = repo.findByNome(objDto.getNome());
			if (obj.isPresent()) {
				Endereco dentrodabasededados = obj.get();
				if (objDto.getNome().equals(dentrodabasededados.getNome())) {
					list.add(new FieldMessage("nome", "o nome ja existe")); 
				}
			} */
		}
		if (objDto.getCidade().equals("")) {
			list.add(new FieldMessage("info", "É Necessario informar o local da cidade!"));
		}
		if (objDto.getEstado().equals("")) {
			list.add(new FieldMessage("info", "É Necessario informar o estado!"));
		}
		if (objDto.getCep().equals("")) {
			list.add(new FieldMessage("descricao", "É Necessario informar o cep!"));
		}
		if (objDto.getBairro().equals("")) {
			list.add(new FieldMessage("info", "É Necessario informar o bairro!"));
		}
		if (objDto.getRua().equals("")) {
			list.add(new FieldMessage("info", "É Necessario informar o local da rua!"));
		}
		if (objDto.getNumero().equals("")) {
			list.add(new FieldMessage("info", "É Necessario informar o numero do predio/casa!"));
		}
		if (objDto.getComplemento().equals("")) {
			list.add(new FieldMessage("info", "É Necessario informar o numero do apartamento se possui!"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();

	}
}
