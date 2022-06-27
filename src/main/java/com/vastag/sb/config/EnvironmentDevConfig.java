package com.vastag.sb.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.vastag.sb.services.DBService;
import com.vastag.sb.services.IEmailService;
import com.vastag.sb.services.SmtpEmailService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@Profile("dev")
public class EnvironmentDevConfig {
	
	private final DBService dbService;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String dbStrategy;
	
	@Bean
	public boolean seedDatabase() throws ParseException {
		
		if (!"create".equals(dbStrategy)) {
			return false;
		}
		
		dbService.seedTestDatabase();
		return true;
	}
	
	@Bean
	public IEmailService emailService() {
		return new SmtpEmailService();
	}
	
}
