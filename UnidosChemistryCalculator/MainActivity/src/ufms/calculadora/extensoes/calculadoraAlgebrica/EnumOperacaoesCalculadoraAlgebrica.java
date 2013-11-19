package ufms.calculadora.extensoes.calculadoraAlgebrica;

import java.util.HashMap;
import java.util.Set;

/**
 * Enum com as opera��es realizadas pela calculadora, os s�mbolos das
 * respectivas Opera��es s�o obtidos pelo m�todo est�tico getSimboloOperacao;
 * 
 * @author Hebert Ramos 
 */
public enum EnumOperacaoesCalculadoraAlgebrica {

	SOMA, SUBTRACAO, IGUALDADE;

	/**
	 * Procura opera��o pelo s�mbolo 
	 * @param EnumOperacaoesCalculadoraAlgebrica
	 * @return
	 */
	public static String getSimboloOperacao(EnumOperacaoesCalculadoraAlgebrica operacao) {
		return getSimbolosOperacao().get(operacao);
	}

	/**
	 * Procura opera��o pelo s�mbolo 
	 * @param EnumOperacaoesCalculadoraAlgebrica
	 * @return
	 */
	public static EnumOperacaoesCalculadoraAlgebrica getSimboloOperacaoPeloString(String simboloString) {
		HashMap<EnumOperacaoesCalculadoraAlgebrica, String> mapaSimbolos = getSimbolosOperacao();
		if (mapaSimbolos.containsValue(simboloString)) {
			Set<EnumOperacaoesCalculadoraAlgebrica> simbolos = mapaSimbolos.keySet();
			for (EnumOperacaoesCalculadoraAlgebrica simbolo : simbolos) {
				if (mapaSimbolos.get(simbolo).equals(simboloString)) {
					return simbolo;
				}
			}
		}
		return null;
	}

	/**
	 * Retorna Map das opera��es.
	 * @return
	 */
	public static HashMap<EnumOperacaoesCalculadoraAlgebrica, String> getSimbolosOperacao() {
		HashMap<EnumOperacaoesCalculadoraAlgebrica, String> mapaSimbolos = new HashMap<EnumOperacaoesCalculadoraAlgebrica, String>();
		mapaSimbolos.put(SOMA, "+");
		mapaSimbolos.put(SUBTRACAO, "-");
		mapaSimbolos.put(IGUALDADE, "=");
		return mapaSimbolos;
	}

	/**
	 * Retorna o simbolo inverso da opera��o alg�brica
	 * @param String
	 * @return
	 */
	public static String getSimboloInversoOperacao(String simboloString) {

		EnumOperacaoesCalculadoraAlgebrica simbolo = getSimboloOperacaoPeloString(simboloString);
		if (simbolo != null) {
			return getSimboloInversoOperacao(simbolo);
		}

		return null;
	}

	/**
	 * Retorna o simbolo inverso da opera��o alg�brica
	 * @param EnumOperacaoesCalculadoraAlgebrica
	 * @return
	 */
	public static String getSimboloInversoOperacao(EnumOperacaoesCalculadoraAlgebrica operacao) {
		if (operacao.equals(SOMA)) {
			return getSimboloOperacao(SUBTRACAO);
		} else if (operacao.equals(SUBTRACAO)) {
			return getSimboloOperacao(SOMA);
		} else {
			return null;
		}
	}
}
