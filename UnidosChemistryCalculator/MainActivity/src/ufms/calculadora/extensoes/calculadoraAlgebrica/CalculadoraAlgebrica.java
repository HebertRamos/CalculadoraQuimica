package ufms.calculadora.extensoes.calculadoraAlgebrica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

/**
 * Classe que realiza de calculos em cima de uma Expressão Algébrica.
 * 
 * @author Hebert Ramos
 */
public class CalculadoraAlgebrica {

	/**
	 * Isola uma variável de uma expressão algébrica. 
	 * Passos: <BR/>
	 * 1 - Caso as ocorrencias da variavel estejam somente do lado direito da expressão os lados são invertidos, para manter as ocorrencias do lado esquerdo.<BR/>
	 * 2 - Mantem do lado esquerdo somente as ocorrencias de variável. <BR/>
	 * 3 - Caso haja várias ocorrencias da variável é realizada as opearções para que reste apenas uma.<BR/> 
	 * 4 - Executa as operações do lado direito afim de sobrar apenas um número. <BR/>
	 * 5 - Se sobrou um operador do em uma posição a esquerda da variavel o mesmo é retirado.<BR/> 
	 * 6 - Passa divindo o valor companheiro da variavel.<BR/>
	 * 
	 * @param expressaoAlgebrica Expressão Algébrica.
	 * @param identificadorVariavel Indentificador da Variável.
	 * @return Expressão algébrica com a variável já isolada.
	 * @throws OperacaoNaoSuportadaException
	 */
	public ExpressaoAlgebrica isolaVariavel(ExpressaoAlgebrica expressaoAlgebrica, String identificadorVariavel) {

		this.mantemOcorrenciasVariavelDoLadoEsquerdo(expressaoAlgebrica, identificadorVariavel);

		List<String> ladoComOperacoesExecutadas;
		if (expressaoAlgebrica.getLadoEsquerdo().size() > 2) {
			ladoComOperacoesExecutadas = executaOperacoesLado(expressaoAlgebrica.getLadoEsquerdo());
			expressaoAlgebrica.setLadoEsquerdo(ladoComOperacoesExecutadas);
		}

		if (expressaoAlgebrica.getLadoDireito().size() > 2) {
			ladoComOperacoesExecutadas = executaOperacoesLado(expressaoAlgebrica.getLadoDireito());
			expressaoAlgebrica.setLadoDireito(ladoComOperacoesExecutadas);
		}

		Integer valorCompanheiro = Integer.valueOf(expressaoAlgebrica.getLadoEsquerdo().get(1).replace(identificadorVariavel, ""));
		if (expressaoAlgebrica.getLadoDireito().size() == 2 && !OperacaoMatematica.contemLetra(expressaoAlgebrica.getLadoDireito().get(1)) && valorCompanheiro != 1) {

			Integer valorResultante = Integer.valueOf(expressaoAlgebrica.getLadoDireito().get(1)) / valorCompanheiro;
			expressaoAlgebrica.getLadoDireito().set(1, String.valueOf(valorResultante));

			expressaoAlgebrica.getLadoEsquerdo().set(1, "1" + identificadorVariavel);
		}

		return expressaoAlgebrica;
	}

	/**
	 * Isola uma variável de um dado duas expressões Algébricas Ex:
	 * 
	 *  A + B = 2 
	 *  B + A + 2 = 4 
	 *   
	 * @param expressaoAlgebrica1
	 * @param expressaoAlgebrica2
	 * @param identificadorVariavel
	 * @return ExpressaoAlgebrica
	 */
	public ExpressaoAlgebrica isolaVariavel(ExpressaoAlgebrica expressaoAlgebrica1, ExpressaoAlgebrica expressaoAlgebrica2, String identificadorVariavel) {

		Variavel var = new Variavel(identificadorVariavel);

		if (expressaoAlgebrica1.getListaVariavel().contains(var) && expressaoAlgebrica2.getListaVariavel().contains(var)) {

			if (expressaoAlgebrica1.getListaVariavel().size() == 2) {

				Variavel outraVariavel = expressaoAlgebrica1.getListaVariavel().get(1 - expressaoAlgebrica1.getListaVariavel().indexOf(var));

				if (expressaoAlgebrica2.getListaVariavel().size() == 2 && expressaoAlgebrica2.getListaVariavel().contains(outraVariavel)) {

					expressaoAlgebrica1 = this.isolaVariavel(expressaoAlgebrica1, outraVariavel.getId());

					expressaoAlgebrica2 = this.aplicaCondicao(expressaoAlgebrica2, outraVariavel, expressaoAlgebrica1);

					expressaoAlgebrica2 = this.isolaVariavel(expressaoAlgebrica2, identificadorVariavel);
				}
			}

		}

		return expressaoAlgebrica2;
	}
	
