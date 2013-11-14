package ufms.calculadora.extensoes.calculadoraAlgebrica;

/**
 * Classe pai para as opera��es matem�ticas
 * 
 * @author HED
 *
 */
public class OperacaoMatematica {

	/**
	 * Executa uma opera��o entre dois Inteiros
	 * 
	 * @param alg1
	 * @param alg2
	 * @return
	 */
	public String executaOperacao(Integer alg1, Integer alg2) {
		return "";
	}

	/**
	 * Executa uma opera��o entre duas Variaveis
	 * 
	 * @param alg1
	 * @param alg2
	 * @return
	 * @throws OperacaoNaoSuportadaException
	 */
	public String executaOperacao(String alg1, String alg2) throws OperacaoNaoSuportadaException {
		return "";
	}

	/**
	 * Verifica se a entrada cont�m somente n�meros.
	 * 
	 * @param entrada
	 * @return boolean
	 */
	public static boolean somenteNumero(String entrada) {

		try {
			Integer.parseInt(entrada);
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}

	/**
	 * Varifica se a entrada contem somente letras.
	 * 
	 * @param entrada
	 * @return boolean
	 */
	public static boolean contemLetra(String entrada) {

		return !retiraLetra(entrada).equals(entrada);
	}

	/**
	 * Retiras as letras da entrada.
	 * 
	 * @param entrada
	 * @return String
	 */
	public static String retiraLetra(String entrada) {
		String regexLetras = "[aA-zZ]";
		return entrada.replaceAll(regexLetras, "");
	}

	/**
	 * Retiras os n�meros da entrada.
	 * 
	 * @param entrada
	 * @return String
	 */
	public static String retiraNumero(String entrada) {
		String regexNumero = "\\d";
		return entrada.replaceAll(regexNumero, "");
	}
}
