package ufms.calculadora.test.negocio;

import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;
import ufms.calculadora.modelo.Elemento;
import ufms.calculadora.negocio.TabelaPeriodica;

public class TabelaPeriodicaTest extends TestCase{

	public void testCarregarElementos(){
		
		List<Elemento> elementosCarregados = TabelaPeriodica.getElementos();
		
		Assert.assertNotNull(elementosCarregados);
	}
}
