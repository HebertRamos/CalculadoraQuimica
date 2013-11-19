package ufms.calculadora.extensoes.calculoBalanceamento;

import java.util.ArrayList;
import java.util.List;

import ufms.calculadora.extensoes.calculadoraAlgebrica.CalculadoraAlgebrica;
import ufms.calculadora.extensoes.calculadoraAlgebrica.ExpressaoAlgebrica;
import ufms.calculadora.extensoes.calculadoraAlgebrica.Variavel;
import ufms.calculadora.modelo.Elemento;
import ufms.calculadora.modelo.EnumSiglaElemento;
import ufms.calculadora.modelo.EquacaoQuimica;
import ufms.calculadora.modelo.Solucao;

/**
 * Classe que executa o Balanceamento das Equações Químicas usando o Método Algébrico.
 *  
 * @author Hebert Ramos
 *
 */
public class MetodoAlgebrico extends CalculoBalanceamento {

	
	@Override
	public void setEquacaoQuimica(EquacaoQuimica equacaoQuimica) {
		// TODO Auto-generated method stub

	}

	/**
	 * Método principal, faz o balanceamento.
	 * @return EquacaoQuimica
	 */
	@Override
	public EquacaoQuimica balancearEquacao(EquacaoQuimica equacaoQuimica) throws Exception {

		List<EnumSiglaElemento> siglasElementos = this.carregaSiglasElementos(equacaoQuimica.getReagentes());

		List<ExpressaoDeIndice> listaExpressaoIndice = new ArrayList<ExpressaoDeIndice>();
		for (EnumSiglaElemento siglaElemento : siglasElementos) {
			listaExpressaoIndice.add(this.carregaExpressaoDeIndiceDoElemento(siglaElemento, equacaoQuimica));
		}

		List<Variavel> listaVariaveisDasExpressoes = this.carregaVariaveisDasExpressoesDeIndice(listaExpressaoIndice);

		this.odernaExpressoesIndicesDeAcordoComAQuantidade(listaExpressaoIndice);

		this.descobreValoresDasVariaveisDasExpressoes(listaVariaveisDasExpressoes, listaExpressaoIndice);

		this.ajustaValoresDosIndicesDasSolucoes(listaVariaveisDasExpressoes, listaExpressaoIndice);

		return equacaoQuimica;
	}

	/**
	 * Ordena as variáveis de indice a partir das exressões algébricas geradas pela equação química. 
	 * @param listaExpressaoIndice
	 */
	private void odernaExpressoesIndicesDeAcordoComAQuantidade(List<ExpressaoDeIndice> listaExpressaoIndice) {

		for (int i = 0; i < listaExpressaoIndice.size() - 1; i++) {

			int menor = i;
			int somaMenor = listaExpressaoIndice.get(menor).geraExpressaoAlgebrica().getListaVariavel().size() + listaExpressaoIndice.get(menor).geraExpressaoAlgebrica().getListaVariavel().size();
			for (int j = 1; j < listaExpressaoIndice.size(); j++) {

				int somaAtual = listaExpressaoIndice.get(j).geraExpressaoAlgebrica().getListaVariavel().size() + listaExpressaoIndice.get(j).geraExpressaoAlgebrica().getListaVariavel().size();

				if (somaAtual < somaMenor) {
					menor = j;
					somaMenor = listaExpressaoIndice.get(menor).getListaIndiceReagente().size() + listaExpressaoIndice.get(menor).getListaIndiceProduto().size();
				}
			}

			ExpressaoDeIndice temp = listaExpressaoIndice.get(i);
			listaExpressaoIndice.set(i, listaExpressaoIndice.get(menor));
			listaExpressaoIndice.set(menor, temp);
		}
	}

	/**
	 * Corrige o valor dos índices das soluções a partir das Variaveis e Expressão de índice de cada elemento.
	 * @param listaVariaveisDasExpressoes
	 * @param listaExpressaoIndice
	 */
	public void ajustaValoresDosIndicesDasSolucoes(List<Variavel> listaVariaveisDasExpressoes, List<ExpressaoDeIndice> listaExpressaoIndice) {

		for (ExpressaoDeIndice expressaoDeIndice : listaExpressaoIndice) {
			for (IndiceMetodoAlgebrico indice : expressaoDeIndice.getListaIndiceProduto()) {
				Integer i = listaVariaveisDasExpressoes.indexOf(indice.getVariavel());
				if (i != -1) {
					Variavel var = listaVariaveisDasExpressoes.get(i);
					indice.getSolucao().setIndice(var.getValor());
				}
			}
			for (IndiceMetodoAlgebrico indice : expressaoDeIndice.getListaIndiceReagente()) {
				Integer i = listaVariaveisDasExpressoes.indexOf(indice.getVariavel());
				if (i != -1) {
					Variavel var = listaVariaveisDasExpressoes.get(i);
					indice.getSolucao().setIndice(var.getValor());
				}
			}
		}
	}

