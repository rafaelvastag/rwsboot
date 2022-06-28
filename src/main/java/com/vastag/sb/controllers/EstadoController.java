package com.vastag.sb.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vastag.sb.dto.CidadeDTO;
import com.vastag.sb.dto.EstadoDTO;
import com.vastag.sb.services.ICidadeService;
import com.vastag.sb.services.IEstadoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/estados")
public class EstadoController {

	private final IEstadoService service;
	
	private final ICidadeService cidadeService;

	@GetMapping
	public ResponseEntity<List<EstadoDTO>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}
	
	@GetMapping(value="/{estadoId}/cidades")
	public ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable Integer estadoId) {
		return ResponseEntity.ok().body(cidadeService.findByEstado(estadoId));
	}
	
}
