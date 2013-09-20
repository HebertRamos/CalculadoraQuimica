package ufms.calculadora.extensoes.calculadoraAlgebrica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

/**
 * Classe que realiza de calculos em cima de uma Expressão Algébrica.
 * 
 * @author hramos
 * 
 */
public class CalculadoraAlgebrica {

	/**
	 * Isola uma variável de uma expressão algébrica.
	 * 
	 * Passos: 1 - Caso as ocorrencias da variavel estejam somente do lado
	 * direito da expressão os lados são invertido, para manter as ocorrencias
	 * do lado esquerdo. 2 - Mantem do lado esquerdo somente as ocorrencias de
	 * variável. 3 - Caso haja várias ocorrencias da variável é realizada as
	 * opearções para que reste apenas uma. 4 - Executa as operações do lado
	 * direito afim de sobrar apenas um número. 5 - Se sobrou um operador do em
	 * uma posição a esquerda da variavel o mesmo é retirado. 6 - Passa divindo
	 * o valor companheiro da variavel.
	 * 
	 * @param expressaoAlgebrica
	 *            Expressão Algébrica.
	 * @param identificadorVariavel
	 *            Indentificador da Variável.
	 * @return Expressão algébrica com a variável já isolada.
	 * @throws OperacaoNaoSuportadaException
	 */
	public ExpressaoAlgebrica isolaVariavel(
			ExpressaoAlgebrica expressaoAlgebrica, String identificadorVariavel) {

		this.mantemOcorrenciasVariavelDoLadoEsquerdo(expressaoAlgebrica,
				identificadorVariavel);

		if (expressaoAlgebrica.getLadoEsquerdo().size() > 1) {

		}

		List<String> ladoComOperacoesExecutadas = executaOperacoesLado(expressaoAlgebrica.getLadoDireito());
		expressaoAlgebrica.setLadoDireito(ladoComOperacoesExecutadas);

		if (EnumOperacaoesCalculadoraAlgebrica
				.getSimboloOperacaoPeloString(expressaoAlgebrica
						.getLadoEsquerdo().get(0)) != null) {
			expressaoAlgebrica.getLadoEsquerdo().remove(0);
		}

		Integer valorResultadoLadoDireito = null;
		if (expressaoAlgebrica.getLadoDireito().size() == 1 && !contemLetra(expressaoAlgebrica.getLadoDireito().get(0))) {
			valorResultadoLadoDireito = Integer.valueOf(expressaoAlgebrica
					.getLadoDireito().get(0));
		}
		
		
		Integer valorCompanheiro = Integer.valueOf(expressaoAlgebrica
				.getLadoEsquerdo().get(0).replace(identificadorVariavel, ""));

		if (valorCompanheiro != 1 && valorResultadoLadoDireito != null) {
			Integer valorResultante = valorResultadoLadoDireito
					/ valorCompanheiro;
			expressaoAlgebrica.getLadoDireito().set(0,
					String.valueOf(valorResultante));
		}

		return expressaoAlgebrica;
	}

	public ExpressaoAlgebrica isolaVariavel(
			ExpressaoAlgebrica expressaoAlgebrica1,
			ExpressaoAlgebrica expressaoAlgebrica2, String identificadorVariavel) {

		Variavel var = new Variavel(identificadorVariavel);

		if (expressaoAlgebrica1.getListaVariavel().contains(var)
				&& expressaoAlgebrica2.getListaVariavel().contains(var)) {

			if (expressaoAlgebrica1.getListaVariavel().size() == 2) {

				Variavel outraVariavel = expressaoAlgebrica1.getListaVariavel()
						.get(1 - expressaoAlgebrica1.getListaVariavel()
								.indexOf(var));

				if (expressaoAlgebrica2.getListaVariavel().size() == 2
						&& expressaoAlgebrica2.getListaVariavel().contains(
								outraVariavel)) {

					expressaoAlgebrica1 = this.isolaVariavel(
							expressaoAlgebrica1, outraVariavel.getId());
					expressaoAlgebrica1.toString();

				}
			}

		}

		return null;
	}

