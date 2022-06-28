package com.vastag.sb.services;

import org.springframework.data.domain.Page;

import com.vastag.sb.domain.Pedido;

public interface IPedidoService {

	public Pedido findById(Long id);

	public Pedido insert(Pedido obj);

	Page<Pedido> findPage(Integer page, Integer linesPerPage, String orderBy, String direction);
}
