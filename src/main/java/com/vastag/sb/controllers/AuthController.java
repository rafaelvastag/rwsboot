package com.vastag.sb.controllers;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vastag.sb.dto.EmailDTO;
import com.vastag.sb.security.UserSpringSecurity;
import com.vastag.sb.security.utils.JWTUtil;
import com.vastag.sb.services.IAuthService;
import com.vastag.sb.services.impl.security.UserService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/auth")
public class AuthController {

	private final JWTUtil jwtUtil;
	private final IAuthService service;

	@ApiOperation(value = "Atualiza token vigente")
	@PostMapping(value = "/refresh_token")
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSpringSecurity user = UserService.authenticatedUser();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		response.addHeader("access-control-expose-headers", "Authorization");
		return ResponseEntity.noContent().build();
	}

	@ApiOperation(value = "Envia email com senha de recuperação")
	@PostMapping(value = "/forgot-password")
	public ResponseEntity<Void> forgotPassword(@Valid @RequestBody EmailDTO obj) {
		service.sendNewPassword(obj.getEmail());
		return ResponseEntity.noContent().build();
	}
}
