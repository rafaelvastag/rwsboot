package com.vastag.sb.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vastag.sb.controllers.utils.URL;
import com.vastag.sb.domain.Produto;
import com.vastag.sb.dto.ProdutoDTO;
import com.vastag.sb.services.IProdutoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

	@Qualifier("ProdutoService")
	private final IProdutoService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Produto> findById(@PathVariable(name = "id") Long id) {
		return ResponseEntity.ok().body(service.findById(id));
	}

	@GetMapping(value = "/page")
	public ResponseEntity<Page<ProdutoDTO>> findPageable(@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "categorias", defaultValue = "0") String categorias,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "5") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		return ResponseEntity.ok().body(service.search(URL.decodeParam(nome), URL.decodeIntList(categorias), page,
				linesPerPage, orderBy, direction));
	}
}