	/**
	 * Mantém a ocorrência de uma variável do lado esquerdo de uma expressão algébrica.
	 * @param expressaoAlgebrica
	 * @param identificadorVariavel
	 * @return ExpressaoAlgebrica
	 */
	public ExpressaoAlgebrica mantemOcorrenciasVariavelDoLadoEsquerdo(ExpressaoAlgebrica expressaoAlgebrica, String identificadorVariavel) {

		if (expressaoAlgebrica.ladoContemVariavel(expressaoAlgebrica.getLadoDireito(), identificadorVariavel) && !expressaoAlgebrica.ladoContemVariavel(expressaoAlgebrica.getLadoEsquerdo(), identificadorVariavel)) {
			this.inverteLado(expressaoAlgebrica);
		}

		for (int i = 0; i < expressaoAlgebrica.getLadoEsquerdo().size(); i++) {

			// Que não seja um algarismo com a variavel e não seja uma operacao
			if (!EnumOperacaoesCalculadoraAlgebrica.getSimbolosOperacao().containsValue(expressaoAlgebrica.getLadoEsquerdo().get(i)) && !expressaoAlgebrica.getLadoEsquerdo().get(i).contains(identificadorVariavel)) {
				String operador = (i == 0 ? null : expressaoAlgebrica.getLadoEsquerdo().get(i - 1));
				String algarismo = expressaoAlgebrica.getLadoEsquerdo().get(i);

				this.trocaAlgarismoDeLado(operador, algarismo, expressaoAlgebrica.getLadoEsquerdo(), expressaoAlgebrica.getLadoDireito());
			}
		}

		for (int i = 0; i < expressaoAlgebrica.getLadoDireito().size(); i++) {

			// Que não seja um algarismo com a variavel e não seja uma operacao
			if (!EnumOperacaoesCalculadoraAlgebrica.getSimbolosOperacao().containsValue(expressaoAlgebrica.getLadoDireito().get(i)) && expressaoAlgebrica.getLadoDireito().get(i).contains(identificadorVariavel)) {
				String operador = (i == 0 ? null : expressaoAlgebrica.getLadoDireito().get(i - 1));
				String algarismo = expressaoAlgebrica.getLadoDireito().get(i);

				this.trocaAlgarismoDeLado(operador, algarismo, expressaoAlgebrica.getLadoDireito(), expressaoAlgebrica.getLadoEsquerdo());
			}
		}

		return expressaoAlgebrica;
	}

	/**
	 * Inverte os lados de uma expresão algébrica para facilitar os cálculos.
	 * @param expressaoAlgebrica
	 * 
	 */
	private void inverteLado(ExpressaoAlgebrica expressaoAlgebrica) {

		List<String> temp = expressaoAlgebrica.getLadoEsquerdo();
		expressaoAlgebrica.setLadoEsquerdo(expressaoAlgebrica.getLadoDireito());
		expressaoAlgebrica.setLadoDireito(temp);
	}

	/**
	 * Faz um parse da Expressão Algebrica para uma variável se existe uma
	 * igualdade da variavel. Ex: A=10.
	 * @param expressaoAlgebrica
	 * @return Variavel
	 */
	public Variavel parsearParaVariavel(ExpressaoAlgebrica expressaoAlgebrica) {
		
		if(expressaoAlgebrica.getListaVariavel().size() == 1){
			
			if(expressaoAlgebrica.getLadoDireito().size() > 2 || expressaoAlgebrica.getLadoEsquerdo().size() > 2){
		
				expressaoAlgebrica = this.isolaVariavel(expressaoAlgebrica, expressaoAlgebrica.getListaVariavel().get(0).getId());
			}

			if (this.expressaoPodeSerParseadaParaVariavelEsquerda(expressaoAlgebrica)) {
				Variavel var = new Variavel();
				var.setId(expressaoAlgebrica.getLadoEsquerdo().get(1).replace("1", ""));
				var.setValor(Integer.valueOf(expressaoAlgebrica.getLadoDireito().get(1)));
				return var;
			} else if (this.expressaoPodeSerParseadaParaVariavelDireita(expressaoAlgebrica)) {
				Variavel var = new Variavel();
				var.setId(expressaoAlgebrica.getLadoDireito().get(1).replace("1", ""));
				var.setValor(Integer.valueOf(expressaoAlgebrica.getLadoEsquerdo().get(1)));
				return var;
			}
		}

		return null;

	}

