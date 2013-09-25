package ufms.calculadora.test.modelo;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

import ufms.calculadora.modelo.Elemento;
import ufms.calculadora.modelo.EnumSiglaElemento;
import ufms.calculadora.modelo.Solucao;

public class SolucaoTest extends TestCase{
	
	public void testDadoUmaSolucaoComOxigenioAoInfomarASiglaDoOxigenioNaBuscaDeElementoPelaSiglaDeveRetonarOMesmo(){
		
		Elemento oxigenioR = new Elemento();
		oxigenioR.setSigla(EnumSiglaElemento.O);
		
		Solucao solucao = new Solucao();
		solucao.adicionarElemento(oxigenioR);
		
		Elemento elementoEncontrado = solucao.buscaElementoPelaSigla(EnumSiglaElemento.O);
		
		Assert.assertEquals(oxigenioR, elementoEncontrado);
	}
	
	public void testDadoUmaSolucaoComOxigenioAoInfomarASiglaDoHidrogenioNaBuscaDeElementoPelaSiglaDeveRetonarNull(){
		
		Elemento oxigenioR = new Elemento();
		oxigenioR.setSigla(EnumSiglaElemento.O);
		
		Solucao solucao = new Solucao();
		solucao.adicionarElemento(oxigenioR);
		
		Elemento elementoEncontrado = solucao.buscaElementoPelaSigla(EnumSiglaElemento.H);
		
		Assert.assertEquals(null, elementoEncontrado);
	}
	
	@Test
	public void testDadoUmaSolucaoDeAguaAoCompararComOutraSolucaoDeAguaDeveRetornarTrue(){
		
		//Solução Entrada1
		Elemento oxigenio1 = new Elemento();
		oxigenio1.setSigla(EnumSiglaElemento.O);
		
		Elemento hidrogenio1 = new Elemento();
		hidrogenio1.setSigla(EnumSiglaElemento.H);
		hidrogenio1.setCoeficiente(2);
		
		Solucao solucao1 = new Solucao();
		solucao1.adicionarElemento(hidrogenio1);
		solucao1.adicionarElemento(oxigenio1);
		
		//Solução Entrada2
		Elemento oxigenio2 = new Elemento();
		oxigenio2.setSigla(EnumSiglaElemento.O);
		
		Elemento hidrogenio2 = new Elemento();
		hidrogenio2.setSigla(EnumSiglaElemento.H);
		hidrogenio2.setCoeficiente(1);
		
		Solucao solucao2 = new Solucao();
		solucao2.adicionarElemento(hidrogenio1);
		solucao2.adicionarElemento(oxigenio2);
		
		Assert.assertEquals(true, solucao1.equals(solucao2));
	}
	
	@Test
	public void testDadoUmaSolucaoDeAguaAoCompararComOutraSolucaoDeHODeveRetornarFalse(){
		
		//Solução Entrada1
		Elemento oxigenio1 = new Elemento();
		oxigenio1.setSigla(EnumSiglaElemento.O);
		
		Elemento hidrogenio1 = new Elemento();
		hidrogenio1.setSigla(EnumSiglaElemento.H);
		hidrogenio1.setCoeficiente(2);
		
		Solucao solucao1 = new Solucao();
		solucao1.adicionarElemento(hidrogenio1);
		solucao1.adicionarElemento(oxigenio1);
		
		//Solução Entrada2
		Elemento oxigenio2 = new Elemento();
		oxigenio2.setSigla(EnumSiglaElemento.O);
		
		Elemento hidrogenio2 = new Elemento();
		hidrogenio2.setSigla(EnumSiglaElemento.H);
		
		Solucao solucao2 = new Solucao();
		solucao2.adicionarElemento(hidrogenio2);
		solucao2.adicionarElemento(oxigenio2);
		
		Assert.assertEquals(false, solucao1.equals(solucao2));
	}



}
