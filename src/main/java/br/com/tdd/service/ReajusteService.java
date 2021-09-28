package br.com.tdd.service;

import java.math.BigDecimal;

import br.com.tdd.modelo.Desempenho;
import br.com.tdd.modelo.Funcionario;

public class ReajusteService {

	public void concederReajuste(Funcionario funcionario, Desempenho desempenho) {
		if (Desempenho.A_DESEJAR.equals(desempenho)) {
			BigDecimal reajuste = funcionario.getSalario().multiply(new BigDecimal("0.03"));
			funcionario.reajustarSalario(reajuste);
		}
	}

}
