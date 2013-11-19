package ufms.calculadora.extensoes.calculadoraAlgebrica;

public class OperacaoMatematica {

	/**
	 * Dois Inteiros
	 * @param alg1
	 * @param alg2
	 * @return
	 */
	public String executaOperacao(Integer alg1, Integer alg2) {
		return "";
	}

	/**
	 * Duas Variaveis
	 * @param alg1
	 * @param alg2
	 * @return
	 * @throws OperacaoNaoSuportadaException
	 */
	public String executaOperacao(String alg1, String alg2) throws OperacaoNaoSuportadaException {
		return "";
	}

	public static boolean somenteNumero(String entrada) {

		try {
			Integer.parseInt(entrada);
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}

	public static boolean contemLetra(String entrada) {

		return !retiraLetra(entrada).equals(entrada);
	}

	public static String retiraLetra(String entrada) {
		String regexLetras = "[aA-zZ]";
		return entrada.replaceAll(regexLetras, "");
	}

	public static String retiraNumero(String entrada) {
		String regexNumero = "\\d";
		return entrada.replaceAll(regexNumero, "");
	}
}
