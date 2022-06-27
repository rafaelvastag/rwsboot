package com.vastag.sb.controllers.exceptions;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FieldMessage implements Serializable{
	private static final long serialVersionUID = 1L;

	private String fieldName;
	private String message;
	
}
