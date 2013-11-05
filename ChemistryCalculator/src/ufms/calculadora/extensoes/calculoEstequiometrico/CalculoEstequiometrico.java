package ufms.calculadora.extensoes.calculoEstequiometrico;

import ufms.calculadora.modelo.Elemento;
import ufms.calculadora.modelo.Solucao;


public class CalculoEstequiometrico {
	
	public static final Double numeroDeAvogrado = 6.02;
	public static final Double volumeCNTP = 22.4;

	
	public static Double converteGrandeza(EnumGrandezaQuimica grandezaAtual, Double valor, EnumGrandezaQuimica grandezaDesejada, Solucao solucao){
		
		//Mant�m o valor em Mols
		if(!grandezaAtual.equals(EnumGrandezaQuimica.MOL)){
			
			if(grandezaAtual.equals(EnumGrandezaQuimica.MASSA_MOLAR)){
				Double massaMolarSolucao = CalculoEstequiometrico.encontraValorDaMassaMolar(solucao);
				valor = valor / massaMolarSolucao;
			}else if(grandezaAtual.equals(EnumGrandezaQuimica.VOLUME)){
				valor = valor / volumeCNTP;
			}else if(grandezaAtual.equals(EnumGrandezaQuimica.NUMERO_MOLECULAS)){
				valor = valor / numeroDeAvogrado;
			}
		}
		
		if(grandezaDesejada.equals(EnumGrandezaQuimica.MASSA_MOLAR)){
			Double massaMolarSolucao = CalculoEstequiometrico.encontraValorDaMassaMolar(solucao);
			valor = valor * massaMolarSolucao;
		}if(grandezaDesejada.equals(EnumGrandezaQuimica.VOLUME)){
			valor = valor * volumeCNTP;
		}else if(grandezaDesejada.equals(EnumGrandezaQuimica.NUMERO_MOLECULAS)){
			valor = valor * numeroDeAvogrado;
		}
		
		//Arredonda 5 casas decimais
		return Math.round(valor*100000.0)/100000.0;
	}
	
	
	public static Double encontraValorDaMassaMolar(Solucao solucao) {
		Double valorResultado = 0.0;
		
		for (Elemento elemento : solucao.getElementos()) {
			valorResultado += elemento.getCoeficiente() * elemento.getMassaAtomica();
		}
		return valorResultado;
	}

}
