package ufms.calculadora.test.extensoes;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

import ufms.calculadora.extensoes.calculadoraAlgebrica.OperacaoNaoSuportadaException;
import ufms.calculadora.extensoes.calculadoraAlgebrica.OperacaoSoma;

public class OperacaoSomaTest extends TestCase{

	
	@Test
	public void testDadoDoisInteiros_1_e_2_deveRetornar_3(){
		
		OperacaoSoma operacaoSoma = new OperacaoSoma();
		String resultadoEsperado = "3";
		
		Assert.assertEquals(resultadoEsperado, operacaoSoma.executaOperacao(1, 2));
		
	}
	
	@Test
	public void testDadoUmaString_4A_e_umaString_3A_deveRetornar7A(){
		
		OperacaoSoma operacaoSoma = new OperacaoSoma();
		String resultadoEsperado = "7A";
		
		try {
			Assert.assertEquals(resultadoEsperado, operacaoSoma.executaOperacao("4A", "3A"));
		} catch (OperacaoNaoSuportadaException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testDadoUmaString_4A_e_umaString_3B_deveRetornarUmaExeceaoDeOperacaoNaoSuportada(){
		
		OperacaoSoma operacaoSoma = new OperacaoSoma();
		
		try {
			operacaoSoma.executaOperacao("4A", "3B");
		} catch (OperacaoNaoSuportadaException e) {
			return;
		}
		Assert.fail();
	}
}
