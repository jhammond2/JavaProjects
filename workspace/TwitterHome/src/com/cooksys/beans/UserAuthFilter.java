package com.cooksys.beans;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

@Component
@Scope("singleton")
public class UserAuthFilter extends GenericFilterBean{
	
	@Autowired
	private UserAuthenticate userAuth;
	
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String context = httpRequest.getContextPath();
		String url = httpRequest.getServletPath();

		System.out.println(context);
		System.out.println(url);
		
		//if authenticate is false from userauthenticate class then no-go for anything in secured pages folder
		if (!userAuth.isAuthenticated()) {
			System.out.println("User is not authenticated.");
			httpResponse.sendRedirect(context + "/index.jsf");
		} else {
			System.out.println("User is authenticated.");
			filterChain.doFilter(request, response);
		}

	}
}
