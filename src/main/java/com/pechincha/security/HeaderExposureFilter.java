package com.pechincha.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class HeaderExposureFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		((HttpServletResponse)response).setHeader("Access-Control-Allow-Origin", "*");
		((HttpServletResponse)response).setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
		((HttpServletResponse)response).setHeader("Access-Control-Max-Age", "3600");
		((HttpServletResponse)response).setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		((HttpServletResponse)response).setHeader("Access-Control-Expose-Headers", "Location");
		
		//((HttpServletResponse)response).addHeader("access-control-expose-headers", "location");
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		
	}

}

