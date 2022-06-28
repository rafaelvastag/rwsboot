package com.vastag.sb.services;

import java.util.List;

import com.vastag.sb.dto.CidadeDTO;

public interface ICidadeService {
	public List<CidadeDTO> findByEstado(Integer estadoId);
}
