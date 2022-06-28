package com.vastag.sb.services.impl;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.vastag.sb.domain.Cidade;
import com.vastag.sb.domain.Cliente;
import com.vastag.sb.domain.Endereco;
import com.vastag.sb.domain.enums.Perfil;
import com.vastag.sb.domain.enums.TipoCliente;
import com.vastag.sb.dto.ClienteDTO;
import com.vastag.sb.dto.ClienteNewDTO;
import com.vastag.sb.repositories.CidadeRepository;
import com.vastag.sb.repositories.ClienteRepository;
import com.vastag.sb.repositories.EnderecoRepository;
import com.vastag.sb.security.UserSpringSecurity;
import com.vastag.sb.services.IClienteService;
import com.vastag.sb.services.IImageService;
import com.vastag.sb.services.IS3Service;
import com.vastag.sb.services.exceptions.AuthorizationException;
import com.vastag.sb.services.exceptions.DataIntegrityException;
import com.vastag.sb.services.exceptions.ObjectNotFoundException;
import com.vastag.sb.services.exceptions.UploadFileToAmazonS3Exception;
import com.vastag.sb.services.impl.security.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service("clienteService")
public class ClienteService implements IClienteService {

	private final ClienteRepository repo;
	private final EnderecoRepository endRepo;
	private final CidadeRepository cidadeRepo;
	private final IS3Service awsS3Service;
	private final IImageService imageService;
	private final BCryptPasswordEncoder encoder;

	@Value("${img.prefix.client.profile}")
	private String prefix;

	@Value("${img.profile.size}")
	private Integer size;

	@Override
	public Cliente findById(Long id) {

		UserSpringSecurity user = UserService.authenticatedUser();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}

		return repo.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	@Override
	public Cliente findByEmail(String email) {
		UserSpringSecurity user = UserService.authenticatedUser();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())) {
			throw new AuthorizationException("Acesso negado");
		}

		Optional<Cliente> obj = repo.findByEmail(email);
		if (obj.isEmpty()) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + Cliente.class.getName());
		}
		return obj.get();
	}

	@Override
	@Transactional
	public Cliente insert(ClienteNewDTO obj) {
		Cliente cc = fromDTO(obj);
		cc = repo.save(cc);
		endRepo.saveAll(cc.getEnderecos());
		return cc;
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

	private Cliente fromDTO(ClienteNewDTO c) {
		Cliente cli = new Cliente(null, c.getNome(), c.getEmail(), c.getCpfOuCnpj(), TipoCliente.toEnum(c.getTipo()),
				encoder.encode(c.getSenha()));
		Cidade cid = cidadeRepo.findById(c.getCidadeId()).get();
		Endereco end = new Endereco(null, c.getLogradouro(), c.getNumero(), c.getComplemento(), c.getBairro(),
				c.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(c.getTelefone1());
		if (c.getTelefone2() != null) {
			cli.getTelefones().add(c.getTelefone2());
		}
		if (c.getTelefone3() != null) {
			cli.getTelefones().add(c.getTelefone3());
		}
		return cli;
	}

	@Override
	public URI uploadProfilePicture(MultipartFile file) {
		try {

			UserSpringSecurity user = UserService.authenticatedUser();
			if (user == null) {
				throw new AuthorizationException("Acesso negado");
			}

			BufferedImage jpgImage = imageService.getJpgImageFromFile(file);
			jpgImage = imageService.cropSquare(jpgImage);
			jpgImage = imageService.resize(jpgImage, size);

			String fileName = prefix + user.getId() + ".jpg";

			URI uri = awsS3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");

			Cliente c = repo.findById(user.getId()).get();
			c.setImageURL(uri.toString());
			repo.save(c);

			return uri;
		} catch (Exception e) {
			throw new UploadFileToAmazonS3Exception("Não é possível excluir uma Cliente com produtos associados");
		}
	}
}
