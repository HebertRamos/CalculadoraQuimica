package ufms.calculadora.excecoes;

/**
 * Exce��o lan�ada quando a solu��o n�o � valida.
 * 
 * @author Hebert Ramos
 */
public class SolucaoInvalidaException extends Exception {

	private static final long serialVersionUID = 1L;

	
	/**
	 * Retorna a mensagem da Exce��o
	 * @return mensagem da exce��o
	 */
	public String getMessage() {
		return "Solu��o n�o � v�lida";
	}

}