	/**
	 * Isola as variáveis das expressoes geradas da equação química
	 * @param listaVariaveisDasExpressoes
	 * @param listaExpressaoIndice
	 * @throws Exception
	 */
	public void descobreValoresDasVariaveisDasExpressoes(List<Variavel> listaVariaveisDasExpressoes, List<ExpressaoDeIndice> listaExpressaoIndice) throws Exception {

		int tamanhoInicial = listaVariaveisDasExpressoes.size();

		List<ExpressaoAlgebrica> expressoesAlgebricasNaoResolvidas = new ArrayList<ExpressaoAlgebrica>();
		for (ExpressaoDeIndice expressaoDeIndice : listaExpressaoIndice) {
			expressoesAlgebricasNaoResolvidas.add(expressaoDeIndice.geraExpressaoAlgebrica());
		}

		List<Variavel> listaVariaveisNaoResolvidas = listaVariaveisDasExpressoes;

		List<Variavel> listaVariaveisResolvidas = new ArrayList<Variavel>();

		Integer valorInicial = 2;
		Variavel var = new Variavel();
		var.setId(listaVariaveisDasExpressoes.get(0).getId());
		var.setValor(valorInicial);

		listaVariaveisResolvidas.add(var);
		listaVariaveisNaoResolvidas.remove(var);

		this.descobreValoresDasVariaveisRecursivo(listaVariaveisNaoResolvidas, listaVariaveisResolvidas, expressoesAlgebricasNaoResolvidas);

		if (tamanhoInicial == listaVariaveisResolvidas.size()) {
			for (Variavel var1 : listaVariaveisResolvidas) {
				var1.setValor(var1.getValor() / valorInicial);
				listaVariaveisDasExpressoes.add(var1);
			}
		}
	}

	/**
	 * Descobre o valor das váriaveis ainda não reslvidas das expressões algébricas.
	 * 
	 * @param listaVariaveisNaoResolvidas
	 * @param listaVariaveisResolvidas
	 * @param expressoesAlgebricasNaoResolvidas
	 * @throws Exception
	 */
	public void descobreValoresDasVariaveisRecursivo(List<Variavel> listaVariaveisNaoResolvidas, List<Variavel> listaVariaveisResolvidas, List<ExpressaoAlgebrica> expressoesAlgebricasNaoResolvidas) throws Exception {

		if (listaVariaveisNaoResolvidas.size() != 0) {

			CalculadoraAlgebrica calculadoraAlgebrica = new CalculadoraAlgebrica();

			for (ExpressaoAlgebrica expressaoAlgebrica : expressoesAlgebricasNaoResolvidas) {
				for (Variavel condicao : listaVariaveisResolvidas) {
					calculadoraAlgebrica.aplicaCondicao(expressaoAlgebrica, condicao, "+");
				}
			}

			int indice = 0;
			List<Integer> indicesParaSeremExcluidos = new ArrayList<Integer>();
			for (ExpressaoAlgebrica expressaoAlgebrica : expressoesAlgebricasNaoResolvidas) {

				Variavel variavelResolvida = calculadoraAlgebrica.parsearParaVariavel(expressaoAlgebrica);
				if (variavelResolvida != null) {

					listaVariaveisResolvidas.add(variavelResolvida);
					listaVariaveisNaoResolvidas.remove(listaVariaveisNaoResolvidas.indexOf(variavelResolvida));

					indicesParaSeremExcluidos.add(indice);
				}
				indice++;
			}

			if (indicesParaSeremExcluidos.size() > 0) {
				
				List<ExpressaoAlgebrica> expressoesASeremExcluidas = new ArrayList<ExpressaoAlgebrica>();

				for (int indiceParaSerExcluido : indicesParaSeremExcluidos) {
					expressoesASeremExcluidas.add(expressoesAlgebricasNaoResolvidas.get(indiceParaSerExcluido));
				}
				
				expressoesAlgebricasNaoResolvidas.removeAll(expressoesASeremExcluidas);
				
				this.descobreValoresDasVariaveisRecursivo(listaVariaveisNaoResolvidas, listaVariaveisResolvidas, expressoesAlgebricasNaoResolvidas);

			} else {

				if (listaVariaveisNaoResolvidas.size() == 2 && expressoesAlgebricasNaoResolvidas.size() == 2) {

					calculadoraAlgebrica.isolaVariavel(expressoesAlgebricasNaoResolvidas.get(0), expressoesAlgebricasNaoResolvidas.get(1), listaVariaveisNaoResolvidas.get(0).getId());

					Variavel variavelResolvida1 = calculadoraAlgebrica.parsearParaVariavel(expressoesAlgebricasNaoResolvidas.get(1));

					if (variavelResolvida1 != null) {

						calculadoraAlgebrica.aplicaCondicao(expressoesAlgebricasNaoResolvidas.get(0), variavelResolvida1, "+");
						calculadoraAlgebrica.isolaVariavel(expressoesAlgebricasNaoResolvidas.get(0), listaVariaveisNaoResolvidas.get(1).getId());

						Variavel variavelResolvida2 = calculadoraAlgebrica.parsearParaVariavel(expressoesAlgebricasNaoResolvidas.get(0));

						if (variavelResolvida2 != null) {
							listaVariaveisResolvidas.add(variavelResolvida1);
							listaVariaveisResolvidas.add(variavelResolvida2);

							listaVariaveisNaoResolvidas.remove(variavelResolvida1);
							listaVariaveisNaoResolvidas.remove(variavelResolvida2);
						}

					} else {
						throw new Exception("Impossível definir 1 ");
					}
				} else {
					throw new Exception("Impossível definir 2 ");
				}

			}

		}
	}

