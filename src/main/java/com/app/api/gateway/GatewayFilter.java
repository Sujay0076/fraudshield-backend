package com.app.api.gateway;

import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class GatewayFilter implements Filter {
	private RateLimiter rateLimiter;
	public GatewayFilter(RateLimiter rateLimiter) {
		this.rateLimiter = rateLimiter;
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		String uri = req.getRequestURI();
		if(uri.startsWith("/auth")) {
			chain.doFilter(request, response);
			return;
		}
		
		String userId = req.getRemoteAddr();
		
		if(rateLimiter.isAllowed(userId)) {
			chain.doFilter(request, response);
		}
		else {
			res.setStatus(429);
			res.getWriter().write("Too many request. Try again after 60 seconds");
			return;
		}
		
	}
	

}
