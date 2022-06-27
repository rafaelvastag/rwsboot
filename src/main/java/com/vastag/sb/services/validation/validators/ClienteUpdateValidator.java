package com.vastag.sb.services.validation.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.servlet.HandlerMapping;

import com.vastag.sb.controllers.exceptions.FieldMessage;
import com.vastag.sb.domain.Cliente;
import com.vastag.sb.dto.ClienteDTO;
import com.vastag.sb.repositories.ClienteRepository;
import com.vastag.sb.services.validation.ClienteUpdate;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

	private final ClienteRepository clienteRepo;
	private final HttpServletRequest request;

	@Override
	public void initialize(ClienteUpdate constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(ClienteDTO c, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Long uriId = Long.parseLong(map.get("id"));
		Cliente cliente = clienteRepo.findByEmail(c.getEmail()).get();
		
		if (null != cliente && !cliente.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "Email já existente"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

}
