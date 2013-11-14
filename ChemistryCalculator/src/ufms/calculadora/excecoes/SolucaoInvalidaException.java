package ufms.calculadora.excecoes;

/**
 * Exceção lançada quando a solução não é valida.
 * 
 * @author Hebert Ramos
 */
public class SolucaoInvalidaException extends Exception {

	private static final long serialVersionUID = 1L;

	
	/**
	 * Retorna a mensagem da Exceção
	 * @return mensagem da exceção
	 */
	public String getMessage() {
		return "Solução não é válida";
	}

}
