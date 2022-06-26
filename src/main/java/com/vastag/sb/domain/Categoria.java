package com.vastag.sb.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

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

	@ManyToMany(mappedBy = "categorias")
	private List<Produto> produtos = new ArrayList<>();

	public Categoria(String nome) {
		super();
		this.nome = nome;
	}

	public Categoria(Long id, String nome) {
		this.setId(id);
		this.nome = nome;
	}
}
