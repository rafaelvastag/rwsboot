package com.vastag.sb.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vastag.sb.domain.enums.PagamentoStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pagamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	private Integer status;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "pedido_id")
	@MapsId
	private Pedido pedido;

	public Pagamento(PagamentoStatus status, Pedido pedido) {
		super();
		this.status = status.getCod();
		this.pedido = pedido;
	}
	
	public PagamentoStatus getStatus() {
		return PagamentoStatus.toEnum(status);
	}

	public void setStatus(PagamentoStatus status) {
		this.status = status.getCod();
	}	
}
