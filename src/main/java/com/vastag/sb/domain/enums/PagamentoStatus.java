package com.vastag.sb.domain.enums;

public enum PagamentoStatus {
	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private Integer cod;
	private String descricao;
	
	private PagamentoStatus(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static PagamentoStatus toEnum(Integer cod) {

		if (cod == null) {
			return null;
		}

		for (PagamentoStatus t : PagamentoStatus.values()) {
			if (cod.equals(t.getCod())) {
				return t;
			}
		}
		
		throw new IllegalArgumentException("ID inválido: " + cod);
	}
}
