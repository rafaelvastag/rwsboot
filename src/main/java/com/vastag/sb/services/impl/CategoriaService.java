package com.vastag.sb.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.vastag.sb.domain.Categoria;
import com.vastag.sb.dto.CategoriaDTO;
import com.vastag.sb.repositories.CategoriaRepository;
import com.vastag.sb.services.ICategoriaService;
import com.vastag.sb.services.exceptions.DataIntegrityException;
import com.vastag.sb.services.exceptions.ObjectNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service("categoriaService")
public class CategoriaService implements ICategoriaService {

	private final CategoriaRepository repo;

	@Override
	public Categoria findById(Long id) {
		return repo.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	@Override
	public Categoria insert(CategoriaDTO obj) {
		obj.setId(null);
		return repo.save(fromDTO(obj));
	}

	@Override
	public Categoria update(CategoriaDTO obj) {
		findById(obj.getId());
		return repo.save(fromDTO(obj));
	}

	@Override
	public void delete(Long id) {
		findById(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria com produtos associados");
		}
	}

	@Override
	public List<CategoriaDTO> findAll() {
		return repo.findAll().stream().map(CategoriaDTO::new).collect(Collectors.toList());
	}

	@Override
	public Page<CategoriaDTO> findPageable(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pr = PageRequest.of(page,linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pr).map(CategoriaDTO::new);
	}
	
	private Categoria fromDTO(CategoriaDTO c) {
		return new Categoria(c.getId() ,c.getNome());
	}

}