	/**
	 * Verifica se uma expressão pode ser convertida em somente uma variavel verifcando se existe somente uma variavel no lado esquerdo. Ex: 
	 * 
	 * A = 5; Pode ser convertida em um objeto Varivel.
	 * 
	 * @param expressaoAlgebrica
	 * @return boolean
	 */
	public boolean expressaoPodeSerParseadaParaVariavelEsquerda(ExpressaoAlgebrica expressaoAlgebrica) {

		String valorAntigoLadoDireito = expressaoAlgebrica.getLadoDireito().get(1);
		String valorAntigoLadoEsquerdo = expressaoAlgebrica.getLadoEsquerdo().get(1);

		if (expressaoAlgebrica.getListaVariavel().size() == 1) {
			Integer valorCompanheiro = Integer.valueOf(expressaoAlgebrica.getLadoEsquerdo().get(1).replace(expressaoAlgebrica.getListaVariavel().get(0).getId(), ""));
			if (expressaoAlgebrica.getLadoDireito().size() == 2 && !OperacaoMatematica.contemLetra(expressaoAlgebrica.getLadoDireito().get(1)) && valorCompanheiro != 1) {

				Integer valorResultante = Integer.valueOf(expressaoAlgebrica.getLadoDireito().get(1)) / valorCompanheiro;
				expressaoAlgebrica.getLadoDireito().set(1, String.valueOf(valorResultante));

				expressaoAlgebrica.getLadoEsquerdo().set(1, "1" + expressaoAlgebrica.getListaVariavel().get(0).getId());
			}
		}

		if (expressaoAlgebrica.getLadoEsquerdo().size() == 2 && expressaoAlgebrica.getLadoDireito().size() == 2 && OperacaoMatematica.retiraLetra(expressaoAlgebrica.getLadoEsquerdo().get(1)).equals("1")
				&& OperacaoMatematica.retiraNumero(expressaoAlgebrica.getLadoDireito().get(1)).equals("")) {
			return true;
		} else {
			expressaoAlgebrica.getLadoDireito().set(1, valorAntigoLadoDireito);
			expressaoAlgebrica.getLadoEsquerdo().set(1, valorAntigoLadoEsquerdo);
			return false;
		}
	}

	/**
	 * Verifica se uma expressão pode ser convertida em somente uma variavel verifcando se existe somente uma variavel no lado direito. Ex: 
	 * 
	 * 5 = A; Pode ser convertida em um objeto Varivel.
	 * 
	 * @param expressaoAlgebrica
	 * @return boolean
	 */
	public boolean expressaoPodeSerParseadaParaVariavelDireita(ExpressaoAlgebrica expressaoAlgebrica) {

		String valorAntigoLadoDireito = expressaoAlgebrica.getLadoDireito().get(1);
		String valorAntigoLadoEsquerdo = expressaoAlgebrica.getLadoEsquerdo().get(1);

		if (expressaoAlgebrica.getListaVariavel().size() == 1) {
			Integer valorCompanheiro = Integer.valueOf(expressaoAlgebrica.getLadoDireito().get(1).replace(expressaoAlgebrica.getListaVariavel().get(0).getId(), ""));
			if (expressaoAlgebrica.getLadoEsquerdo().size() == 2 && !OperacaoMatematica.contemLetra(expressaoAlgebrica.getLadoEsquerdo().get(1)) && valorCompanheiro != 1) {

				Integer valorResultante = Integer.valueOf(expressaoAlgebrica.getLadoEsquerdo().get(1)) / valorCompanheiro;
				expressaoAlgebrica.getLadoEsquerdo().set(1, String.valueOf(valorResultante));
				expressaoAlgebrica.getLadoDireito().set(1, "1" + expressaoAlgebrica.getListaVariavel().get(0).getId());
			}
		}

		if (expressaoAlgebrica.getLadoDireito().size() == 2 && expressaoAlgebrica.getLadoEsquerdo().size() == 2 && OperacaoMatematica.retiraLetra(expressaoAlgebrica.getLadoDireito().get(1)).equals("1")
				&& OperacaoMatematica.retiraNumero(expressaoAlgebrica.getLadoEsquerdo().get(1)).equals("")) {
			return true;
		} else {
			expressaoAlgebrica.getLadoDireito().set(1, valorAntigoLadoDireito);
			expressaoAlgebrica.getLadoEsquerdo().set(1, valorAntigoLadoEsquerdo);
			return false;
		}

	}

