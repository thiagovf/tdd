package br.com.tdd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import br.com.tdd.modelo.Funcionario;
import br.com.tdd.service.BonusService;

class BonusServiceTest {

	@Test
	void bonusDeveriaSerZeroParaSalarioMuitoAlto() {
		BonusService service = new BonusService();
		Funcionario funcionario = new Funcionario("Thiago", LocalDate.now(), new BigDecimal("28000"));
		BigDecimal bonus = service.calcularBonus(funcionario);
		assertEquals(BigDecimal.ZERO, bonus);
	}
	
	@Test
	void bonusDeveriaSerDezPorcentoDoSalario() {
		BonusService service = new BonusService();
		BigDecimal salario = new BigDecimal("9000");
		BigDecimal dezPorcento = salario.multiply(new BigDecimal("0.1")) ;
		
		Funcionario funcionario = new Funcionario("Thiago", LocalDate.now(), salario);
		BigDecimal bonus = service.calcularBonus(funcionario);
		
		assertEquals(dezPorcento, bonus);
	}

}
