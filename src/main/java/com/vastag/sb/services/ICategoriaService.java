package com.vastag.sb.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.vastag.sb.domain.Categoria;
import com.vastag.sb.dto.CategoriaDTO;

public interface ICategoriaService {

	public Categoria findById(Long id);

	public Categoria insert(CategoriaDTO obj);

	public Categoria update(CategoriaDTO obj);

	public void delete(Long id);

	public List<CategoriaDTO> findAll();

	public Page<CategoriaDTO> findPageable(Integer page, Integer linesPerPage, String orderBy, String direction);
}
