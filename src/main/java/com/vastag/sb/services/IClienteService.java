package com.vastag.sb.services;

import java.net.URI;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.vastag.sb.domain.Cliente;
import com.vastag.sb.dto.ClienteDTO;
import com.vastag.sb.dto.ClienteNewDTO;

public interface IClienteService {

	public Cliente findById(Long id);

	public Cliente insert(ClienteNewDTO obj);

	public Cliente update(ClienteDTO obj);

	public void delete(Long id);

	public List<ClienteDTO> findAll();

	public Page<ClienteDTO> findPageable(Integer page, Integer linesPerPage, String orderBy, String direction);
	
	public URI uploadProfilePicture(MultipartFile file);

	public Cliente findByEmail(String email);
}
