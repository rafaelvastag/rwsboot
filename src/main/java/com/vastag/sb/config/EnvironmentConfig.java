package com.vastag.sb.config;

import java.text.ParseException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.vastag.sb.services.DBService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@Profile("test")
public class EnvironmentConfig {
	
	private final DBService dbService;

	@Bean
	public boolean seedDatabase() throws ParseException {
		dbService.seedTestDatabase();
		return true;
	}
	
}
