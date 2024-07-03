package com.pechincha.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.pechincha.domain.Produto;
import com.pechincha.dto.ItensDoPedidoDTO;
import com.pechincha.dto.PedidoDTOCadastrar;
import com.pechincha.excpetions.AuthorizationException;
import com.pechincha.excpetions.FieldMessage;
import com.pechincha.repositories.PedidoRepository;
import com.pechincha.repositories.ProdutoRepository;
import com.pechincha.security.UserSS;
import com.pechincha.services.PedidoService;
import com.pechincha.services.UserService;
import com.pechincha.validations.PedidoCadastrar;

public class PedidoCadastrarValidator implements ConstraintValidator<PedidoCadastrar, PedidoDTOCadastrar> {

	@Autowired
	PedidoService service;
	
	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private ProdutoRepository repoproduto;
	
	

	@Override
	public void initialize(PedidoCadastrar ann) {
	}

	@Override
	public boolean isValid(PedidoDTOCadastrar objDTO, ConstraintValidatorContext context) {
		

		List<FieldMessage> list = new ArrayList<>();

		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso Negado");
		}
		if (objDTO.getValorpedido() <= 0) {
			
			list.add(new FieldMessage("valorpedido", "O valor precisa ser acima de 0!"));
		}
		
		double somatoria = 0;
		for (int i = 0; i < objDTO.getItensdopedido().size(); i++) {
			ItensDoPedidoDTO obj = objDTO.getItensdopedido().get(i);
			if(objDTO.getItensdopedido().get(i).getQuantidade() <= 0) {
				list.add(new FieldMessage("itensdopedido", "ERRO quantidade de produtos negativa!"));
			}
			if(objDTO.getItensdopedido().get(i).getProduto() == null) {
				list.add(new FieldMessage("itensdopedido", "O produto nao foi passado na área de cadastro!"));
			} else if(objDTO.getItensdopedido().get(i).getProduto().getId() == null) {
				list.add(new FieldMessage("itensdopedido", "O produto nao foi passado na área de cadastro!"));
			} else {
				Optional<Produto> produto = repoproduto.findById(objDTO.getItensdopedido().get(i).getProduto().getId());
				if(produto.isPresent() == false) {
					list.add(new FieldMessage("itensdopedido", "Produto indisponivel!"));
				}
				else if(objDTO.getItensdopedido().get(i).getQuantidade() * produto.get().getPreco() != objDTO.getItensdopedido().get(i).getSubtotal()) {
					list.add(new FieldMessage("itensdopedido", "O valor total da compra esta incorreto!"));
				} else {
					somatoria = somatoria + objDTO.getItensdopedido().get(i).getSubtotal();					
				}
			}
		}
		if(somatoria != objDTO.getValorpedido()) {
			list.add(new FieldMessage("itensdopedido", "O valor total da compra esta incorreto!"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();

	}
}
