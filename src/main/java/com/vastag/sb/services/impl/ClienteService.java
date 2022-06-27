package com.vastag.sb.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.vastag.sb.domain.Cliente;
import com.vastag.sb.dto.ClienteDTO;
import com.vastag.sb.repositories.ClienteRepository;
import com.vastag.sb.services.IClienteService;
import com.vastag.sb.services.exceptions.DataIntegrityException;
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

	@Override
	public Cliente insert(ClienteDTO obj) {
		obj.setId(null);
		return repo.save(fromDTO(obj));
	}

	@Override
	public Cliente update(ClienteDTO obj) {
		Cliente c = findById(obj.getId());
		updateClientDataFromDTO(c, obj);
		return repo.save(c);
	}

	private void updateClientDataFromDTO(Cliente c, ClienteDTO obj) {
		c.setNome(obj.getNome());
		c.setEmail(obj.getEmail());
	}

	@Override
	public void delete(Long id) {
		findById(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma Cliente com produtos associados");
		}
	}

	@Override
	public List<ClienteDTO> findAll() {
		return repo.findAll().stream().map(ClienteDTO::new).collect(Collectors.toList());
	}

	@Override
	public Page<ClienteDTO> findPageable(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pr = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pr).map(ClienteDTO::new);
	}

	private Cliente fromDTO(ClienteDTO c) {
		return new Cliente(c.getId(), c.getNome(), c.getEmail(), null, null);
	}
}