	public ExpressaoAlgebrica mantemOcorrenciasVariavelDoLadoEsquerdo(
			ExpressaoAlgebrica expressaoAlgebrica, String identificadorVariavel) {

		if (expressaoAlgebrica.ladoContemVariavel(
				expressaoAlgebrica.getLadoDireito(), identificadorVariavel)
				&& !expressaoAlgebrica.ladoContemVariavel(
						expressaoAlgebrica.getLadoEsquerdo(),
						identificadorVariavel)) {
			this.inverteLado(expressaoAlgebrica);
		}

		for (int i = 0; i < expressaoAlgebrica.getLadoEsquerdo().size(); i++) {

			// Que não seja um algarismo com a variavel e não seja uma operacao
			if (!EnumOperacaoesCalculadoraAlgebrica.getSimbolosOperacao()
					.containsValue(expressaoAlgebrica.getLadoEsquerdo().get(i))
					&& !expressaoAlgebrica.getLadoEsquerdo().get(i)
							.contains(identificadorVariavel)) {
				String operador = (i == 0 ? null : expressaoAlgebrica
						.getLadoEsquerdo().get(i - 1));
				String algarismo = expressaoAlgebrica.getLadoEsquerdo().get(i);

				this.trocaAlgarismoDeLado(operador, algarismo,
						expressaoAlgebrica.getLadoEsquerdo(),
						expressaoAlgebrica.getLadoDireito());
			}
		}

		for (int i = 0; i < expressaoAlgebrica.getLadoDireito().size(); i++) {

			// Que não seja um algarismo com a variavel e não seja uma operacao
			if (!EnumOperacaoesCalculadoraAlgebrica.getSimbolosOperacao()
					.containsValue(expressaoAlgebrica.getLadoDireito().get(i))
					&& expressaoAlgebrica.getLadoDireito().get(i)
							.contains(identificadorVariavel)) {
				String operador = (i == 0 ? null : expressaoAlgebrica
						.getLadoDireito().get(i - 1));
				String algarismo = expressaoAlgebrica.getLadoDireito().get(i);

				this.trocaAlgarismoDeLado(operador, algarismo,
						expressaoAlgebrica.getLadoDireito(),
						expressaoAlgebrica.getLadoEsquerdo());
			}
		}

		return expressaoAlgebrica;
	}

	private void inverteLado(ExpressaoAlgebrica expressaoAlgebrica) {

		List<String> temp = expressaoAlgebrica.getLadoEsquerdo();
		expressaoAlgebrica.setLadoEsquerdo(expressaoAlgebrica.getLadoDireito());
		expressaoAlgebrica.setLadoDireito(temp);
	}

	/**
	 * Faz um parse da Expressão Algebrica para uma variável se existe uma
	 * igualdade da variavel. Ex: A=10.
	 * 
	 * @param expressaoAlgebrica
	 * @return Variavel
	 */
	public Variavel parsearParaVariavel(ExpressaoAlgebrica expressaoAlgebrica) {

		expressaoAlgebrica.getLadoEsquerdo().set(0,
				expressaoAlgebrica.getLadoEsquerdo().get(0).replace("1", ""));

		if (expressaoAlgebrica.getLadoEsquerdo().size() == 1
				&& expressaoAlgebrica.getLadoDireito().size() == 1
				&& !expressaoAlgebrica.getLadoEsquerdo().get(0)
						.matches(".*\\d.*")) {
			Variavel var = new Variavel();
			var.setId(expressaoAlgebrica.getLadoEsquerdo().get(0));
			var.setValor(Integer.valueOf(expressaoAlgebrica.getLadoDireito()
					.get(0)));
			return var;
		}

		return null;

	}

