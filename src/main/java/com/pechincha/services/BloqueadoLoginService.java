package com.pechincha.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pechincha.domain.BloqueadoLogin;
import com.pechincha.dto.CredenciaisDTO;
import com.pechincha.repositories.BloqueadoLoginRepository;


@Service
public class BloqueadoLoginService {

	@Autowired
	private BloqueadoLoginRepository repo;

	public BloqueadoLogin find(String usuario) {
		List<BloqueadoLogin> obj = repo.findByUsuario(usuario);
		if (obj.size() > 0) {
			return obj.get(0);
		}
		return null;
	}

	public List<BloqueadoLogin> findAll() {
		return repo.findAll();
	}

	@Transactional
	public BloqueadoLogin insert(BloqueadoLogin obj) {

		obj.setId(null);
		obj.setStatus(1);
		return repo.save(obj);

	}

	public BloqueadoLogin update(BloqueadoLogin obj) {
		BloqueadoLogin newObj = find(obj.getUsuario());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	private void updateData(BloqueadoLogin newObj, BloqueadoLogin obj) {
		newObj.setRequisicao(obj.getRequisicao());
		newObj.setQuantidade(obj.getQuantidade());
		newObj.setIp(obj.getIp());
		if (newObj.getQuantidade() >= 20) {
			newObj.setStatus(0);
		} else {
			newObj.setStatus(1);
		}
	}

	public BloqueadoLogin fromDTO(CredenciaisDTO objDto) {
		BloqueadoLogin obj = new BloqueadoLogin();

		return obj;

	}

	public Page<BloqueadoLogin> search(String descricao, String tipo, int status, Integer page, Integer linesPerPage,
			String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		return null;
	}

}
