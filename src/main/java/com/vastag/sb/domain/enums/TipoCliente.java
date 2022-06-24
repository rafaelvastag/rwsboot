package com.vastag.sb.domain.enums;

public enum TipoCliente {

	PESSOA_FISICA(1, "Pessoa física"), PESSOA_JURIDICA(2, "Pessoa Jurídica");

	private int cod;
	private String descricao;

	TipoCliente(int cod, String desc) {
		this.cod = cod;
		this.descricao = desc;
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

	public static TipoCliente toEnum(Integer cod) {

		if (cod == null) {
			return null;
		}

		for (TipoCliente t : TipoCliente.values()) {
			if (cod.equals(t.getCod())) {
				return t;
			}
		}
		
		throw new IllegalArgumentException("ID inválido: " + cod);
	}
}
