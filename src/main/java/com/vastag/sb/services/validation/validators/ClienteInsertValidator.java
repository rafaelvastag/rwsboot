package com.vastag.sb.services.validation.validators;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.vastag.sb.controllers.exceptions.FieldMessage;
import com.vastag.sb.domain.enums.TipoCliente;
import com.vastag.sb.dto.ClienteNewDTO;
import com.vastag.sb.repositories.ClienteRepository;
import com.vastag.sb.services.utils.ValidationBRUtil;
import com.vastag.sb.services.validation.ClienteInsert;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	private final ClienteRepository clienteRepo;

	@Override
	public void initialize(ClienteInsert constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(ClienteNewDTO c, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if (c.getTipo().equals(TipoCliente.PESSOA_FISICA.getCod()) && !ValidationBRUtil.isValidCPF(c.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		}

		if (c.getTipo().equals(TipoCliente.PESSOA_JURIDICA.getCod())
				&& !ValidationBRUtil.isValidCNPJ(c.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}

		if (clienteRepo.findByEmail(c.getEmail()).isPresent()) {
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
