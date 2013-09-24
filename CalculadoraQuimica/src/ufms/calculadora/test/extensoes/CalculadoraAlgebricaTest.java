package ufms.calculadora.test.extensoes;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

import ufms.calculadora.extensoes.calculadoraAlgebrica.CalculadoraAlgebrica;
import ufms.calculadora.extensoes.calculadoraAlgebrica.EnumOperacaoesCalculadoraAlgebrica;
import ufms.calculadora.extensoes.calculadoraAlgebrica.ExpressaoAlgebrica;
import ufms.calculadora.extensoes.calculadoraAlgebrica.Variavel;

public class CalculadoraAlgebricaTest extends TestCase {

	/** Executa Soma **/
	@Test
	public void testDadoOAlgarismo_mais_10_e_mais_20_AoExecutarAOperacaoDeveRetornar30() {

		CalculadoraAlgebrica calculadoraAlgebrica = new CalculadoraAlgebrica();
		List<String> resultadoEsperado = Arrays.asList(new String[] { "+", "30" });

		Assert.assertEquals(resultadoEsperado, calculadoraAlgebrica.executaOperacaoSoma("+", "10", "+", "20"));

	}

	@Test
	public void testDadoOsAlgarismos_mais_2A_e_mais_3A_eAoExecutarAOperacaoDeveRetornar5A() {

		CalculadoraAlgebrica calculadoraAlgebrica = new CalculadoraAlgebrica();
		List<String> resultadoEsperado = Arrays.asList(new String[] { "+", "5A" });

		Assert.assertEquals(resultadoEsperado, calculadoraAlgebrica.executaOperacaoSoma("+", "2A", "+", "3A"));

	}

	@Test
	public void testDadoOsAlgarismos_mais_2A_e_mais_3B_eAoExecutarAOperacaoDeveRetornarAMesmaExpressao() {

		CalculadoraAlgebrica calculadoraAlgebrica = new CalculadoraAlgebrica();
		List<String> resultadoEsperado = Arrays.asList(new String[] { "+", "2A", "+", "3B" });

		Assert.assertEquals(resultadoEsperado, calculadoraAlgebrica.executaOperacaoSoma("+", "2A", "+", "3B"));
	}

	@Test
	public void testDadoOAlgarismo_mais_10_e_menos_20_AoExecutarAOperacaoDeveRetornar_menos_10() {

		CalculadoraAlgebrica calculadoraAlgebrica = new CalculadoraAlgebrica();
		List<String> resultadoEsperado = Arrays.asList(new String[] { "-", "10" });

		Assert.assertEquals(resultadoEsperado, calculadoraAlgebrica.executaOperacaoSoma("+", "10", "-", "20"));

	}

	@Test
	public void testDadoOAlgarismo_mais_20_e_menos_10_AoExecutarAOperacaoDeveRetornar_mais_10() {

		CalculadoraAlgebrica calculadoraAlgebrica = new CalculadoraAlgebrica();
		List<String> resultadoEsperado = Arrays.asList(new String[] { "+", "10" });

		Assert.assertEquals(resultadoEsperado, calculadoraAlgebrica.executaOperacaoSoma("+", "20", "-", "10"));

	}

	@Test
	public void testDadoOsAlgarismos_mais_2A_e_menos_3A_eAoExecutarAOperacaoDeveRetornar_menos_1A() {

		CalculadoraAlgebrica calculadoraAlgebrica = new CalculadoraAlgebrica();
		List<String> resultadoEsperado = Arrays.asList(new String[] { "-", "1A" });

		Assert.assertEquals(resultadoEsperado, calculadoraAlgebrica.executaOperacaoSoma("+", "2A", "-", "3A"));

	}

	@Test
	public void testDadoOsAlgarismos_menos_2A_e_mais_3A_eAoExecutarAOperacaoDeveRetornarA_mais_1A() {

		CalculadoraAlgebrica calculadoraAlgebrica = new CalculadoraAlgebrica();
		List<String> resultadoEsperado = Arrays.asList(new String[] { "+", "1A", });

		Assert.assertEquals(resultadoEsperado, calculadoraAlgebrica.executaOperacaoSoma("-", "2A", "+", "3A"));
	}

