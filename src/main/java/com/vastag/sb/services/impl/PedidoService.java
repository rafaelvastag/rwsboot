package com.vastag.sb.services.impl;

import org.springframework.stereotype.Service;

import com.vastag.sb.domain.Pedido;
import com.vastag.sb.repositories.PedidoRepository;
import com.vastag.sb.services.IPedidoService;
import com.vastag.sb.services.exceptions.ObjectNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service("pedidoService")
public class PedidoService implements IPedidoService {

	private final PedidoRepository repo;

	@Override
	public Pedido buscarById(Long id) {
		return repo.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

}
