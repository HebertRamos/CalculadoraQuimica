package ufms.calculadora.extensoes.calculadoraAlgebrica;

import java.util.HashMap;
import java.util.Set;

/**
 * Enum com as operações realizadas pela calculadora, os símbolos das
 * respectivas Operações são obtidos pelo método estático getSimboloOperacao;
 * @author hramos
 */
public enum EnumOperacaoesCalculadoraAlgebrica {

	SOMA, SUBTRACAO, IGUALDADE;

	public static String getSimboloOperacao(EnumOperacaoesCalculadoraAlgebrica operacao) {
		return getSimbolosOperacao().get(operacao);
	}

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

	public static HashMap<EnumOperacaoesCalculadoraAlgebrica, String> getSimbolosOperacao() {
		HashMap<EnumOperacaoesCalculadoraAlgebrica, String> mapaSimbolos = new HashMap<EnumOperacaoesCalculadoraAlgebrica, String>();
		mapaSimbolos.put(SOMA, "+");
		mapaSimbolos.put(SUBTRACAO, "-");
		mapaSimbolos.put(IGUALDADE, "=");
		return mapaSimbolos;
	}

	public static String getSimboloInversoOperacao(String simboloString) {

		EnumOperacaoesCalculadoraAlgebrica simbolo = getSimboloOperacaoPeloString(simboloString);
		if (simbolo != null) {
			return getSimboloInversoOperacao(simbolo);
		}

		return null;
	}

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
