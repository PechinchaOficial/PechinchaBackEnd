package com.pechincha.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pechincha.domain.HistoricoLogin;
import com.pechincha.domain.TentativaLogin;
import com.pechincha.dto.CredenciaisDTO;
import com.pechincha.repositories.TentativaLoginRepository;


@Service
public class TentativaLoginService {

	@Autowired
	private TentativaLoginRepository repo;

	public TentativaLogin find(int id) {
		Optional<TentativaLogin> obj = repo.findById(id);
		if (obj.isPresent()) {
			return obj.get();
		}
		return null;
	}

	public List<TentativaLogin> findAll() {
		return repo.findAll();
	}

	@Transactional
	public TentativaLogin insert(HistoricoLogin objDto) {
		TentativaLogin obj = new TentativaLogin();
		obj.setHeaders(objDto.getHeaders());
		obj.setIp(objDto.getIp());
		obj.setParametros(objDto.getParametros());
		obj.setRequisicao(objDto.getUltimaRequisicao());
		obj.setId(null);
		return repo.save(obj);

	}

	public TentativaLogin update(TentativaLogin obj) {
		TentativaLogin newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	private void updateData(TentativaLogin newObj, TentativaLogin obj) {

	}

	public TentativaLogin fromDTO(CredenciaisDTO objDto) {
		TentativaLogin obj = new TentativaLogin();

		return obj;

	}

	public Page<TentativaLogin> search(String descricao, String tipo, int status, Integer page, Integer linesPerPage,
			String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		return null;
	}

}
