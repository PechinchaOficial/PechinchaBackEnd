package com.pechincha.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pechincha.domain.HistoricoLogin;
import com.pechincha.dto.CredenciaisDTO;
import com.pechincha.repositories.HistoricoLoginRepository;


@Service
public class HistoricoLoginService {

	@Autowired
	private HistoricoLoginRepository repo;

	@Transactional
	public HistoricoLogin find(String ip) {
		try {
			List<HistoricoLogin> obj = repo.findByIp(ip);
			if (!obj.isEmpty()) {
				return obj.get(0);
			}
		} catch (Exception ex) {

		}
		return null;
	}

	public List<HistoricoLogin> findAll() {
		return repo.findAll();
	}

	@Transactional
	public HistoricoLogin insert(HistoricoLogin obj) {

		obj.setId(null);
		obj.setStatus(1);
		return repo.save(obj);

	}

	public HistoricoLogin update(HistoricoLogin obj) {
		HistoricoLogin newObj = find(obj.getIp());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	private void updateData(HistoricoLogin newObj, HistoricoLogin obj) {
		newObj.setHeaders(obj.getHeaders());
		newObj.setParametros(obj.getParametros());
		newObj.setUltimaRequisicao(obj.getUltimaRequisicao());
		newObj.setQuantidade(obj.getQuantidade());
		if (newObj.getQuantidade() >= 10) {
			newObj.setStatus(0);
		} else {
			newObj.setStatus(1);
		}
	}

	public HistoricoLogin fromDTO(CredenciaisDTO objDto) {
		HistoricoLogin obj = new HistoricoLogin();

		return obj;

	}

	public Page<HistoricoLogin> search(String descricao, String tipo, int status, Integer page, Integer linesPerPage,
			String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		return null;
	}

}