	/**
	 * Busca as variáveis de Indice de cada elemento da equação química.
	 * @param listaExpressaoIndice
	 * @return
	 */
	public List<Variavel> carregaVariaveisDasExpressoesDeIndice(List<ExpressaoDeIndice> listaExpressaoIndice) {

		List<String> listaIdentificadorVariaveisDasExpressoesDeIndice = new ArrayList<String>();
		for (ExpressaoDeIndice expressaoDeIndice : listaExpressaoIndice) {
			for (IndiceMetodoAlgebrico indice : expressaoDeIndice.getListaIndiceReagente()) {
				if (!listaIdentificadorVariaveisDasExpressoesDeIndice.contains(indice.getVariavel().getId())) {
					listaIdentificadorVariaveisDasExpressoesDeIndice.add(indice.getVariavel().getId());
				}
			}
			for (IndiceMetodoAlgebrico indice : expressaoDeIndice.getListaIndiceProduto()) {
				if (!listaIdentificadorVariaveisDasExpressoesDeIndice.contains(indice.getVariavel().getId())) {
					listaIdentificadorVariaveisDasExpressoesDeIndice.add(indice.getVariavel().getId());
				}
			}

		}
		List<Variavel> variaveisDasExpressoesDeIndice = new ArrayList<Variavel>();
		for (String identificadorVariavel : listaIdentificadorVariaveisDasExpressoesDeIndice) {
			variaveisDasExpressoesDeIndice.add(new Variavel(identificadorVariavel));
		}
		return variaveisDasExpressoesDeIndice;
	}

	/**
	 * Gera uma expressão de índice para o elemento da equação química.
	 * @param siglaElemento
	 * @param equacaoQuimica
	 * @return
	 */
	public ExpressaoDeIndice carregaExpressaoDeIndiceDoElemento(EnumSiglaElemento siglaElemento, EquacaoQuimica equacaoQuimica) {

		ExpressaoDeIndice expressaoDeIndice = new ExpressaoDeIndice();
		expressaoDeIndice.setSiglaElemento(siglaElemento);

		int indiceSolucao = 0;
		for (Solucao solucao : equacaoQuimica.getReagentes()) {
			Integer valorCompanheiro = 0;
			for (Elemento elemento : solucao.getElementos()) {
				if (elemento.getSigla().equals(siglaElemento)) {
					valorCompanheiro += elemento.getCoeficiente();
				}
			}
			if (valorCompanheiro != 0) {
				expressaoDeIndice.adicionarIndiceReagente(new IndiceMetodoAlgebrico(indiceSolucao, valorCompanheiro, solucao));
			}
			indiceSolucao++;
		}

		for (Solucao solucao : equacaoQuimica.getProdutos()) {
			Integer valorCompanheiro = 0;
			for (Elemento elemento : solucao.getElementos()) {
				if (elemento.getSigla().equals(siglaElemento)) {
					valorCompanheiro += elemento.getCoeficiente();
				}
			}
			if (valorCompanheiro != 0) {
				expressaoDeIndice.adicionarIndiceProduto(new IndiceMetodoAlgebrico(indiceSolucao, valorCompanheiro, solucao));
			}
			indiceSolucao++;
		}

		expressaoDeIndice.geraExpressaoAlgebrica().toString();

		return expressaoDeIndice;
	}
}