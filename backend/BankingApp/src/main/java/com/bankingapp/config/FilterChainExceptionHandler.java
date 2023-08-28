package com.bankingapp.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FilterChainExceptionHandler extends OncePerRequestFilter{

	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	@Qualifier("handlerExceptionResolver")
	private HandlerExceptionResolver resolver;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			filterChain.doFilter(request, response);
		}catch(Exception e) {
			log.error("Spring filter exception: "+e.getMessage());
			resolver.resolveException(request, response, null, e);
		}
		
	}

}
