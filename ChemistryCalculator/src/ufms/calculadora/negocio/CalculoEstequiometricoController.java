package ufms.calculadora.negocio;

import ufms.calculadora.extensoes.calculoEstequiometrico.CalculoEstequiometrico;
import ufms.calculadora.extensoes.calculoEstequiometrico.EnumGrandezaQuimica;
import ufms.calculadora.modelo.Solucao;

public class CalculoEstequiometricoController implements Controller {

	private Solucao solucao;
	private Double valorAtual;
	private EnumGrandezaQuimica grandezaAtual;
	
	public void setSolucao(Solucao solucao){
		this.solucao = solucao;
	}
	
	public Double converteParaMassaMolar(){
			
			if(!dadosIniciadosValidos()){
				valorAtual = CalculoEstequiometrico.encontraValorDaMassaMolar(solucao);
			}else{
				valorAtual = CalculoEstequiometrico.converteGrandeza(grandezaAtual, valorAtual, EnumGrandezaQuimica.MASSA_MOLAR, solucao);
			}
			grandezaAtual = EnumGrandezaQuimica.MASSA_MOLAR;
			return valorAtual;
	}
	
	public void setValorAtua(Double valor){
		valorAtual = valor;
	}
	
	public Double converteParaNumeroMols(){
		return converteParaGrandeza(EnumGrandezaQuimica.MOL);
	}
	
	public Double converteParaVolume(){
		return converteParaGrandeza(EnumGrandezaQuimica.VOLUME);
	}
	
	public Double converteParaNumeroMoleculas(){
		return converteParaGrandeza(EnumGrandezaQuimica.NUMERO_MOLECULAS);
	}
	
	private Double converteParaGrandeza(EnumGrandezaQuimica grandezaDesejada){
		
		if(!dadosIniciadosValidos()){
			converteParaMassaMolar();
		}
		
		valorAtual = CalculoEstequiometrico.converteGrandeza(grandezaAtual, valorAtual, grandezaDesejada, solucao);
		grandezaAtual = grandezaDesejada;
		
		return valorAtual;
	}
	
	private boolean dadosIniciadosValidos(){
		if(solucao == null){
			//Lança Exceção
		}
		return valorAtual != null && grandezaAtual != null;  
	}
	
	
}
