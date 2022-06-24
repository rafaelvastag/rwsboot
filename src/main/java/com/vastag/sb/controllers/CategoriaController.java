package com.vastag.sb.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vastag.sb.services.ICategoriaService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {
	
	@Qualifier("categoriaService")
	private final ICategoriaService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> listarCategorias(@PathVariable(name = "id") Long id) {
		return ResponseEntity.ok().body(service.buscarById(id));
	}
}