	/**
	 * Executa as operações suportadas pela calculadora em um lado da Equação
	 * Algebrica recursivamente até que o lado tenha apenas um valor.
	 * @param lado
	 * @return
	 */
	public List<String> executaOperacoesLado(List<String> lado) {

		lado = this.agrupaVariaveisLado(lado);
		return this.executaOperacoesLadoRecursivo(lado);

	}

	/**
	 * Executa operações em um lado recusivamente em duplas
	 * @param lado
	 * @return  List<String>
	 */
	public List<String> executaOperacoesLadoRecursivo(List<String> lado) {
		if (lado.size() >= 4) {

			List<String> resultado = this.executaOperacaoSoma(lado.get(0), lado.get(1), lado.get(2), lado.get(3));

			if (resultado.size() != 2) {
				return lado;
			}

			List<String> novaLado = new ArrayList<String>();

			novaLado.addAll(resultado);

			for (int i = 4; i < lado.size(); i++) {
				novaLado.add(lado.get(i));
			}

			return executaOperacoesLadoRecursivo(novaLado);

		}

		return lado;
	}

	/**
	 * Método principal da classe que executa a operacação SOMA de dois
	 * algarismos Caso a soma não possa ser efetuada devolve-se a operação
	 * @param sinalAlg1
	 * @param alg1
	 * @param sinalAlg2
	 * @param alg2
	 * @return Lista com o sinal e resultado da soma, OU a operacao completa
	 * caso não seja possível realiza-la.
	 */
	public List<String> executaOperacaoSoma(String sinalAlg1, String alg1, String sinalAlg2, String alg2) {

		List<String> resultadoOperacao = new ArrayList<String>();
		String resultado = "";

		Integer algarismo1;
		Integer algarismo2;

		OperacaoMatematica operacaoMatematica;

		if (OperacaoMatematica.somenteNumero(alg1) && OperacaoMatematica.somenteNumero(alg2)) {

			algarismo1 = Integer.valueOf(alg1);
			algarismo2 = Integer.valueOf(alg2);

			if (sinalAlg1.equals(sinalAlg2)) {
				operacaoMatematica = new OperacaoSoma();

				resultado = operacaoMatematica.executaOperacao(algarismo1, algarismo2);

				resultadoOperacao.add(sinalAlg1);
				resultadoOperacao.add(resultado);

				return resultadoOperacao;
			} else {

				operacaoMatematica = new OperacaoSubtracao();

				resultado = operacaoMatematica.executaOperacao(algarismo1, algarismo2);

				if (algarismo2 > algarismo1) {
					resultadoOperacao.add(sinalAlg2);
				} else {
					resultadoOperacao.add(sinalAlg1);
				}

				resultadoOperacao.add(resultado);

				return resultadoOperacao;
			}

		} else if (OperacaoMatematica.contemLetra(alg1) && OperacaoMatematica.contemLetra(alg2)) {

			String identificadorVariavel1 = OperacaoMatematica.retiraNumero(alg1);
			String identificadorVariavel2 = OperacaoMatematica.retiraNumero(alg2);

			algarismo1 = Integer.valueOf(alg1.replace(identificadorVariavel1, ""));
			algarismo2 = Integer.valueOf(alg2.replace(identificadorVariavel2, ""));

			try {

				if (sinalAlg1.equals(sinalAlg2)) {
					operacaoMatematica = new OperacaoSoma();

					resultado = operacaoMatematica.executaOperacao(alg1, alg2);

					resultadoOperacao.add(sinalAlg1);
					resultadoOperacao.add(resultado);

					return resultadoOperacao;
				} else {

					operacaoMatematica = new OperacaoSubtracao();

					resultado = operacaoMatematica.executaOperacao(alg1, alg2);

					if (algarismo2 > algarismo1) {
						resultadoOperacao.add(sinalAlg2);
					} else {
						resultadoOperacao.add(sinalAlg1);
					}

					resultadoOperacao.add(resultado);

					return resultadoOperacao;
				}

			} catch (OperacaoNaoSuportadaException e) {

				resultadoOperacao.add(sinalAlg1);
				resultadoOperacao.add(alg1);
				resultadoOperacao.add(sinalAlg2);
				resultadoOperacao.add(alg2);

				return resultadoOperacao;
			}

		}

		return resultadoOperacao;
	}

