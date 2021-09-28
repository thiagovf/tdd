# TDD - Test Driven Development  
## Introdução
TDD é uma abordagem em que o teste é desenvolvido antes que a regra de negócio seja implementada. O código usado nesse repositório trata-se de um curso. Por mais que o TDD seja extremamente útil por impactar inclusive na forma de pensar do desenvolvedor, ressalto também a releavância de aplicar testes automatizados mesmo em códigos já existentes e que sabidamente funcionam conforme a requisito estabelecido.  
Em projetos reais é comum encontrarmos pilhas de classes sem cobertura alguma de testes automatizados mas que o sistema está em produção e é amplamente utilizado. Na rotina de prazos apertados no dia a dia do desenvolvimento somos levados a fazer ajustes ou correções de bugs pontuais sem se ater aos testes. Esse é o ponto crítico que considero.  
Se partirmos do princípio que é responsabilidade do desenvolvedor refatorar o código sempre que encontrar duplicidade ou outros vícios do tipo, é grande o risco de que o desenvolvedor acabe inserindo erros em regras que já estavam funcionando ou que o desenvolvedor não tenha nem coragem de fazer a refatoração tornando o código cada vez mais difícil de dar manutenção.  
Meu ponto principal para esses códigos já sem cobertura é: **implementar testes antes de refatorar o código**. Isso vai encorajar o desenvolvedor a fazer as refatorações necessárias e diminuirá significativamente a chance de quebrar as regras que já estavam funcionando.
## Implementações  
Apesar do ponto trazido acima, tratando de códigos que já estariam em produção, o código desse repositório é simples e traz implementações que já nasceram com TDD.  
### Teste Classe de Serviço  
No código do repositório, existe a classe BonusService que possui a regra de negócio que implementa o bônus, para isso, escrevemos a classe abaixo:
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
### TDD  
Os códigos que fizemos até agora utilizaram testes automatizados, mas estes foram feitos depois da implementação. No TDD a sequência é inversa.  
![Ciclo-TDD](http://newyorkschooltalk.org/wp-content/uploads/2020/02/1_ieVWcSsJmeBbZFo6a_dL5g.png)  
Iremos implementar três cenários:
- Se o desempenho for a desejar, aumento de 3%;
- Se o desempenho for bom, aumento de 15%;
- Se o desempenho for ótimo, aumento de 20%.  

Portanto, conforme pode ser visto no [commit](https://github.com/thiagovf/tdd/commit/11bf2428562bc71736f18d79c43b8609cbfd7ba5) do código abaixo, ainda não compila. Fizemos dessa forma para que pensemos no resultado antes de pensar na forma como iremos realmente implementá-lo.
```java  
@Test
public void reajusteDeveriaSerDeTresPorcentoQuandoDesempenhoForADesejar() {
	ReajusteService service = new ReajusteService();
	Funcionario funcionario = new Funcionario("Ana", LocalDate.now(), new BigDecimal("1000.00"));

	service.concederReajuste(funcionario, Desempenho.A_DESEJAR);
	assertEquals(new BigDecimal("1030.00"), funcionario.getSalario());
}
```  
Feito esse código, o compilador vai reclamar dos métodos e classes não existentes. Dessa forma, é possível usar a própria IDE para gerar o "template" do que vai ser implementado, chegando ao ponto de termos que fazer apenas a lógica abaixo na classe ```ReajusteService```. 
```java  
public void concederReajuste(Funcionario funcionario, Desempenho desempenho) {
	if (Desempenho.A_DESEJAR.equals(desempenho)) {
		BigDecimal reajuste = funcionario.getSalario().multiply(new BigDecimal("0.03"));
		funcionario.reajustarSalario(reajuste);
	}
}
```  
Seguindo na estratégia de implementar de forma simplificada, temos o código abaixo que trata os três cenários possíveis de reajuste.   
```java  
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
```   
Observe que a lógica dos ```if```  é a mesma, variando só o percentual de acordo com o desempenho passado. Esse cenário é ideal para a implementação do padrão *strategy*. Portanto, podemos agora **refatorar** o código sem medo de que o código vá parar de funcionar, dado que temos os testes cobrindo os cenários possíveis.  
```java  
public class ReajusteService {
	public void concederReajuste(Funcionario funcionario, Desempenho desempenho) {
		BigDecimal reajuste = funcionario.getSalario().multiply(desempenho.percentualReajuste());
		funcionario.reajustarSalario(reajuste);
	}
}  
```
Ao invés de ser adicionado um ```if``` novo para cada cenário possível, passamos a utilizar no ```Enum``` o método que retorna o percentual referente ao desempenho.  
```java  
public enum Desempenho {
	A_DESEJAR {
		@Override
		public BigDecimal percentualReajuste() {
			return new BigDecimal("0.03");
		}
	}, BOM {
		@Override
		public BigDecimal percentualReajuste() {
			return new BigDecimal("0.15");
		}
	}, OTIMO {
		@Override
		public BigDecimal percentualReajuste() {
			return new BigDecimal("0.2");
		}
	};

	public abstract BigDecimal percentualReajuste();
}   
```  
