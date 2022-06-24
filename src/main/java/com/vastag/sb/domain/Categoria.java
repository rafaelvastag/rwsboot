package com.vastag.sb.domain;

import java.io.Serializable;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Categoria extends BaseEntityAudit implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nome;
}
