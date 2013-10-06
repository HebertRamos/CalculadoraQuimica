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
		Elemento ferro = new Elemento(EnumSiglaElemento.Fe);
		Elemento carbono = new Elemento(EnumSiglaElemento.C);
		Elemento cloro = new Elemento(EnumSiglaElemento.Cl);
		Elemento manganes = new Elemento(EnumSiglaElemento.Mn);
		Elemento potasio = new Elemento(EnumSiglaElemento.K);
		Elemento nitrogenio = new Elemento(EnumSiglaElemento.N);	
		Elemento cobre = new Elemento(EnumSiglaElemento.Cu);
		Elemento cromo = new Elemento(EnumSiglaElemento.Cr);
		Elemento iodo = new Elemento(EnumSiglaElemento.I);
		Elemento enxofre = new Elemento(EnumSiglaElemento.S);
		Elemento sodio = new Elemento(EnumSiglaElemento.Na);
		
		elementos = new ArrayList<Elemento>();
		
		elementos.add(hidrogenio);
		elementos.add(oxigenio);
		elementos.add(ferro);
		elementos.add(carbono);
		elementos.add(cloro);
		elementos.add(manganes);
		elementos.add(potasio);
		elementos.add(nitrogenio);
		elementos.add(cobre);
		elementos.add(cromo);
		elementos.add(iodo);
		elementos.add(enxofre);
		elementos.add(sodio);
		elementos.add(cloro);
		
		
		return elementos;
	}

}
