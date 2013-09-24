package ufms.calculadora.test.extensoes;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

import ufms.calculadora.extensoes.calculadoraAlgebrica.OperacaoNaoSuportadaException;
import ufms.calculadora.extensoes.calculadoraAlgebrica.OperacaoSubtracao;

public class OperacaoSubtracaoTest extends TestCase {

	@Test
	public void testDadoDoisInteiros_1_e_2_deveRetornar_1() {

		OperacaoSubtracao operacaoSubtracao = new OperacaoSubtracao();
		String resultadoEsperado = "1";

		Assert.assertEquals(resultadoEsperado, operacaoSubtracao.executaOperacao(1, 2));

	}

	@Test
	public void testDadoDoisInteiros_2_e_1_deveRetornar_1() {

		OperacaoSubtracao operacaoSubtracao = new OperacaoSubtracao();
		String resultadoEsperado = "1";

		Assert.assertEquals(resultadoEsperado, operacaoSubtracao.executaOperacao(2, 1));

	}

	@Test
	public void testDadoUmaString_4A_e_umaString_3A_deveRetornar1A() {

		OperacaoSubtracao operacaoSubtracao = new OperacaoSubtracao();
		String resultadoEsperado = "1A";

		try {
			Assert.assertEquals(resultadoEsperado, operacaoSubtracao.executaOperacao("4A", "3A"));
		} catch (OperacaoNaoSuportadaException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testDadoUmaString_3A_e_umaString_4A_deveRetornar1A() {

		OperacaoSubtracao operacaoSubtracao = new OperacaoSubtracao();
		String resultadoEsperado = "1A";

		try {
			Assert.assertEquals(resultadoEsperado, operacaoSubtracao.executaOperacao("3A", "4A"));
		} catch (OperacaoNaoSuportadaException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testDadoUmaString_4A_e_umaString_3B_deveRetornarUmaExeceaoDeOperacaoNaoSuportada() {

		OperacaoSubtracao operacaoSubtracao = new OperacaoSubtracao();

		try {
			operacaoSubtracao.executaOperacao("4A", "3B");
		} catch (OperacaoNaoSuportadaException e) {
			return;
		}
		Assert.fail();
	}
}
