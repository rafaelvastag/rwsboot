package com.vastag.sb.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Endereco extends BaseEntityAudit implements Serializable {
	private static final long serialVersionUID = 1L;

	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "cidade_id")
	private Cidade cidade;
}
