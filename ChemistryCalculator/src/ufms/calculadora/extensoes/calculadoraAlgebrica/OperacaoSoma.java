package ufms.calculadora.extensoes.calculadoraAlgebrica;

/**
 * Classe que faz operaçõa de soma.
 * 
 * @author Hebert Ramos
 *
 */
public class OperacaoSoma extends OperacaoMatematica {

	/**
	 * Executa a operação de soma entre dois inteiros.
	 * @return String
	 */
	@Override
	public String executaOperacao(Integer alg1, Integer alg2) {
		return String.valueOf(alg1 + alg2);
	}

	/**
	 * Executa a operação de soma entre duas Variaveis.
	 * @return String
	 */
	@Override
	public String executaOperacao(String alg1, String alg2) throws OperacaoNaoSuportadaException {

		String identificadorVariavel1 = retiraNumero(alg1);
		String identificadorVariavel2 = retiraNumero(alg2);

		if (identificadorVariavel1.equals(identificadorVariavel2)) {

			Integer algarismo1 = Integer.valueOf(alg1.replace(identificadorVariavel1, ""));
			Integer algarismo2 = Integer.valueOf(alg2.replace(identificadorVariavel1, ""));

			return String.valueOf(algarismo1 + algarismo2) + identificadorVariavel1;
		} else {
			throw new OperacaoNaoSuportadaException();
		}
	}
}
