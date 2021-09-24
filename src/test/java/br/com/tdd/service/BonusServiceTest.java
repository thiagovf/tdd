package br.com.tdd.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import br.com.tdd.modelo.Funcionario;

class BonusServiceTest {

	@Test
	void bonusDeveriaSerZeroParaSalarioMuitoAlto() {
		BonusService service = new BonusService();
		BigDecimal salario = new BigDecimal("28000");
		BigDecimal bonusEsperado = new BigDecimal("0.00");
		
		Funcionario funcionario = new Funcionario("Thiago", LocalDate.now(), salario);
		BigDecimal bonus = service.calcularBonus(funcionario);
		
		assertEquals(bonusEsperado, bonus);
	}
	
	@Test
	void bonusDeveriaSerDezPorcentoDoSalario() {
		BonusService service = new BonusService();
		BigDecimal salario = new BigDecimal("9000");
		BigDecimal bonusEsperado = new BigDecimal("900.00");
		
		Funcionario funcionario = new Funcionario("Thiago", LocalDate.now(), salario);
		BigDecimal bonus = service.calcularBonus(funcionario);
		
		assertEquals(bonusEsperado, bonus);
	}
	
	@Test
	void bonusDeveriaSerDezPorcentoDoSalarioExatamente10000() {
		BonusService service = new BonusService();
		BigDecimal salario = new BigDecimal("10000");
		BigDecimal bonusEsperado = new BigDecimal("1000.00");
		
		Funcionario funcionario = new Funcionario("Thiago", LocalDate.now(), salario);
		BigDecimal bonus = service.calcularBonus(funcionario);
		
		assertEquals(bonusEsperado, bonus);
	}
	
	@Test
	void bonusDeveriaSerZeroParaSalarioNegativo() {
		BonusService service = new BonusService();
		BigDecimal salario = new BigDecimal("-1000");
		BigDecimal bonusEsperado = new BigDecimal("0.00");
		
		Funcionario funcionario = new Funcionario("Thiago", LocalDate.now(), salario);
		BigDecimal bonus = service.calcularBonus(funcionario);
		
		assertEquals(bonusEsperado, bonus);
	}
	
	@Test
	void bonusDeveriaSerZeroParaSalarioZero() {
		BonusService service = new BonusService();
		BigDecimal salario = new BigDecimal("0");
		BigDecimal bonusEsperado = new BigDecimal("0.00");
		
		Funcionario funcionario = new Funcionario("Thiago", LocalDate.now(), salario);
		BigDecimal bonus = service.calcularBonus(funcionario);
		
		assertEquals(bonusEsperado, bonus);
	}

}