	/** Executa Operacoes Lado **/
	@Test
	public void testDadoUmaLado_10_Mais_20_AoExecutarAsOperacoesDoLadooDeveRetornar_mais_30() {

		List<String> ladoEntrada = Arrays.asList(new String[] { "+", "10", "+", "20" });

		CalculadoraAlgebrica calculadoraAlgebrica = new CalculadoraAlgebrica();

		List<String> resultadoEsperado = Arrays.asList(new String[] { "+", "30" });

		Assert.assertEquals(resultadoEsperado, calculadoraAlgebrica.executaOperacoesLado(ladoEntrada));

	}

	@Test
	public void testDadoLado_Mais_10_Mais_20__Mais_50_Menos_20_AoExecutarAsOperacoesDoLado_600() {

		List<String> ladoEntrada = Arrays.asList(new String[] { "+", "10", "+", "20", "+", "50", "-", "20" });

		CalculadoraAlgebrica calculadoraAlgebrica = new CalculadoraAlgebrica();

		List<String> resultadoEsperado = Arrays.asList(new String[] { "+", "60" });

		Assert.assertEquals(resultadoEsperado, calculadoraAlgebrica.executaOperacoesLado(ladoEntrada));

	}

	public void testDadoUmLado_mais_3A_mais_2B_menos_2A_menos_1_AoExecutarOLadoDeveRetornar_1A_mais2B_menos_1() {

		CalculadoraAlgebrica calculadoraAlgebrica = new CalculadoraAlgebrica();

		List<String> ladoResultado = calculadoraAlgebrica.executaOperacoesLado(Arrays.asList(new String[] { "+", "3A", "+", "2B", "-", "2A", "-", "1" }));

		List<String> ladoEsperado = Arrays.asList(new String[] { "+", "1A", "+", "2B", "-", "1" });

		Assert.assertEquals(ladoEsperado, ladoResultado);

	}

	public void testDadoUmLadoDaExpressao_mais_3A_mais_2A_menos_2A_menos_1_AoExecutarOLadoDeveRetornar_mais_3A_menos_1() {

		CalculadoraAlgebrica calculadoraAlgebrica = new CalculadoraAlgebrica();

		List<String> ladoResultado = calculadoraAlgebrica.executaOperacoesLado(Arrays.asList(new String[] { "+", "3A", "+", "2A", "-", "2A", "-", "1" }));

		List<String> ladoEsperado = Arrays.asList(new String[] { "+", "3A", "-", "1" });

		Assert.assertEquals(ladoEsperado, ladoResultado);

	}

	public void testDadoUmLadoDaExpressao_mais_3A_mais_2B_menos_2C_menos1_AoExecutarOLadoDeveRetornar_mais_3A_mais_2B_menos_2C_menos_1() {

		CalculadoraAlgebrica calculadoraAlgebrica = new CalculadoraAlgebrica();

		List<String> ladoResultado = calculadoraAlgebrica.executaOperacoesLado(Arrays.asList(new String[] { "+", "3A", "+", "2B", "-", "2C", "-", "1" }));

		List<String> ladoEsperado = Arrays.asList(new String[] { "+", "3A", "+", "2B", "-", "2C", "-", "1" });

		Assert.assertEquals(ladoEsperado, ladoResultado);

	}

	public void testDadoUmLadoDaExpressao_1A_mais_2_menos_3B_AoAgruparDeveRetornar_1A_3B_2() {

		CalculadoraAlgebrica calculadoraAlgebrica = new CalculadoraAlgebrica();

		List<String> ladoResultado = calculadoraAlgebrica.agrupaVariaveisLado(Arrays.asList(new String[] { "+", "1A", "+", "2", "-", "3B" }));

		List<String> ladoEsperado = Arrays.asList(new String[] { "+", "1A", "-", "3B", "+", "2" });

		Assert.assertEquals(ladoEsperado, ladoResultado);

	}

	public void testDadoUmaExpressao_2B_menos_11AoAgruparDeveRetornar_2B_menos_11() {

		CalculadoraAlgebrica calculadoraAlgebrica = new CalculadoraAlgebrica();

		List<String> ladoResultado = calculadoraAlgebrica.agrupaVariaveisLado(Arrays.asList(new String[] { "11", "-", "2B" }));

		List<String> ladoEsperado = calculadoraAlgebrica.agrupaVariaveisLado(Arrays.asList(new String[] { "-", "2B", "+", "11" }));

		Assert.assertEquals(ladoEsperado, ladoResultado);

	}

