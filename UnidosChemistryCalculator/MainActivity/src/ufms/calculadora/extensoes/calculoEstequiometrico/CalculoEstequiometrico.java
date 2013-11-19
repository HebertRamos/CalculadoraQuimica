package ufms.calculadora.extensoes.calculoEstequiometrico;

import ufms.calculadora.modelo.Elemento;
import ufms.calculadora.modelo.Solucao;

/**
 * Classe que efetua operações afim de calcular estequiometria da Equação Química. 
 * 
 * @author Hebert Ramos.
 *
 */
public class CalculoEstequiometrico {
	
	public static final Double numeroDeAvogrado = 6.02;
	public static final Double volumeCNTP = 22.4;

	/**
	 * Método que converte grandezas químicas.
	 *  
	 * @param grandezaAtual
	 * @param valor
	 * @param grandezaDesejada
	 * @param solucao
	 * @return
	 */
	public static Double converteGrandeza(EnumGrandezaQuimica grandezaAtual, Double valor, EnumGrandezaQuimica grandezaDesejada, Solucao solucao){
		
		//Mantém o valor em Mols
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
	
	/**
	 * Encontra o valor da massa molar de uma solução, somando a massa molar de cada elmento multiplicado pelo seu coeficiente.
	 * @param solucao
	 * @return
	 */
	public static Double encontraValorDaMassaMolar(Solucao solucao) {
		Double valorResultado = 0.0;
		
		for (Elemento elemento : solucao.getElementos()) {
			valorResultado += elemento.getCoeficiente() * elemento.getMassaAtomica();
		}
		return valorResultado;
	}

}
