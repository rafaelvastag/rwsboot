package com.vastag.sb.domain;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.vastag.sb.domain.enums.PagamentoStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonTypeName("pagamentoComCartao")
@Entity
public class PagamentoComCartao extends Pagamento {
	private static final long serialVersionUID = 1L;

	private Integer numeroDeParcelas;

	public PagamentoComCartao(PagamentoStatus status, Pedido pedido, Integer numeroDeParcelas) {
		super(status, pedido);
		this.numeroDeParcelas = numeroDeParcelas;
	}
}
