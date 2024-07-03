package com.pechincha.security;

import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.pechincha.domain.BloqueadoLogin;
import com.pechincha.domain.HistoricoLogin;
import com.pechincha.dto.CredenciaisDTO;
import com.pechincha.services.BloqueadoLoginService;
import com.pechincha.services.HistoricoLoginService;
import com.pechincha.services.TentativaLoginService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {

	@Value("${jwt.secret}")
	private String secret;
 	@Value("${jwt.expiration}")
	private Long expiration;
	@Autowired
	private HistoricoLoginService service;
	@Autowired
	private BloqueadoLoginService bloqService;
	@Autowired
	private TentativaLoginService tentService;
	private CredenciaisDTO creds;
	
	public String generateToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes())
				.compact();
	}
	
	public boolean tokenValido(String token) {
		Claims claims = getClaims(token);
		if (claims != null) {
			String username = claims.getSubject();
			Date expirationDate = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());
			if (username != null && expirationDate != null && now.before(expirationDate)) {
				return true;
			}
		}
		return false;
	}
	
	public String getUsername(String token) {
		Claims claims = getClaims(token);
		if (claims != null) {
			return claims.getSubject();
		}
		return null;
	}
	
	private Claims getClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		}
		catch (Exception e) {
			return null;
		}
	}
	
	public boolean falha(HttpServletRequest request)
	{
		String remoteAddr = this.getIp(request);
		String headers = this.getHeaders(request);
		String parametros = this.getBody();
		HistoricoLogin obj = service.find(remoteAddr);
		BloqueadoLogin bloq = bloqService.find(this.getCreds().getLogin());
		if (obj == null) {
			obj = new HistoricoLogin();
			obj.setIp(remoteAddr);
			obj.setHeaders(headers);
			obj.setParametros(parametros);
			obj.setUltimaRequisicao(new Date(System.currentTimeMillis()));
			obj.setStatus(1);
			obj.setQuantidade(1);
			obj = service.insert(obj);
		} else {
			obj.setHeaders(headers);
			obj.setParametros(parametros);
			obj.setUltimaRequisicao(new Date(System.currentTimeMillis()));
			obj.setQuantidade(obj.getQuantidade() + 1);
			obj = service.update(obj);
		}
		this.tentService.insert(obj);
		if (bloq == null) {
			bloq = new BloqueadoLogin();
			bloq.setIp(remoteAddr);
			bloq.setUsuario(this.getCreds().getLogin());
			bloq.setRequisicao(new Date(System.currentTimeMillis()));
			bloq.setStatus(1);
			bloq.setQuantidade(1);
			bloq = bloqService.insert(bloq);
		} else {
			bloq.setIp(remoteAddr);
			bloq.setRequisicao(new Date(System.currentTimeMillis()));
			bloq.setQuantidade(bloq.getQuantidade() + 1);
			bloq = bloqService.update(bloq);
		}
		if(obj.getQuantidade() >= 10 || bloq.getQuantidade() >= 20)
		{
			return false;
		}

			return true;

	}
	
	public void sucesso(HttpServletRequest request)
	{
		String remoteAddr = this.getIp(request);
		String headers = this.getHeaders(request);
		String parametros = this.getBody();
		HistoricoLogin obj = service.find(remoteAddr);
		BloqueadoLogin bloq = bloqService.find(this.getCreds().getLogin());
		if (obj == null) {
			obj = new HistoricoLogin();
			obj.setIp(remoteAddr);
			obj.setHeaders(headers);
			obj.setParametros(parametros);
			obj.setUltimaRequisicao(new Date(System.currentTimeMillis()));
			obj.setStatus(1);
			obj.setQuantidade(0);
			obj = service.insert(obj);
		} else {
			obj.setHeaders(headers);
			obj.setParametros(parametros);
			obj.setUltimaRequisicao(new Date(System.currentTimeMillis()));
			obj.setQuantidade(0);
			obj = service.update(obj);
		}
		this.tentService.insert(obj);
		if (bloq == null) {
			bloq = new BloqueadoLogin();
			bloq.setIp(remoteAddr);
			bloq.setUsuario(this.getCreds().getLogin());
			bloq.setRequisicao(new Date(System.currentTimeMillis()));
			bloq.setStatus(1);
			bloq.setQuantidade(0);
			bloq = bloqService.insert(bloq);
		} else {
			bloq.setIp(remoteAddr);
			bloq.setRequisicao(new Date(System.currentTimeMillis()));
			bloq.setQuantidade(0);
			bloq = bloqService.update(bloq);
		}
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
	
	
	
	public CredenciaisDTO getCreds() {
		return creds;
	}

	public void setCreds(CredenciaisDTO creds) {
		this.creds = creds;
	}

	public String getBody()
	{
		if(creds == null)
		{
			this.creds = new CredenciaisDTO();
			this.creds.setLogin("BODY INVÁLIDO");
			this.creds.setSenha("BODY INVÁLIDO");
		}
		return "{ \"usuario\" : \"" + this.creds.getLogin() + "\", \"senha\" : \"" + this.creds.getSenha()+"\"}";
	}
	
}

