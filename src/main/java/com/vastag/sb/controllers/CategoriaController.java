package com.vastag.sb.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categories")
public class CategoriaController {

	@GetMapping(value = "/test")
	public String getMethod() {
		return "Running";
	}
	
}
