package com.vastag.sb.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vastag.sb.domain.Categoria;
import com.vastag.sb.services.ICategoriaService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

	@Qualifier("categoriaService")
	private final ICategoriaService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> buscaById(@PathVariable(name = "id") Long id) {
		return ResponseEntity.ok().body(service.buscarById(id));
	}

	@PostMapping
	public ResponseEntity<?> inserir(@RequestBody Categoria obj) {
		Categoria resource = service.inserir(obj);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(resource.getId())
				.toUri();
		
		return ResponseEntity.created(uri).build();
	}
}