	/**
	 * Executa as operações suportadas pela calculadora em um lado da Equação
	 * Algebrica recursivamente até que o lado tenha apenas um valor.
	 * 
	 * @param lado
	 * @return
	 */
	public List<String> executaOperacoesLado(List<String> lado) {

		lado = this.agrupaVariaveisLado(lado);
		return this.executaOperacoesLadoRecursivo(lado);

	}

	public List<String> executaOperacoesLadoRecursivo(List<String> lado) {
		if (lado.size() >= 3) {

			String resultado;
			try {
				resultado = executaOperacao(lado.get(0), lado.get(1),
						lado.get(2));

			} catch (OperacaoNaoSuportadaException e) {
				return lado;
			}

			List<String> novaLado = new ArrayList<String>();
			novaLado.add(resultado);
			for (int i = 3; i < lado.size(); i++) {
				novaLado.add(lado.get(i));
			}

			return executaOperacoesLadoRecursivo(novaLado);

		}

		return lado;
	}

	/**
	 * Método principal da classe, executa uma operação dado dois algarismos e
	 * um operador.
	 * 
	 * Posui ainda algumas regras que limitam a possibilidades de execução, são
	 * elas:
	 * 
	 * 1 - Os operadores tem de ser números. 2 - Suporta os operadores SOMA E
	 * SUBTRAÇÃO 3 - alg1 > alg2
	 * 
	 * 
	 * @param alg1
	 * @param operador
	 * @param alg2
	 * @return
	 * @throws OperacaoNaoSuportadaException
	 */
	public String executaOperacao(String alg1, String operador, String alg2)
			throws OperacaoNaoSuportadaException {
		String saida = "";
		Integer algarismo1;
		Integer algarismo2;

		if (somenteNumero(alg1) && somenteNumero(alg2)) {

			algarismo1 = Integer.valueOf(alg1);
			algarismo2 = Integer.valueOf(alg2);

			if (EnumOperacaoesCalculadoraAlgebrica
					.getSimboloOperacaoPeloString(operador).equals(
							EnumOperacaoesCalculadoraAlgebrica.SOMA)) {
				saida = String.valueOf(algarismo1 + algarismo2);
			} else if (EnumOperacaoesCalculadoraAlgebrica
					.getSimboloOperacaoPeloString(operador).equals(
							EnumOperacaoesCalculadoraAlgebrica.SUBTRACAO)) {
				if (algarismo1 >= algarismo2) {
					saida = String.valueOf(algarismo1 - algarismo2);
				} else {
					saida = String.valueOf(algarismo2 - algarismo1);
				}
			}

		} else if (contemLetra(alg1) && contemLetra(alg2)) {

			String identificadorVariavel1 = retiraNumero(alg1);
			String identificadorVariavel2 = retiraNumero(alg2);

			if (identificadorVariavel1.equals(identificadorVariavel2)) {

				algarismo1 = Integer.valueOf(alg1.replace(
						identificadorVariavel1, ""));
				algarismo2 = Integer.valueOf(alg2.replace(
						identificadorVariavel1, ""));

				if (EnumOperacaoesCalculadoraAlgebrica
						.getSimboloOperacaoPeloString(operador).equals(
								EnumOperacaoesCalculadoraAlgebrica.SOMA)) {
					saida = String.valueOf(algarismo1 + algarismo2)
							+ identificadorVariavel1;
				} else if (EnumOperacaoesCalculadoraAlgebrica
						.getSimboloOperacaoPeloString(operador).equals(
								EnumOperacaoesCalculadoraAlgebrica.SUBTRACAO)) {
					if (algarismo1 >= algarismo2) {
						saida = String.valueOf(algarismo1 - algarismo2)
								+ identificadorVariavel1;
					} else {
						saida = String.valueOf(algarismo2 - algarismo1);
					}
				}

			} else {
				throw new OperacaoNaoSuportadaException();
			}
		} else {
			throw new OperacaoNaoSuportadaException();
		}

		return saida;
	}

