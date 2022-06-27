package com.vastag.sb.dto;

import com.vastag.sb.domain.Produto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO {

	private Long id;
	private String nome;
	private Double preco;
	
	public ProdutoDTO(Produto p ) {
		id = p.getId();
		nome = p.getNome();
		preco = p.getPreco();
	}
}
