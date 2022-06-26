package com.vastag.sb.services.impl;

import org.springframework.stereotype.Service;

import com.vastag.sb.domain.Cliente;
import com.vastag.sb.repositories.ClienteRepository;
import com.vastag.sb.services.IClienteService;
import com.vastag.sb.services.exceptions.ObjectNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service("clienteService")
public class ClienteService implements IClienteService {

	private final ClienteRepository repo;

	@Override
	public Cliente findById(Long id) {
		return repo.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

}
