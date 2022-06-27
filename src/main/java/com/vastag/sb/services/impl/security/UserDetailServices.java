package com.vastag.sb.services.impl.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vastag.sb.domain.Cliente;
import com.vastag.sb.repositories.ClienteRepository;
import com.vastag.sb.security.UserSpringSecurity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserDetailServices implements UserDetailsService {

	private final ClienteRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Cliente> c = repo.findByEmail(email);
		if (c.isEmpty()) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSpringSecurity(c.get());
	}

}
