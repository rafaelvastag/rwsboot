package com.vastag.sb.config;

import java.text.ParseException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.vastag.sb.services.DBService;
import com.vastag.sb.services.IEmailService;
import com.vastag.sb.services.MockEmailService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@Profile("test")
public class EnvironmentTestConfig {
	
	private final DBService dbService;

	@Bean
	public boolean seedDatabase() throws ParseException {
		dbService.seedTestDatabase();
		return true;
	}
	
	@Bean
	public IEmailService emailService() {
		return new MockEmailService();
	}
}
