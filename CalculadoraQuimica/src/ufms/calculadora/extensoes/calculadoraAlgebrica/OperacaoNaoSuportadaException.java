package ufms.calculadora.extensoes.calculadoraAlgebrica;

public class OperacaoNaoSuportadaException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public String getMessage(){
		return "Operacao N�o Suportada Pela Classe "+CalculadoraAlgebrica.class.getCanonicalName();
	}

}
