package com.vastag.sb.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Estado extends BaseEntityAudit implements Serializable{
	private static final long serialVersionUID = 1L;

	private String nome;
	
	@OneToMany(mappedBy = "estado")
	private List<Cidade> cidade = new ArrayList<>();
	
	public Estado(String nome) {
		super();
		this.nome = nome;
	} 
}
