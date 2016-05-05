package br.com.caelum.financas.modelo;

import java.math.BigDecimal;

public class ValorPorMesEAno {

	private int mes;
	private int ano;
	private BigDecimal valor;

	public ValorPorMesEAno(int mes, int ano, BigDecimal total) {
		this.mes = mes;
		this.ano = ano;
		this.valor = total;
	}

	public int getMes() {
		return mes;
	}

	public int getAno() {
		return ano;
	}

	public BigDecimal getValor() {
		return valor;
	}

}