	/**
	 * Troca um algarismo de lado trocando o seu operador se necessário Ex:
	 * 
	 * A - 5 = 4;   =>   A = 4 + 5;
	 *  
	 * 
	 * @param operador
	 * @param algarismo
	 * @param ladoOrigem
	 * @param ladoDestino
	 */
	public void trocaAlgarismoDeLado(String operador, String algarismo, List<String> ladoOrigem, List<String> ladoDestino) {

		String operadorInverso = EnumOperacaoesCalculadoraAlgebrica.getSimboloInversoOperacao(operador);

		if (operadorInverso == null) {
			operador.toString();
		}

		EnumOperacaoesCalculadoraAlgebrica.getSimboloInversoOperacao(operador);

		ladoOrigem.remove(ladoOrigem.indexOf(algarismo) - 1);
		ladoOrigem.remove(ladoOrigem.indexOf(algarismo));

		ladoDestino.add(operadorInverso);
		ladoDestino.add(algarismo);
	}

	/**
	 * Aplica uma condição Variavel a uma Expressão Algébrica. Ex:
	 * 
	 * A + B = 4; onde a condição é A = 3  => 3 + B = 4;
	 * 
	 * 
	 * @param expressaoAlgebrica
	 * @param variavelCondicao
	 * @param sinal
	 * @return
	 */
	public ExpressaoAlgebrica aplicaCondicao(ExpressaoAlgebrica expressaoAlgebrica, Variavel variavelCondicao, String sinal) {

		if (variavelCondicao != null) {
			aplicaCondicao(expressaoAlgebrica.getLadoEsquerdo(), variavelCondicao, sinal);
			aplicaCondicao(expressaoAlgebrica.getLadoDireito(), variavelCondicao, sinal);

			expressaoAlgebrica.getListaVariavel().remove(variavelCondicao);
		}
		return expressaoAlgebrica;
	}

	/**
	 * Aplica uma condição recursivamente por pares a um expressão algébrica, onde essa condição é uma Expressão Algébrica. Ex:
	 * 
	 * A + B = 5; onde a condição é A = B + C => B + C + B = 5;
	 * 
	 * @param expressaoAlgebrica
	 * @param variavelCondicao
	 * @param expressaoAlgebricaCondicao
	 * @return
	 */
	public ExpressaoAlgebrica aplicaCondicao(ExpressaoAlgebrica expressaoAlgebrica, Variavel variavelCondicao, ExpressaoAlgebrica expressaoAlgebricaCondicao) {

		if (variavelCondicao != null && expressaoAlgebricaCondicao.getLadoEsquerdo().size() == 2 && expressaoAlgebricaCondicao.getLadoEsquerdo().get(1).equals("1" + variavelCondicao.getId())) {
			List<String> novoLado;

			novoLado = aplicaCondicao(expressaoAlgebrica.getLadoEsquerdo(), variavelCondicao, expressaoAlgebricaCondicao);
			expressaoAlgebrica.setLadoEsquerdo(novoLado);

			novoLado = aplicaCondicao(expressaoAlgebrica.getLadoDireito(), variavelCondicao, expressaoAlgebricaCondicao);
			expressaoAlgebrica.setLadoDireito(novoLado);

			expressaoAlgebrica.getListaVariavel().remove(variavelCondicao);
		}
		return expressaoAlgebrica;
	}

