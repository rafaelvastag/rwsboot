package com.vastag.sb.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vastag.sb.domain.Categoria;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {
	
	@GetMapping
	public List<Categoria> listarCategorias() {

		Categoria c1 = new Categoria(1, "Informática");
		Categoria c2 = new Categoria(2, "Escritório");
		
		List<Categoria> ls = new ArrayList<>();
		ls.add(c1);
		ls.add(c2);
		
		return ls;
	}
}
