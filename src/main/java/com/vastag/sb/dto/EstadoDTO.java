package com.vastag.sb.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.vastag.sb.domain.Estado;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstadoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Size(min = 5, max = 80, message = "O tamanho do nome deve ser entre 5 e 80 caracteres")
	private String nome;

	public EstadoDTO(Estado c) {
		id = c.getId();
		nome = c.getNome();
	}
	
}
