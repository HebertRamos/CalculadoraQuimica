package ufms.calculadora.test.extensoes;

import org.junit.Ignore;

import junit.framework.Assert;
import junit.framework.TestCase;
import ufms.calculadora.extensoes.calculadoraAlgebrica.CalculadoraAlgebrica;
import ufms.calculadora.extensoes.calculadoraAlgebrica.EnumOperacaoesCalculadoraAlgebrica;
import ufms.calculadora.extensoes.calculadoraAlgebrica.ExpressaoAlgebrica;
import ufms.calculadora.extensoes.calculadoraAlgebrica.OperacaoNaoSuportadaException;
import ufms.calculadora.extensoes.calculadoraAlgebrica.Variavel;

public class CalculadoraAlgebricaTest extends TestCase {

	@Ignore
	public void testDadoUmaExpressaoAlgebrica_100A_Igual_10B_Mais_20C_ESendo_A_Igual_1_E_B_Igual_2AoIsolar_C_deveRetonarAExpressao_C_Igual_4() {

		ExpressaoAlgebrica expressaoAlgebrica = new ExpressaoAlgebrica();

		Variavel var1 = new Variavel();
		var1.setId("A");
		var1.setValorCompanheiro(100);

		Variavel var2 = new Variavel();
		var2.setId("B");
		var2.setValorCompanheiro(10);

		Variavel var3 = new Variavel();
		var3.setId("C");
		var3.setValorCompanheiro(20);

		expressaoAlgebrica.setVariavel(var1)
				.setOperacao(EnumOperacaoesCalculadoraAlgebrica.IGUALDADE)
				.setVariavel(var2)
				.setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA)
				.setVariavel(var3);

		Variavel condicao1 = new Variavel();
		condicao1.setId("A");
		condicao1.setValor(1);

		Variavel condicao2 = new Variavel();
		condicao2.setId("B");
		condicao2.setValor(2);

		CalculadoraAlgebrica calculadoraAlgebrica = new CalculadoraAlgebrica();
		calculadoraAlgebrica.aplicaCondicao(expressaoAlgebrica, condicao1);
		calculadoraAlgebrica.aplicaCondicao(expressaoAlgebrica, condicao2);

		try {
			calculadoraAlgebrica.isolaVariavel(expressaoAlgebrica, "C");
		} catch (OperacaoNaoSuportadaException e) {
			Assert.fail(e.getMessage());
			return;
		}

		Assert.assertEquals("C=4", expressaoAlgebrica.toString());
	}

	@Ignore
	public void testDadoUmaExpressaoAlgebrica_100A_Mais_10B_Igual_20C_ESendo_A_Igual_1_E_B_Igual_2AoIsolar_C_deveRetonarAExpressao_C_Igual_4() {

		ExpressaoAlgebrica expressaoAlgebrica = new ExpressaoAlgebrica();

		Variavel var1 = new Variavel();
		var1.setId("A");
		var1.setValorCompanheiro(100);

		Variavel var2 = new Variavel();
		var2.setId("B");
		var2.setValorCompanheiro(10);

		Variavel var3 = new Variavel();
		var3.setId("C");
		var3.setValorCompanheiro(20);

		expressaoAlgebrica.setVariavel(var1)
				.setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA)
				.setVariavel(var2)
				.setOperacao(EnumOperacaoesCalculadoraAlgebrica.IGUALDADE)
				.setVariavel(var3);

		Variavel condicao1 = new Variavel();
		condicao1.setId("A");
		condicao1.setValor(1);

		Variavel condicao2 = new Variavel();
		condicao2.setId("B");
		condicao2.setValor(2);

		CalculadoraAlgebrica calculadoraAlgebrica = new CalculadoraAlgebrica();
		calculadoraAlgebrica.aplicaCondicao(expressaoAlgebrica, condicao1);
		calculadoraAlgebrica.aplicaCondicao(expressaoAlgebrica, condicao2);

		try {
			calculadoraAlgebrica.isolaVariavel(expressaoAlgebrica, "C");
		} catch (OperacaoNaoSuportadaException e) {
			Assert.fail(e.getMessage());
			return;
		}

		Assert.assertEquals("C=6", expressaoAlgebrica.toString());
	}

	@Ignore
	public void testDadoUmaExpressaoAlgebrica_100A_Igual_10B_ESendo_A_Igual_10_AoIsolar_B_deveRetonarAExpressao_B_Igual_4() {

		ExpressaoAlgebrica expressaoAlgebrica = new ExpressaoAlgebrica();

		Variavel var1 = new Variavel();
		var1.setId("A");
		var1.setValorCompanheiro(100);

		Variavel var2 = new Variavel();
		var2.setId("B");
		var2.setValorCompanheiro(100);

		expressaoAlgebrica.setVariavel(var1).setOperacao(EnumOperacaoesCalculadoraAlgebrica.IGUALDADE).setVariavel(var2);

		Variavel condicao1 = new Variavel();
		condicao1.setId("A");
		condicao1.setValor(10);

		CalculadoraAlgebrica calculadoraAlgebrica = new CalculadoraAlgebrica();
		calculadoraAlgebrica.aplicaCondicao(expressaoAlgebrica, condicao1);

		try {
			calculadoraAlgebrica.isolaVariavel(expressaoAlgebrica, "B");
		} catch (OperacaoNaoSuportadaException e) {
			Assert.fail(e.getMessage());
			return;
		}

		Assert.assertEquals("B=10", expressaoAlgebrica.toString());
	}
	
	public void testDadoExpressao_A_Igual_10_AoAchamarOParseDeveRetornarUmaVarivelComValor10(){
		
		Variavel var1 = new Variavel();
		var1.setId("A");
		
		ExpressaoAlgebrica expressaoAlgebrica = new ExpressaoAlgebrica();
		expressaoAlgebrica.setVariavel(var1).setOperacao(EnumOperacaoesCalculadoraAlgebrica.IGUALDADE).setVariavel(10);
		
		CalculadoraAlgebrica calculadoraAlgebrica = new CalculadoraAlgebrica();
		
		Variavel varResultado = calculadoraAlgebrica.parsearParaVariavel(expressaoAlgebrica);
		
		Variavel varEsperada = new Variavel();
		varEsperada.setId("A");
		varEsperada.setValor(10);
		
		Assert.assertEquals(varEsperada, varResultado);
		
	}

}
