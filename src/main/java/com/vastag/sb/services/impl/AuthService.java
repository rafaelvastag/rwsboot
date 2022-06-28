package com.vastag.sb.services.impl;

import java.util.Optional;
import java.util.Random;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vastag.sb.domain.Cliente;
import com.vastag.sb.repositories.ClienteRepository;
import com.vastag.sb.services.IAuthService;
import com.vastag.sb.services.IEmailService;
import com.vastag.sb.services.exceptions.ObjectNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthService implements IAuthService {
	

	private final ClienteRepository clienteRepository;
	private final BCryptPasswordEncoder pe;
	private final IEmailService emailService;
	private Random rand = new Random();

	@Override
	public void sendNewPassword(String email) {
		
		Optional<Cliente> cliente = clienteRepository.findByEmail(email);
		if (cliente.isEmpty()) {
			throw new ObjectNotFoundException("Email não encontrado");
		}
		
		String newPass = newPassword();
		cliente.get().setSenha(pe.encode(newPass));
		
		clienteRepository.save(cliente.get());
		emailService.sendNewPasswordEmail(cliente.get(), newPass);
	}
	
	private String newPassword() {
		char[] vet = new char[10];
		for (int i=0; i<10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		if (opt == 0) { // gera um digito
			return (char) (rand.nextInt(10) + 48);
		}
		else if (opt == 1) { // gera letra maiuscula
			return (char) (rand.nextInt(26) + 65);
		}
		else { // gera letra minuscula
			return (char) (rand.nextInt(26) + 97);
		}
	}

}
