package com.vastag.sb.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.vastag.sb.domain.Estado;
import com.vastag.sb.dto.EstadoDTO;
import com.vastag.sb.repositories.EstadoRepository;
import com.vastag.sb.services.IEstadoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EstadoService implements IEstadoService {

	private final EstadoRepository repo;
	
	@Override
	public List<EstadoDTO> findAll() {
		List<Estado> e = repo.findAllByOrderByNome();
		return e.stream().map(EstadoDTO::new).collect(Collectors.toList());
	}

}
