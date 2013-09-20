package ufms.calculadora.test.extensoes;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Ignore;

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

		calculadoraAlgebrica.isolaVariavel(expressaoAlgebrica, "C");

		Assert.assertEquals("1C=4", expressaoAlgebrica.toString());
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

		calculadoraAlgebrica.isolaVariavel(expressaoAlgebrica, "C");

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

		expressaoAlgebrica.setVariavel(var1)
				.setOperacao(EnumOperacaoesCalculadoraAlgebrica.IGUALDADE)
				.setVariavel(var2);

		Variavel condicao1 = new Variavel();
		condicao1.setId("A");
		condicao1.setValor(10);

		CalculadoraAlgebrica calculadoraAlgebrica = new CalculadoraAlgebrica();
		calculadoraAlgebrica.aplicaCondicao(expressaoAlgebrica, condicao1);

		calculadoraAlgebrica.isolaVariavel(expressaoAlgebrica, "B");

		Assert.assertEquals("B=10", expressaoAlgebrica.toString());
	}

	public void testDadoExpressao_A_Igual_10_AoAchamarOParseDeveRetornarUmaVarivelComValor10() {

		Variavel var1 = new Variavel();
		var1.setId("A");

		ExpressaoAlgebrica expressaoAlgebrica = new ExpressaoAlgebrica();
		expressaoAlgebrica.setVariavel(var1)
				.setOperacao(EnumOperacaoesCalculadoraAlgebrica.IGUALDADE)
				.setValorImediato(10);

		CalculadoraAlgebrica calculadoraAlgebrica = new CalculadoraAlgebrica();

		Variavel varResultado = calculadoraAlgebrica
				.parsearParaVariavel(expressaoAlgebrica);

		Variavel varEsperada = new Variavel();
		varEsperada.setId("A");
		varEsperada.setValor(10);

		Assert.assertEquals(varEsperada, varResultado);

	}

	public void testDadoUmaExpresao_A_Igual_10_Mais_20_AoExecutarAsOperacoesDoLadoDireitoDeveRestarApenasUmaPosicaoNoladoDireitoComValor30() {

		Variavel var1 = new Variavel();
		var1.setId("A");
		ExpressaoAlgebrica expressaoAlgebrica = new ExpressaoAlgebrica();
		expressaoAlgebrica.setVariavel(var1)
				.setOperacao(EnumOperacaoesCalculadoraAlgebrica.IGUALDADE)
				.setValorImediato(20)
				.setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA)
				.setValorImediato(10);

		CalculadoraAlgebrica calculadoraAlgebrica = new CalculadoraAlgebrica();

		List<String> resultadoEsperado = Arrays.asList(new String[] { "30" });

		Assert.assertEquals(resultadoEsperado, calculadoraAlgebrica
				.executaOperacoesLado(expressaoAlgebrica.getLadoDireito()));

	}

	public void testDadoUmaExpresao_A_Igual_10_Mais_20__Mais_50_Mais_20AoExecutarAsOperacoesDoLadoDireitoDeveRestarApenasUmaPosicaoNoladoDireitoComValor100() {

		Variavel var1 = new Variavel();
		var1.setId("A");
		ExpressaoAlgebrica expressaoAlgebrica = new ExpressaoAlgebrica();
		expressaoAlgebrica.setVariavel(var1)
				.setOperacao(EnumOperacaoesCalculadoraAlgebrica.IGUALDADE)
				.setValorImediato(10)
				.setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA)
				.setValorImediato(20)
				.setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA)
				.setValorImediato(50)
				.setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA)
				.setValorImediato(20);

		CalculadoraAlgebrica calculadoraAlgebrica = new CalculadoraAlgebrica();

		List<String> resultadoEsperado = Arrays.asList(new String[] { "100" });

		Assert.assertEquals(resultadoEsperado, calculadoraAlgebrica
				.executaOperacoesLado(expressaoAlgebrica.getLadoDireito()));

	}

	public void testDadoUmaExpresao_A_Igual_10_Mais_20__Mais_20_Menos_50AoExecutarAsOperacoesDoLadoDireitoDeveRestarApenasUmaPosicaoNoladoDireitoComValor0() {

		Variavel var1 = new Variavel();
		var1.setId("A");
		ExpressaoAlgebrica expressaoAlgebrica = new ExpressaoAlgebrica();
		expressaoAlgebrica.setVariavel(var1)
				.setOperacao(EnumOperacaoesCalculadoraAlgebrica.IGUALDADE)
				.setValorImediato(10)
				.setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA)
				.setValorImediato(20)
				.setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA)
				.setValorImediato(20)
				.setOperacao(EnumOperacaoesCalculadoraAlgebrica.SUBTRACAO)
				.setValorImediato(50);

		CalculadoraAlgebrica calculadoraAlgebrica = new CalculadoraAlgebrica();

		List<String> resultadoEsperado = Arrays.asList(new String[] { "0" });

		Assert.assertEquals(resultadoEsperado, calculadoraAlgebrica
				.executaOperacoesLado(expressaoAlgebrica.getLadoDireito()));

	}

	public void testDadoOAlgarismo_10_e_20_eOOperador_SOMA_AoExecutarAOperacaoDeveRetornar30() {

		CalculadoraAlgebrica calculadoraAlgebrica = new CalculadoraAlgebrica();
		String resultadoEsperado = "30";

		try {
			Assert.assertEquals(
					resultadoEsperado,
					calculadoraAlgebrica
							.executaOperacao(
									"10",
									EnumOperacaoesCalculadoraAlgebrica
											.getSimboloOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA),
									"20"));
		} catch (OperacaoNaoSuportadaException e) {
			Assert.fail(e.getMessage());
			return;
		}
	}

	public void testDadoUmaExpressao_2A_Igual_20_mais_A_AoManterAsOcorrenciasDe_A_DoLadoEsquerdoDeveRetornar_A_Igual_20() {

		Variavel var1 = new Variavel();
		var1.setId("A");
		var1.setValorCompanheiro(2);

		Variavel var2 = new Variavel();
		var2.setId("A");

		ExpressaoAlgebrica expressaoAlgebrica = new ExpressaoAlgebrica();
		expressaoAlgebrica.setVariavel(var1)
				.setOperacao(EnumOperacaoesCalculadoraAlgebrica.IGUALDADE)
				.setValorImediato(20)
				.setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA)
				.setVariavel(var2);

		CalculadoraAlgebrica calculadoraAlgebrica = new CalculadoraAlgebrica();

		String expressaoEsperada = "2A-1A=20";

		Assert.assertEquals(
				expressaoEsperada,
				calculadoraAlgebrica.mantemOcorrenciasVariavelDoLadoEsquerdo(
						expressaoAlgebrica, "A").toString());

	}

	public void testDadoUmaExpressao_2A_Mais_3B_Igual_20_mais_A_AoManterAsOcorrenciasDe_A_DoLadoEsquerdoDeveRetornar_A_Igual_20() {

		Variavel var1 = new Variavel();
		var1.setId("A");
		var1.setValorCompanheiro(2);

		Variavel var2 = new Variavel();
		var2.setId("B");
		var2.setValorCompanheiro(3);

		Variavel var3 = new Variavel();
		var3.setId("A");

		ExpressaoAlgebrica expressaoAlgebrica = new ExpressaoAlgebrica();
		expressaoAlgebrica.setVariavel(var1)
				.setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA)
				.setVariavel(var2)
				.setOperacao(EnumOperacaoesCalculadoraAlgebrica.IGUALDADE)
				.setValorImediato(20)
				.setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA)
				.setVariavel(var3);

		CalculadoraAlgebrica calculadoraAlgebrica = new CalculadoraAlgebrica();

		String expressaoEsperada = "2A-1A=20-3B";
		
		ExpressaoAlgebrica expressaoResultado = calculadoraAlgebrica.mantemOcorrenciasVariavelDoLadoEsquerdo(expressaoAlgebrica, "A");

		Assert.assertEquals(expressaoEsperada,expressaoResultado.toString());

	}

	public void testDadoOsAlgarismos_2A_e_3A_eOOperador_SOMA_AoExecutarAOperacaoDeveRetornar5A() {

		String algarismo1 = "2A";
		String algarismo2 = "3A";
		String operador = EnumOperacaoesCalculadoraAlgebrica
				.getSimboloOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA);

		CalculadoraAlgebrica calculadoraAlgebrica = new CalculadoraAlgebrica();

		String resultadoEsperado = "5A";

		try {
			Assert.assertEquals(resultadoEsperado, calculadoraAlgebrica
					.executaOperacao(algarismo1, operador, algarismo2));
		} catch (OperacaoNaoSuportadaException e) {
			Assert.fail(e.getMessage());
			return;
		}

	}

	public void testDadoOsAlgarismos_2A_e_3B_eOOperador_SOMA_AoExecutarAOperacaoDeveRetornarExeceaoEFalhar() {

		String algarismo1 = "2A";
		String algarismo2 = "3B";
		String operador = EnumOperacaoesCalculadoraAlgebrica
				.getSimboloOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA);

		CalculadoraAlgebrica calculadoraAlgebrica = new CalculadoraAlgebrica();

		try {
			calculadoraAlgebrica.executaOperacao(algarismo1, operador,
					algarismo2);
		} catch (OperacaoNaoSuportadaException e) {
			return;
		}
		Assert.fail();

	}

	public void testDadoUmLadoDaExpressao_1A_mais_2_menos_3B_AoAgruparDeveRetornar_1A_3B_2() {

		CalculadoraAlgebrica calculadoraAlgebrica = new CalculadoraAlgebrica();

		List<String> ladoResultado = calculadoraAlgebrica
				.agrupaVariaveisLado(Arrays.asList(new String[] { "1A", "+",
						"2", "-", "3B" }));

		List<String> ladoEsperado = Arrays.asList(new String[] { "1A", "-",
				"3B", "+", "2" });

		Assert.assertEquals(ladoEsperado, ladoResultado);

	}
	
	public void testDadoUmaExpressao_2B_menos_11AoAgruparDeveRetornar_2B_menos_11(){
		
		CalculadoraAlgebrica calculadoraAlgebrica = new CalculadoraAlgebrica();

		List<String> ladoResultado = calculadoraAlgebrica.agrupaVariaveisLado(Arrays.asList(new String[] { "11", "-", "2B" }));

		List<String> ladoEsperado = calculadoraAlgebrica.agrupaVariaveisLado(Arrays.asList(new String[] { "-", "2B", "+", "11" }));

		Assert.assertEquals(ladoEsperado, ladoResultado);

	}

	public void testDadoUmLadoDaExpressao_1_menos_2_mais_3B_AoAgruparDeveRetornar_3B_1_2() {

		CalculadoraAlgebrica calculadoraAlgebrica = new CalculadoraAlgebrica();

		List<String> ladoResultado = calculadoraAlgebrica
				.agrupaVariaveisLado(Arrays.asList(new String[] { "1", "-",
						"2", "+", "3B" }));

		List<String> ladoEsperado = Arrays.asList(new String[] { "3B", "+",
				"1", "-", "2" });

		Assert.assertEquals(ladoEsperado, ladoResultado);

	}

	public void testDadoUmLadoDaExpressao_3_menos_1_mais_2_AoAgruparDeveRetornar_3_1_2() {

		CalculadoraAlgebrica calculadoraAlgebrica = new CalculadoraAlgebrica();

		List<String> ladoResultado = calculadoraAlgebrica
				.agrupaVariaveisLado(Arrays.asList(new String[] { "3", "-",
						"1", "+", "2" }));

		List<String> ladoEsperado = Arrays.asList(new String[] { "3", "-", "1",
				"+", "2" });

		Assert.assertEquals(ladoEsperado, ladoResultado);

	}

	public void testDadoUmLadoDaExpressao_3A_mais_2B_menos_2A_menos1_AoExecutarOLadoDeveRetornar_1A_mais2B_menos1() {

		CalculadoraAlgebrica calculadoraAlgebrica = new CalculadoraAlgebrica();

		List<String> ladoResultado = calculadoraAlgebrica
				.executaOperacoesLado(Arrays.asList(new String[] { "3A", "+",
						"2B", "-", "2A", "-", "1" }));

		List<String> ladoEsperado = Arrays.asList(new String[] { "1A", "+",
				"2B", "-", "1" });

		Assert.assertEquals(ladoEsperado, ladoResultado);

	}

	public void testDadoUmLadoDaExpressao_3A_mais_2A_menos_2A_menos1_AoExecutarOLadoDeveRetornar_3A_menos1() {

		CalculadoraAlgebrica calculadoraAlgebrica = new CalculadoraAlgebrica();

		List<String> ladoResultado = calculadoraAlgebrica
				.executaOperacoesLado(Arrays.asList(new String[] { "3A", "+",
						"2A", "-", "2A", "-", "1" }));

		List<String> ladoEsperado = Arrays
				.asList(new String[] { "3A", "-", "1" });

		Assert.assertEquals(ladoEsperado, ladoResultado);

	}

	public void testDadoUmLadoDaExpressao_3A_mais_2B_menos_2C_menos1_AoExecutarOLadoDeveRetornar_3A_mais_2B_menos_2C_menos1() {

		CalculadoraAlgebrica calculadoraAlgebrica = new CalculadoraAlgebrica();

		List<String> ladoResultado = calculadoraAlgebrica
				.executaOperacoesLado(Arrays.asList(new String[] { "3A", "+",
						"2B", "-", "2C", "-", "1" }));

		List<String> ladoEsperado = Arrays.asList(new String[] { "3A", "+",
				"2B", "-", "2C", "-", "1" });

		Assert.assertEquals(ladoEsperado, ladoResultado);

	}
	
	/**
	 * E1 => 1A + 2B = 11 
	 * E2 => 1A = 11 - 2B
	 */
	public void testDadoUmaExpressao_E1_AoIsolarAVariavel_A_DeveRetornarUmaExpressao_E2(){
		
		String identificadorVariavel = "A";

		ExpressaoAlgebrica expressaoAlgebrica1 = new ExpressaoAlgebrica();
		expressaoAlgebrica1
				.setVariavel("A")
				.setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA)
				.setVariavel("2B")
				.setOperacao(EnumOperacaoesCalculadoraAlgebrica.IGUALDADE)
				.setValorImediato(11);
		
		CalculadoraAlgebrica calculadoraAlgebrica = new CalculadoraAlgebrica();
		ExpressaoAlgebrica expressaoAlgebrica = calculadoraAlgebrica.isolaVariavel(expressaoAlgebrica1, identificadorVariavel);
		
		Assert.assertEquals("1A=-2B+11", expressaoAlgebrica.toString());
	}

	/**
	 * E1 => 1A + 2B = 11 
	 * E2 => 2 + A = 1B + 1 
	 * A = 3 
	 * B = 4
	 */
	public void testDadoDuasExpressoes_E1_E_E2_aoIsolar_A_AMesmaDeveTerOValor() {
		
		String identificadorVariavel = "A";

		ExpressaoAlgebrica expressaoAlgebrica1 = new ExpressaoAlgebrica();
		expressaoAlgebrica1
				.setVariavel("A")
				.setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA)
				.setVariavel("2B")
				.setOperacao(EnumOperacaoesCalculadoraAlgebrica.IGUALDADE)
				.setValorImediato(11);

		ExpressaoAlgebrica expressaoAlgebrica2 = new ExpressaoAlgebrica();
			expressaoAlgebrica2.setValorImediato(2)
				.setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA)
				.setVariavel("A")
				.setOperacao(EnumOperacaoesCalculadoraAlgebrica.IGUALDADE)
				.setVariavel("B")
				.setOperacao(EnumOperacaoesCalculadoraAlgebrica.IGUALDADE)
				.setValorImediato(11);
			
		CalculadoraAlgebrica calculadoraAlgebrica = new CalculadoraAlgebrica();
		ExpressaoAlgebrica expressaoAlgebrica = calculadoraAlgebrica.isolaVariavel(expressaoAlgebrica1,expressaoAlgebrica2, identificadorVariavel);
		
		Assert.assertEquals("A=3", expressaoAlgebrica.toString());

	}

}
