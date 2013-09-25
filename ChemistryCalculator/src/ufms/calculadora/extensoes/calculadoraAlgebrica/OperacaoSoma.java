package ufms.calculadora.extensoes.calculadoraAlgebrica;

public class OperacaoSoma extends OperacaoMatematica {

	@Override
	public String executaOperacao(Integer alg1, Integer alg2) {
		return String.valueOf(alg1 + alg2);
	}

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
