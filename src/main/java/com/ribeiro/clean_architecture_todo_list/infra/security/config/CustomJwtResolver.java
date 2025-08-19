package com.ribeiro.clean_architecture_todo_list.infra.security.config;

import java.util.Arrays;

import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;
import org.springframework.util.StringUtils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class CustomJwtResolver implements BearerTokenResolver {
	
	private static final String COOKIE_NAME = "accessToken"; 

	@Override
	public String resolve(HttpServletRequest request) {
		var cookies = request.getCookies();

		if (cookies == null)
			return null; 

		return Arrays.stream(cookies)
			.filter(cookie -> COOKIE_NAME.equals(cookie.getName()) && StringUtils.hasText(cookie.getValue()))
			.map(Cookie::getValue)
			.findFirst()
			.orElse(null);
	}

}