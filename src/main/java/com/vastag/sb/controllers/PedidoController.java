package com.vastag.sb.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vastag.sb.domain.Pedido;
import com.vastag.sb.services.IPedidoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

	@Qualifier("PedidoService")
	private final IPedidoService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Pedido> findById(@PathVariable(name = "id") Long id) {
		return ResponseEntity.ok().body(service.findById(id));
	}
}
