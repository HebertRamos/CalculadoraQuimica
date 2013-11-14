package ufms.calculadora.extensoes.calculadoraAlgebrica;

/**
 * Exceção lançada quando ocorre uma operação não suportada pela Calculadora Algébrica.
 * 
 * @author Hebert Ramos
 *
 */
public class OperacaoNaoSuportadaException extends Exception {

	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "Operacao Não Suportada Pela Classe " + CalculadoraAlgebrica.class.getCanonicalName();
	}

}
