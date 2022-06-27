package com.vastag.sb.services.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.vastag.sb.domain.Categoria;
import com.vastag.sb.domain.Produto;
import com.vastag.sb.dto.ProdutoDTO;
import com.vastag.sb.repositories.CategoriaRepository;
import com.vastag.sb.repositories.ProdutoRepository;
import com.vastag.sb.services.IProdutoService;
import com.vastag.sb.services.exceptions.ObjectNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service("ProdutoService")
public class ProdutoService implements IProdutoService {

	private final ProdutoRepository repo;
	private final CategoriaRepository categoriaRepo;

	@Override
	public Produto findById(Long id) {
		return repo.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}

	@Override
	public Page<ProdutoDTO> search(String nome, List<Long> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pr = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepo.findAllById(ids);
		return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pr).map(ProdutoDTO::new);
	}
}
