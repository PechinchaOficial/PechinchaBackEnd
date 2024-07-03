package com.pechincha.services;

import java.util.Enumeration;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pechincha.domain.Usuario;
import com.pechincha.repositories.UsuarioRepository;
import com.pechincha.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository repo;
	@Autowired
	private HttpServletRequest request;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		String remoteAddr = this.getIp(request);
		String headers = this.getHeaders(request);
		String parametros = this.getParametros(request);

		int objHQuantidade = 0;
		int bloqQuantidade = 0;
		Optional<Usuario> obj = repo.findByEmailAndStatus(email, 1);
		if (!obj.isPresent()) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSS(obj.get().getId(), obj.get().getEmail(), obj.get().getSenha(), obj.get().getPerfil());
	}

	private String getParametros(HttpServletRequest request) {
		String parametros = "";

		Enumeration parameterNames = request.getParameterNames();

		while (parameterNames.hasMoreElements()) {
			String key = (String) parameterNames.nextElement();
			String value = request.getParameter(key);
			parametros = parametros + "\"" + key + "\" : " + "\"" + value + "\",";
		}

		parametros = "{" + parametros + "}";
		return parametros;
	}

	private String getHeaders(HttpServletRequest request) {
		String headers = "";
		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			headers = headers + "\"" + key + "\" : " + "\"" + value + "\",";
		}
		headers = "{" + headers + "}";
		return headers;
	}

	private String getIp(HttpServletRequest request) {
		String remoteAddr = "";
		if (request != null) {
			remoteAddr = request.getHeader("X-FORWARDED-FOR");
			if (remoteAddr == null || "".equals(remoteAddr)) {
				remoteAddr = request.getRemoteAddr();
			}
		}
		return remoteAddr;
	}
}
