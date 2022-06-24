package com.vastag.sb.services.impl;

import org.springframework.stereotype.Service;

import com.vastag.sb.domain.Categoria;
import com.vastag.sb.repositories.CategoriaRepository;
import com.vastag.sb.services.ICategoriaService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service("categoriaService")
public class CategoriaService implements ICategoriaService {
	
	private final CategoriaRepository repo;

	@Override
	public Categoria buscarById(Long id) {
		return repo.findById(id).orElse(null);
	}

}
