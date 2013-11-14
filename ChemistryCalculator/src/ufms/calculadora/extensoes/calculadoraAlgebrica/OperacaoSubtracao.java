package ufms.calculadora.extensoes.calculadoraAlgebrica;

/**
 * Classe que faz operaçõa de subtração.
 * 
 * @author Hebert Ramos
 *
 */
public class OperacaoSubtracao extends OperacaoMatematica {

	/**
	 * Executa a operação de subtração entre dois inteiros.
	 * @return String
	 */
	@Override
	public String executaOperacao(Integer alg1, Integer alg2) {

		if (alg1 < alg2) {
			Integer temp = alg1;
			alg1 = alg2;
			alg2 = temp;
		}
		return String.valueOf(alg1 - alg2);
	}

	/**
	 * Executa a operação de subtração entre duas Variaveis.
	 * @return String
	 */
	@Override
	public String executaOperacao(String alg1, String alg2) throws OperacaoNaoSuportadaException {

		String identificadorVariavel1 = retiraNumero(alg1);
		String identificadorVariavel2 = retiraNumero(alg2);

		if (identificadorVariavel1.equals(identificadorVariavel2)) {

			Integer algarismo1 = Integer.valueOf(alg1.replace(identificadorVariavel1, ""));
			Integer algarismo2 = Integer.valueOf(alg2.replace(identificadorVariavel1, ""));

			if (algarismo1 < algarismo2) {
				Integer temp = algarismo1;
				algarismo1 = algarismo2;
				algarismo2 = temp;
			}

			return String.valueOf(algarismo1 - algarismo2) + identificadorVariavel1;
		} else {
			throw new OperacaoNaoSuportadaException();
		}
	}
}