	public void trocaAlgarismoDeLado(String operador, String algarismo,
			List<String> ladoOrigem, List<String> ladoDestino) {
		if (operador != null) {
			ladoOrigem.remove(ladoOrigem.indexOf(operador));
			operador = EnumOperacaoesCalculadoraAlgebrica
					.getSimboloInversoOperacao(operador);
		} else {
			operador = EnumOperacaoesCalculadoraAlgebrica
					.getSimboloInversoOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA);
		}

		ladoOrigem.remove(ladoOrigem.indexOf(algarismo));

		ladoDestino.add(operador);
		ladoDestino.add(algarismo);
	}

	public ExpressaoAlgebrica aplicaCondicao(
			ExpressaoAlgebrica expressaoAlgebrica, Variavel condicao) {

		if (condicao != null) {
			aplicaCondicao(expressaoAlgebrica.getLadoEsquerdo(), condicao);
			aplicaCondicao(expressaoAlgebrica.getLadoDireito(), condicao);
		}
		return expressaoAlgebrica;
	}

	private void aplicaCondicao(List<String> lado, Variavel condicaoVariavel) {
		int posicao = 0;
		for (String algarismo : lado) {
			if (algarismo.contains(condicaoVariavel.getId())) {
				String algarismoString = algarismo.replace(
						condicaoVariavel.getId(), "");
				Integer valorCompanheiro = Integer.valueOf(algarismoString);
				valorCompanheiro *= condicaoVariavel.getValor();

				lado.set(posicao, String.valueOf(valorCompanheiro));
			}
			posicao++;
		}
	}

	public List<String> agrupaVariaveisLado(List<String> lado) {

		HashMap<String, List<String>> mapaLado = new HashMap<String, List<String>>();

		List<String> ladoAgrupado = new ArrayList<String>();

		String chaveParaNumeros = "ZZZNUMEROS";
		String operador;

		int indice = 0;
		for (String algarismo : lado) {
			if (EnumOperacaoesCalculadoraAlgebrica
					.getSimboloOperacaoPeloString(algarismo) == null) {
				if (somenteNumero(algarismo)) {
					if (!mapaLado.containsKey(chaveParaNumeros)) {
						mapaLado.put(chaveParaNumeros, new ArrayList<String>());
					}

					if (indice == 0) {
						operador = EnumOperacaoesCalculadoraAlgebrica
								.getSimboloOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA);
					} else {
						operador = lado.get(indice - 1);
					}

					mapaLado.get(chaveParaNumeros).add(operador);
					mapaLado.get(chaveParaNumeros).add(algarismo);

				} else if (contemLetra(algarismo)) {
					String identificadorVariavel = retiraNumero(algarismo);

					if (!mapaLado.containsKey(identificadorVariavel)) {
						mapaLado.put(identificadorVariavel,
								new ArrayList<String>());
					}

					if (indice == 0) {
						operador = EnumOperacaoesCalculadoraAlgebrica
								.getSimboloOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA);
					} else {
						operador = lado.get(indice - 1);
					}

					mapaLado.get(identificadorVariavel).add(operador);
					mapaLado.get(identificadorVariavel).add(algarismo);

				}
			}

			indice++;
		}

		TreeSet<String> arvore = new TreeSet<String>(mapaLado.keySet());
		Iterator<String> iterator = arvore.iterator();

		indice = 0;
		while (iterator.hasNext()) {
			String chave = iterator.next();
			
			ladoAgrupado.addAll(mapaLado.get(chave));

			indice++;
		}

		return ladoAgrupado;
	}

	public static boolean somenteNumero(String entrada) {
		
		try{
			Integer.parseInt( entrada );
		}
		catch (NumberFormatException e) {
			return false;
		}
		
		return true;
	}

	public static boolean contemLetra(String entrada) {

		return entrada.matches(".*\\w.*");
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
