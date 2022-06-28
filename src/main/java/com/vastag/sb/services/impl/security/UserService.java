package com.vastag.sb.services.impl.security;

import org.springframework.security.core.context.SecurityContextHolder;

import com.vastag.sb.security.UserSpringSecurity;

public class UserService {

	public static UserSpringSecurity authenticatedUser() {
		try {
			return (UserSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}

	};
}
