package com.vastag.sb.services;

import com.vastag.sb.domain.Pedido;

public interface IPedidoService {

	public Pedido findById(Long id);

	public Pedido insert(Pedido obj);
}