	public void testDadoUmLadoDaExpressao_1_menos_2_mais_3B_AoAgruparDeveRetornar_3B_1_2() {

		CalculadoraAlgebrica calculadoraAlgebrica = new CalculadoraAlgebrica();

		List<String> ladoResultado = calculadoraAlgebrica.agrupaVariaveisLado(Arrays.asList(new String[] { "+", "1", "-", "2", "+", "3B" }));

		List<String> ladoEsperado = Arrays.asList(new String[] { "+", "3B", "+", "1", "-", "2" });

		Assert.assertEquals(ladoEsperado, ladoResultado);

	}

	public void testDadoUmLadoDaExpressao_3_menos_1_mais_2_AoAgruparDeveRetornar_3_1_2() {

		CalculadoraAlgebrica calculadoraAlgebrica = new CalculadoraAlgebrica();

		List<String> ladoResultado = calculadoraAlgebrica.agrupaVariaveisLado(Arrays.asList(new String[] { "+", "3", "-", "1", "+", "2" }));

		List<String> ladoEsperado = Arrays.asList(new String[] { "+", "3", "-", "1", "+", "2" });

		Assert.assertEquals(ladoEsperado, ladoResultado);

	}

	/** InverteLado **/

	public void testDadoUmaExpressao_mais_2A_Igual_mais_20_mais_A_AoTrocar_mais_A_deLadoOLadoEsquerdoDeveFicar_mais_2A_menos_A() {

		Variavel var1 = new Variavel();
		var1.setId("A");
		var1.setValorCompanheiro(2);

		Variavel var2 = new Variavel();
		var2.setId("A");

		ExpressaoAlgebrica expressaoAlgebrica = new ExpressaoAlgebrica();
		expressaoAlgebrica.setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA).setVariavel(var1).setOperacao(EnumOperacaoesCalculadoraAlgebrica.IGUALDADE).setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA).setValorImediato(20)
				.setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA).setVariavel(var2);

		CalculadoraAlgebrica calculadoraAlgebrica = new CalculadoraAlgebrica();

		List<String> ladoEsquerdoEsperada = Arrays.asList(new String[] { "+", "2A", "-", "1A" });

		calculadoraAlgebrica.trocaAlgarismoDeLado("+", "1A", expressaoAlgebrica.getLadoDireito(), expressaoAlgebrica.getLadoEsquerdo());

		Assert.assertEquals(ladoEsquerdoEsperada, expressaoAlgebrica.getLadoEsquerdo());
	}

	public void testDadoUmaExpressao_mais_2A_Igual_mais_20_mais_A_AoTrocar_mais_A_deLadoOLadoDireitoDeveFicar_mais_20() {

		Variavel var1 = new Variavel();
		var1.setId("A");
		var1.setValorCompanheiro(2);

		Variavel var2 = new Variavel();
		var2.setId("A");

		ExpressaoAlgebrica expressaoAlgebrica = new ExpressaoAlgebrica();
		expressaoAlgebrica.setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA).setVariavel(var1).setOperacao(EnumOperacaoesCalculadoraAlgebrica.IGUALDADE).setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA).setValorImediato(20)
				.setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA).setVariavel(var2);

		CalculadoraAlgebrica calculadoraAlgebrica = new CalculadoraAlgebrica();

		List<String> ladoDireitoEsperado = Arrays.asList(new String[] { "+", "20" });

		calculadoraAlgebrica.trocaAlgarismoDeLado("+", "1A", expressaoAlgebrica.getLadoDireito(), expressaoAlgebrica.getLadoEsquerdo());

		Assert.assertEquals(ladoDireitoEsperado, expressaoAlgebrica.getLadoDireito());
	}

	public void testDadoUmaExpressao_mais_3B_menos_20_Igual_mais_20_mais_A_mais_A_AoTrocar_mais_A_deLadoOLadoEsquerdoDeveFicar_mais_3B_mais_20_menos_A() {

		Variavel var1 = new Variavel("3B");
		Variavel var2 = new Variavel("A");
		Variavel var3 = new Variavel("A");

		ExpressaoAlgebrica expressaoAlgebrica = new ExpressaoAlgebrica();
		expressaoAlgebrica.setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA).setVariavel(var1).setOperacao(EnumOperacaoesCalculadoraAlgebrica.SUBTRACAO).setValorImediato(20).setOperacao(EnumOperacaoesCalculadoraAlgebrica.IGUALDADE)
				.setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA).setValorImediato(20).setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA).setVariavel(var2).setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA).setVariavel(var3);

		CalculadoraAlgebrica calculadoraAlgebrica = new CalculadoraAlgebrica();

		List<String> ladoEsquerdoEsperada = Arrays.asList(new String[] { "+", "3B", "-", "20", "-", "1A" });

		calculadoraAlgebrica.trocaAlgarismoDeLado("+", "1A", expressaoAlgebrica.getLadoDireito(), expressaoAlgebrica.getLadoEsquerdo());

		Assert.assertEquals(ladoEsquerdoEsperada, expressaoAlgebrica.getLadoEsquerdo());
	}

	public void testDadoUmaExpressao_mais_3B_menos_20_Igual_mais_20_mais_A_mais_A_AoTrocar_mais_A_deLadoOLadoDireitoDeveFicar_mais_20_mais_A() {

		Variavel var1 = new Variavel("3B");
		Variavel var2 = new Variavel("A");
		Variavel var3 = new Variavel("A");

		ExpressaoAlgebrica expressaoAlgebrica = new ExpressaoAlgebrica();
		expressaoAlgebrica.setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA).setVariavel(var1).setOperacao(EnumOperacaoesCalculadoraAlgebrica.SUBTRACAO).setValorImediato(20).setOperacao(EnumOperacaoesCalculadoraAlgebrica.IGUALDADE)
				.setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA).setValorImediato(20).setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA).setVariavel(var2).setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA).setVariavel(var3);

		CalculadoraAlgebrica calculadoraAlgebrica = new CalculadoraAlgebrica();

		List<String> ladoEsquerdoEsperada = Arrays.asList(new String[] { "+", "20", "+", "1A" });

		calculadoraAlgebrica.trocaAlgarismoDeLado("+", "1A", expressaoAlgebrica.getLadoDireito(), expressaoAlgebrica.getLadoEsquerdo());

		Assert.assertEquals(ladoEsquerdoEsperada, expressaoAlgebrica.getLadoDireito());
	}

	/** Manter as ocorrencias em um lado **/

	public void testDadoUmaExpressao_mais_2A_Igual_20_mais_A_AoManterAsOcorrenciasDe_A_DoLadoEsquerdoDeveRetornar_A_Igual_20() {

		Variavel var1 = new Variavel();
		var1.setId("A");
		var1.setValorCompanheiro(2);

		Variavel var2 = new Variavel();
		var2.setId("A");

		ExpressaoAlgebrica expressaoAlgebrica = new ExpressaoAlgebrica();
		expressaoAlgebrica.setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA).setVariavel(var1).setOperacao(EnumOperacaoesCalculadoraAlgebrica.IGUALDADE).setValorImediato(20).setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA)
				.setVariavel(var2);

		CalculadoraAlgebrica calculadoraAlgebrica = new CalculadoraAlgebrica();

		String expressaoEsperada = "+2A-1A=20";

		Assert.assertEquals(expressaoEsperada, calculadoraAlgebrica.mantemOcorrenciasVariavelDoLadoEsquerdo(expressaoAlgebrica, "A").toString());

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
		expressaoAlgebrica.setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA).setVariavel(var1).setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA).setVariavel(var2).setOperacao(EnumOperacaoesCalculadoraAlgebrica.IGUALDADE)
				.setValorImediato(20).setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA).setVariavel(var3);

		CalculadoraAlgebrica calculadoraAlgebrica = new CalculadoraAlgebrica();

		String expressaoEsperada = "+2A-1A=20-3B";

		ExpressaoAlgebrica expressaoResultado = calculadoraAlgebrica.mantemOcorrenciasVariavelDoLadoEsquerdo(expressaoAlgebrica, "A");

		Assert.assertEquals(expressaoEsperada, expressaoResultado.toString());

	}

	/** Aplicar Condição **/
	public void testDadoUmaExpressao_mais_2A_Mais_3B_Igual_mais_20_mais_A_AoAplicarACOndicao_A_igual_mais_10_DeveRetornarUmaExpressa_mais_20_mais_3B_igual_mais_10() {

		Variavel var1 = new Variavel();
		var1.setId("A");
		var1.setValorCompanheiro(2);

		Variavel var2 = new Variavel();
		var2.setId("B");
		var2.setValorCompanheiro(3);

		Variavel var3 = new Variavel();
		var3.setId("A");

		ExpressaoAlgebrica expressaoAlgebrica = new ExpressaoAlgebrica();
		expressaoAlgebrica.setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA).setVariavel(var1).setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA).setVariavel(var2).setOperacao(EnumOperacaoesCalculadoraAlgebrica.IGUALDADE)
				.setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA).setValorImediato(20).setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA).setVariavel(var3);

		CalculadoraAlgebrica calculadoraAlgebrica = new CalculadoraAlgebrica();

		Variavel condicao = new Variavel("A");
		condicao.setValor(10);
		String sinal = "+";

		String expressaoEsperada = "+20+3B=+20+10";

		ExpressaoAlgebrica expressaoResultado = calculadoraAlgebrica.aplicaCondicao(expressaoAlgebrica, condicao, sinal);

		Assert.assertEquals(expressaoEsperada, expressaoResultado.toString());

	}

	/** IsolaVariavel **/
	/**
	 * E1 => + 1A + 2B = + 11 E2 => + 1A = + 11 - 2B
	 */
	public void testDadoUmaExpressao_E1_AoIsolarAVariavel_A_DeveRetornarUmaExpressao_E2() {

		String identificadorVariavel = "A";

		ExpressaoAlgebrica expressaoAlgebrica1 = new ExpressaoAlgebrica();
		expressaoAlgebrica1.setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA).setVariavel("A")

		.setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA).setVariavel("2B")

		.setOperacao(EnumOperacaoesCalculadoraAlgebrica.IGUALDADE)

		.setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA).setValorImediato(11);

		CalculadoraAlgebrica calculadoraAlgebrica = new CalculadoraAlgebrica();
		ExpressaoAlgebrica expressaoAlgebrica = calculadoraAlgebrica.isolaVariavel(expressaoAlgebrica1, identificadorVariavel);

		Assert.assertEquals("+1A=-2B+11", expressaoAlgebrica.toString());
	}

	/**
	 * E1 => + 1B = + 20 + A E2 => + 2 + 1A = + 2B + 1 E3 => + 2 + 1A = + 40 +
	 * 2A + 1
	 */
	public void testDadoDuasExpressoes_E1_AoAplicar_E2_ComoCondicacaoDeveRetornar_E3_() {

		ExpressaoAlgebrica expressaoAlgebrica1 = new ExpressaoAlgebrica();
		expressaoAlgebrica1.setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA).setValorImediato(2).setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA).setVariavel("A").setOperacao(EnumOperacaoesCalculadoraAlgebrica.IGUALDADE)
				.setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA).setVariavel("2B").setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA).setValorImediato(1);

		ExpressaoAlgebrica expressaoAlgebricaCondicao = new ExpressaoAlgebrica();
		expressaoAlgebricaCondicao.setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA).setVariavel("B").setOperacao(EnumOperacaoesCalculadoraAlgebrica.IGUALDADE).setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA).setValorImediato(20)
				.setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA).setVariavel("A");

		Variavel condicao = new Variavel("B");

		CalculadoraAlgebrica calculadoraAlgebrica = new CalculadoraAlgebrica();
		ExpressaoAlgebrica expressaoAlgebrica = calculadoraAlgebrica.aplicaCondicao(expressaoAlgebrica1, condicao, expressaoAlgebricaCondicao);

		Assert.assertEquals("+2+1A=+40+2A+1", expressaoAlgebrica.toString());
	}

	/**
	 * E1 => + 1A + 1B = + 11 E2 => + 1 + 1A = + 2B + 1 A = 3 B = 4
	 */
	public void testDadoDuasExpressoes_E1_E_E2_aoIsolar_A_AMesmaDeveTerOValor() {

		String identificadorVariavel = "A";

		ExpressaoAlgebrica expressaoAlgebrica1 = new ExpressaoAlgebrica();
		expressaoAlgebrica1.setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA).setVariavel("A").setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA).setVariavel("B").setOperacao(EnumOperacaoesCalculadoraAlgebrica.IGUALDADE)
				.setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA).setValorImediato(11);

		ExpressaoAlgebrica expressaoAlgebrica2 = new ExpressaoAlgebrica();
		expressaoAlgebrica2.setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA).setValorImediato(2).setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA).setVariavel("1A").setOperacao(EnumOperacaoesCalculadoraAlgebrica.IGUALDADE)
				.setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA).setVariavel("2B").setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA).setValorImediato(1);

		CalculadoraAlgebrica calculadoraAlgebrica = new CalculadoraAlgebrica();
		ExpressaoAlgebrica expressaoAlgebrica = calculadoraAlgebrica.isolaVariavel(expressaoAlgebrica1, expressaoAlgebrica2, identificadorVariavel);

		Assert.assertEquals("+1A=+7", expressaoAlgebrica.toString());

	}

}
