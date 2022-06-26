package com.vastag.sb.services;

import com.vastag.sb.domain.Categoria;

public interface ICategoriaService {

	public Categoria buscarById(Long id);

	public Categoria inserir(Categoria obj);
}
