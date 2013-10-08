package ufms.calculadora.test.extensoes;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.jdom.JDOMException;

import ufms.calculadora.extensoes.leitorXML.TabelaPeriodica;
import ufms.calculadora.modelo.Elemento;

public class LeitorXMLTest extends TestCase {
	
	public void testLeitorXML() {
	
		List<Elemento> tabelaPeriodica = new ArrayList<Elemento>();
		TabelaPeriodica leitor = new TabelaPeriodica();
		try {
			tabelaPeriodica = leitor.carregarXML("elementos.xml");
		} catch (JDOMException e) {
			e.printStackTrace();
			Assert.fail();
		}
		
		
		Assert.assertNotSame(0, tabelaPeriodica.size());
	}
	

}
