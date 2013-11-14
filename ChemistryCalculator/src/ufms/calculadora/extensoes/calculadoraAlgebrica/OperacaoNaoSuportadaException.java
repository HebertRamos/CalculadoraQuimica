package ufms.calculadora.extensoes.calculadoraAlgebrica;

/**
 * Exce��o lan�ada quando ocorre uma opera��o n�o suportada pela Calculadora Alg�brica.
 * 
 * @author Hebert Ramos
 *
 */
public class OperacaoNaoSuportadaException extends Exception {

	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "Operacao N�o Suportada Pela Classe " + CalculadoraAlgebrica.class.getCanonicalName();
	}

}
