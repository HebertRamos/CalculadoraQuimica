package ufms.calculadora.negocio;

import java.util.ArrayList;
import java.util.List;

import ufms.calculadora.modelo.Elemento;
import ufms.calculadora.modelo.EnumSiglaElemento;

public class TabelaPeriodica {
	
	private static List<Elemento> elementos;
	
	public static List<Elemento> getElementos(){
		
		Elemento hidrogenio = new Elemento(EnumSiglaElemento.H);
		Elemento oxigenio = new Elemento(EnumSiglaElemento.O);
		
		elementos = new ArrayList<Elemento>();
		elementos.add(hidrogenio);
		elementos.add(oxigenio);
		
		return elementos;
	}

}
