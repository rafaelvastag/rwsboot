package com.vastag.sb.services.impl;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.vastag.sb.domain.Categoria;
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
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	@Override
	public Categoria update(Categoria obj) {
		findById(obj.getId());
		return repo.save(obj);
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

}
