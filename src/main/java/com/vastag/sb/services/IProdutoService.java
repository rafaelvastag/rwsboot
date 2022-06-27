package com.vastag.sb.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.vastag.sb.domain.Produto;
import com.vastag.sb.dto.ProdutoDTO;

public interface IProdutoService {

	public Produto findById(Long id);
	
	public Page<ProdutoDTO> search(String nome, List<Long> ids, Integer page, Integer linesPerPage, String orderBy, String direction);
}
