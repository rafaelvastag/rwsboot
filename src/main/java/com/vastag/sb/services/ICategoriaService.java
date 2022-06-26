package com.vastag.sb.services;

import com.vastag.sb.domain.Categoria;

public interface ICategoriaService {

	public Categoria findById(Long id);

	public Categoria insert(Categoria obj);
	
	public Categoria update(Categoria obj);

	public void delete(Long id);
}
