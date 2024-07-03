package com.pechincha.validators;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.pechincha.domain.Usuario;
import com.pechincha.dto.ProdutoDTOCadastrar;
import com.pechincha.enums.Perfil;
import com.pechincha.excpetions.AuthorizationException;
import com.pechincha.excpetions.FieldMessage;
import com.pechincha.repositories.ProdutoRepository;
import com.pechincha.security.UserSS;
import com.pechincha.services.ProdutoService;
import com.pechincha.services.UserService;
import com.pechincha.services.UsuarioService;
import com.pechincha.validations.ProdutoCadastrar;

public class ProdutoCadastrarValidator implements ConstraintValidator<ProdutoCadastrar, ProdutoDTOCadastrar> {

	@Autowired
	ProdutoService service;
	
	@Autowired
	UsuarioService serviceUsuario;
	
	@Autowired
	private ProdutoRepository repo;
	
	

	@Override
	public void initialize(ProdutoCadastrar ann) {
	}

	@Override
	public boolean isValid(ProdutoDTOCadastrar objDto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();

		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso Negado");
		}
		Usuario logado = this.serviceUsuario.getById(user.getId());
		if (!logado.getPerfil().contains(Perfil.MERCADO)) {
			list.add(new FieldMessage("id", "somente mercadores podem cadastrar produtos"));
		}
		if (objDto.getNome().equals("")) {
			list.add(new FieldMessage("nome", "É Necessário digitar o nome!"));
		} else {
			/*Optional<Produto> obj = repo.findByNome(objDto.getNome());
			if (obj.isPresent()) {
				Produto dentrodabasededados = obj.get();
				if (objDto.getNome().equals(dentrodabasededados.getNome())) {
					list.add(new FieldMessage("nome", "o nome ja existe")); 
				}
			} */
		}
		if (objDto.getPreco() < 0) {
			list.add(new FieldMessage("preco", "É Necessario digitar um preco com um valor maior que 0!"));
		}
		if (objDto.getNome().equals("")) {
			list.add(new FieldMessage("info", "É Necessario digitar o nome do produto!"));
		}
		if (objDto.getDescricao().equals("")) {
			list.add(new FieldMessage("descricao", "É Necessario digitar a descricao do produto!"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();

	}
}
