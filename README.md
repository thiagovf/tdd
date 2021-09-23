# TDD - Test Driven Development  
## Introdução
TDD é uma abordagem em que o teste é desenvolvido antes que a regra de negócio seja implementada. O código usado nesse repositório trata-se de um curso da Alura. Por mais que o TDD seja extremamente útil por impactar inclusive na forma de pensar do desenvolvedor, ressalto também a releavância de aplicar testes automatizados mesmo em códigos já existentes e que sabidamente funcionam conforme a requisito estabelecido.  
Em projetos reais é comum encontrarmos pilhas de classes sem cobertura alguma de testes automatizados mas que o sistema está em produção e é amplamente utilizado. Na rotina de prazos apertados no dia a dia do desenvolvimento somos levados a fazer ajustes ou correções de bugs pontuais sem se ater aos testes. Esse é o ponto crítico que considero.  
Se partirmos do princípio que é responsabilidade do desenvolvedor refatorar o código sempre que encontrar duplicidade ou outros vícios do tipo, é grande o risco de que o desenvolvedor acabe inserindo erros em regras que já estavam funcionando ou que o desenvolvedor não tenha nem coragem de fazer a refatoração tornando o código cada vez mais difícil de dar manutenção.  
Meu ponto principal para esses códigos já sem cobertura é: **implementar testes antes de refatorar o código**. Isso vai encorajar o desenvolvedor a fazer as refatorações necessárias e diminuirá significativamente a chance de quebrar as regras que já estavam funcionando.
## Implementações  
Apesar do ponto trazido acima, tratando de códigos que já estariam em produção, o código desse repositório é simples e traz implementações que já nasceram com TDD.  
### Teste Classe de Serviço  
No código tem a classe BonusService onde possui a regra de negócio que implementa o bônus, para isso, escrevemos a classe abaixo:
```java  
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
```  
Observe que os nomes dos métodos, apesar de extensos, estão sendo bem claros no que se propõem.