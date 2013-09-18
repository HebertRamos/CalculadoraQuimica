package ufms.calculadora.test.modelo;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

import ufms.calculadora.modelo.Elemento;
import ufms.calculadora.modelo.EnumSiglaElemento;

/**
 * 
 * @author hramos
 *
 */
public class ElementoTest extends TestCase{
	
	
	@Test
	public void testDadoDoisElementosComAMesmaSiglaElesDevRetornarTrue(){
		
		Elemento oxigenio1 = new Elemento();
		oxigenio1.setSigla(EnumSiglaElemento.O);
		
		Elemento oxigenio2 = new Elemento();
		oxigenio2.setSigla(EnumSiglaElemento.O);
		
		Assert.assertEquals(oxigenio1.equals(oxigenio2), true);
	}
	
	@Test
	public void testDadoDoisElementosComASiglaDiferenteElesDeveRetornarFalse(){
		
		Elemento oxigenio1 = new Elemento();
		oxigenio1.setSigla(EnumSiglaElemento.O);
		
		Elemento hidrogenio = new Elemento();
		hidrogenio.setSigla(EnumSiglaElemento.H);
		
		Assert.assertEquals(oxigenio1.equals(hidrogenio), false);
	}
	
	@Test
	public void testDadoDoisElementosComASiglaIguaisMasComIndiceDiferenteDeveRetornarFalse(){
		
		Elemento oxigenio1 = new Elemento();
		oxigenio1.setSigla(EnumSiglaElemento.O);
		
		Elemento oxigenio2 = new Elemento();
		oxigenio2.setSigla(EnumSiglaElemento.O);
		oxigenio2.setIndice(2);
		
		Assert.assertEquals(oxigenio1.equals(oxigenio2), false);
	}
	
	@Test
	public void testDadoDoisElementosComASiglaEComIndiceIguaiDeveRetornarTrue(){
		
		Elemento oxigenio1 = new Elemento();
		oxigenio1.setSigla(EnumSiglaElemento.O);
		oxigenio1.setIndice(2);
		
		Elemento oxigenio2 = new Elemento();
		oxigenio2.setSigla(EnumSiglaElemento.O);
		oxigenio2.setIndice(2);
		
		Assert.assertEquals(oxigenio1.equals(oxigenio2), true);
	}
	
	@Test
	public void testDadoDoisElementosComASiglaIguaisMasComCoeficienteDiferenteDeveRetornarFalse(){
		
		Elemento oxigenio1 = new Elemento();
		oxigenio1.setSigla(EnumSiglaElemento.O);
		
		Elemento oxigenio2 = new Elemento();
		oxigenio2.setSigla(EnumSiglaElemento.O);
		oxigenio2.setCoeficiente(2);
		
		Assert.assertEquals(oxigenio1.equals(oxigenio2), false);
	}
	
	@Test
	public void testDadoDoisElementosComASiglaEComCoeficienteIguaiDeveRetornarTrue(){
		
		Elemento oxigenio1 = new Elemento();
		oxigenio1.setSigla(EnumSiglaElemento.O);
		oxigenio1.setCoeficiente(2);
		
		Elemento oxigenio2 = new Elemento();
		oxigenio2.setSigla(EnumSiglaElemento.O);
		oxigenio2.setCoeficiente(2);
		
		Assert.assertEquals(oxigenio1.equals(oxigenio2), true);
	}



}