	/**
	 * Aplica uma condição recursivamente por pares a um lado, onde essa condição é uma Expressão Algébrica. Ex: 
	 * 
	 * A + B; onde a condição é A = B + C => B + C + B
	 *  
	 * @param lado
	 * @param condicaoVariavel
	 * @param expressaoAlgebricaCondicao
	 * @return
	 */
	private List<String> aplicaCondicao(List<String> lado, Variavel condicaoVariavel, ExpressaoAlgebrica expressaoAlgebricaCondicao) {

		int posicao = 0;
		List<String> novoLado = new ArrayList<String>();
		for (String algarismo : lado) {
			if (algarismo.contains(condicaoVariavel.getId())) {

				Integer valorCompanheiro = Integer.valueOf(OperacaoMatematica.retiraLetra(algarismo));
				String sinal = lado.get(posicao - 1);

				List<String> resultadoConversao = new ArrayList<String>();

				int posicaoCondicao = 0;
				for (String elementoCondicao : expressaoAlgebricaCondicao.getLadoDireito()) {

					String sinalElementoCondicao = expressaoAlgebricaCondicao.getLadoDireito().get((posicaoCondicao == 0 ? posicaoCondicao : posicaoCondicao - 1));
					String resultadoMultiplicacao = "";

					if (OperacaoMatematica.somenteNumero(elementoCondicao)) {

						resultadoMultiplicacao = String.valueOf(valorCompanheiro * Integer.parseInt(elementoCondicao));

					} else if (OperacaoMatematica.contemLetra(elementoCondicao)) {

						Integer valorCompanheiroCondicao = Integer.valueOf(OperacaoMatematica.retiraLetra(elementoCondicao));
						valorCompanheiroCondicao *= valorCompanheiro;
						String identificadorVariavel = OperacaoMatematica.retiraNumero(elementoCondicao);

						resultadoMultiplicacao = String.valueOf(valorCompanheiroCondicao) + identificadorVariavel;
					}

					if (!resultadoMultiplicacao.isEmpty()) {
						if (!sinalElementoCondicao.equals(sinal)) {
							resultadoConversao.add(EnumOperacaoesCalculadoraAlgebrica.getSimboloOperacao(EnumOperacaoesCalculadoraAlgebrica.SUBTRACAO));
						} else {
							resultadoConversao.add(sinal);
						}

						resultadoConversao.add(resultadoMultiplicacao);
					}

					posicaoCondicao++;
				}

				novoLado.addAll(resultadoConversao);
				resultadoConversao = new ArrayList<String>();

			} else {
				if ((posicao != lado.size() - 1 && !lado.get(posicao + 1).contains(condicaoVariavel.getId())) || posicao == lado.size() - 1) {
					novoLado.add(algarismo);
				}
			}
			posicao++;
		}

		return novoLado;
	}

	/**
	 * Aplica uma condição dado um lado e uma variável e o seu sinal de operação. Ex:
	 * A + B; onde A = 3 => 3 + B 
	 * @param lado
	 * @param condicaoVariavel
	 * @param sinal
	 */
	private void aplicaCondicao(List<String> lado, Variavel condicaoVariavel, String sinal) {
		int posicao = 0;
		for (String algarismo : lado) {
			if (algarismo.contains(condicaoVariavel.getId())) {

				Integer valorCompanheiro = Integer.valueOf(OperacaoMatematica.retiraLetra(algarismo));
				valorCompanheiro *= condicaoVariavel.getValor();

				if (!lado.get(posicao - 1).equals(sinal)) {
					lado.set(posicao - 1, EnumOperacaoesCalculadoraAlgebrica.getSimboloOperacao(EnumOperacaoesCalculadoraAlgebrica.SUBTRACAO));
				}

				lado.set(posicao, String.valueOf(valorCompanheiro));

			}
			posicao++;
		}
	}

	/**
	 * Mantém as ocorrencias de uma variável agrupadas dentro do lado.Ex:
	 * 
	 * 2A + 5 + 3A = 4 => 2A + 3A + 5 = 4;
	 *  
	 * @param lado
	 * @return
	 */
	public List<String> agrupaVariaveisLado(List<String> lado) {

		HashMap<String, List<String>> mapaLado = new HashMap<String, List<String>>();

		List<String> ladoAgrupado = new ArrayList<String>();

		String chaveParaNumeros = "ZZZNUMEROS";
		String operador;

		int indice = 0;
		for (String algarismo : lado) {
			if (EnumOperacaoesCalculadoraAlgebrica.getSimboloOperacaoPeloString(algarismo) == null) {
				if (OperacaoMatematica.somenteNumero(algarismo)) {
					if (!mapaLado.containsKey(chaveParaNumeros)) {
						mapaLado.put(chaveParaNumeros, new ArrayList<String>());
					}

					if (indice == 0) {
						operador = EnumOperacaoesCalculadoraAlgebrica.getSimboloOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA);
					} else {
						operador = lado.get(indice - 1);
					}

					mapaLado.get(chaveParaNumeros).add(operador);
					mapaLado.get(chaveParaNumeros).add(algarismo);

				} else if (OperacaoMatematica.contemLetra(algarismo)) {
					String identificadorVariavel = OperacaoMatematica.retiraNumero(algarismo);

					if (!mapaLado.containsKey(identificadorVariavel)) {
						mapaLado.put(identificadorVariavel, new ArrayList<String>());
					}

					if (indice == 0) {
						operador = EnumOperacaoesCalculadoraAlgebrica.getSimboloOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA);
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

}
