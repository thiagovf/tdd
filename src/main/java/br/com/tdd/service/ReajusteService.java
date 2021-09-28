package br.com.tdd.service;

import java.math.BigDecimal;

import br.com.tdd.modelo.Desempenho;
import br.com.tdd.modelo.Funcionario;

public class ReajusteService {

	public void concederReajuste(Funcionario funcionario, Desempenho desempenho) {
		BigDecimal percentual;
		if (Desempenho.A_DESEJAR.equals(desempenho)) {
			percentual = new BigDecimal("0.03");
		} else if (Desempenho.BOM.equals(desempenho)) {
			percentual = new BigDecimal("0.15");
		} else if (Desempenho.OTIMO.equals(desempenho)) {
			percentual = new BigDecimal("0.2");
		} else {
			percentual = new BigDecimal("0");
		}
		
		BigDecimal reajuste = funcionario.getSalario().multiply(percentual);
		funcionario.reajustarSalario(reajuste);
		
	}

}
