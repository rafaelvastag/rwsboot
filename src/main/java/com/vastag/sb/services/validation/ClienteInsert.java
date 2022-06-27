package com.vastag.sb.services.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.vastag.sb.services.validation.validators.ClienteInsertValidator;

@Constraint(validatedBy = ClienteInsertValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ClienteInsert {
	String messge() default "Erro de validação";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
}
