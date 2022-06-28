package com.vastag.sb.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.vastag.sb.domain.Cidade;
import com.vastag.sb.dto.CidadeDTO;
import com.vastag.sb.repositories.CidadeRepository;
import com.vastag.sb.services.ICidadeService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CidadeService implements ICidadeService {
	
	private final CidadeRepository repo;

	@Override
	public List<CidadeDTO> findByEstado(Integer estadoId) {
		List<Cidade> c = repo.findCidades(estadoId);
		return c.stream().map(CidadeDTO::new).collect(Collectors.toList());
	}

}
