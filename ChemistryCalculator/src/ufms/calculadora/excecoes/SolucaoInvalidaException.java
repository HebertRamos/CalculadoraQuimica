package ufms.calculadora.excecoes;


public class SolucaoInvalidaException extends Exception {

	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "Solu��o n�o � v�lida";
	}

}
