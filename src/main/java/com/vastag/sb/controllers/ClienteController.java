package com.vastag.sb.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vastag.sb.domain.Cliente;
import com.vastag.sb.services.IClienteService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

	@Qualifier("clienteService")
	private final IClienteService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable(name = "id") Long id) {
		return ResponseEntity.ok().body(service.findById(id));
	}
}
