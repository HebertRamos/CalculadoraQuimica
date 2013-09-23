package ufms.calculadora.extensoes.calculoBalanceamento;

import java.util.ArrayList;
import java.util.List;

import ufms.calculadora.extensoes.calculadoraAlgebrica.CalculadoraAlgebrica;
import ufms.calculadora.extensoes.calculadoraAlgebrica.ExpressaoAlgebrica;
import ufms.calculadora.extensoes.calculadoraAlgebrica.OperacaoNaoSuportadaException;
import ufms.calculadora.extensoes.calculadoraAlgebrica.Variavel;
import ufms.calculadora.modelo.Elemento;
import ufms.calculadora.modelo.EnumSiglaElemento;
import ufms.calculadora.modelo.EquacaoQuimica;
import ufms.calculadora.modelo.Solucao;


public class MetodoAlgebrico extends CalculoBalanceamento{
	

	@Override
	public void setEquacaoQuimica(EquacaoQuimica equacaoQuimica) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EquacaoQuimica balancearEquacao(EquacaoQuimica equacaoQuimica) throws OperacaoNaoSuportadaException {
		
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
	
	private void odernaExpressoesIndicesDeAcordoComAQuantidade(List<ExpressaoDeIndice> listaExpressaoIndice) {
		
		for(int i = 0; i < listaExpressaoIndice.size() - 1; i++){
			
			int menor = i;
			int somaMenor = listaExpressaoIndice.get(menor).geraExpressaoAlgebrica().getListaVariavel().size() + listaExpressaoIndice.get(menor).geraExpressaoAlgebrica().getListaVariavel().size(); 
			for(int j = 1; j < listaExpressaoIndice.size(); j++){
				
				int somaAtual = listaExpressaoIndice.get(j).geraExpressaoAlgebrica().getListaVariavel().size() + listaExpressaoIndice.get(j).geraExpressaoAlgebrica().getListaVariavel().size();
				
				if(somaAtual < somaMenor){
					menor = j;
					somaMenor = listaExpressaoIndice.get(menor).getListaIndiceReagente().size() + listaExpressaoIndice.get(menor).getListaIndiceProduto().size();
				}
			}
			
			ExpressaoDeIndice temp = listaExpressaoIndice.get(i);
			listaExpressaoIndice.set(i, listaExpressaoIndice.get(menor));
			listaExpressaoIndice.set(menor, temp);
		}
	}

	public void ajustaValoresDosIndicesDasSolucoes(List<Variavel> listaVariaveisDasExpressoes, List<ExpressaoDeIndice> listaExpressaoIndice){
		
		
		for (ExpressaoDeIndice expressaoDeIndice : listaExpressaoIndice) {
			for (IndiceMetodoAlgebrico indice : expressaoDeIndice.getListaIndiceProduto()) {
				Integer i = listaVariaveisDasExpressoes.indexOf(indice.getVariavel());
				if(i != -1){
					Variavel var = listaVariaveisDasExpressoes.get(i);
					indice.getSolucao().setIndice(var.getValor());
				}
			}
		}
	}
	
	public void descobreValoresDasVariaveisDasExpressoes(List<Variavel> listaVariaveisDasExpressoes, List<ExpressaoDeIndice> listaExpressaoIndice) throws OperacaoNaoSuportadaException{
		
		Integer valorInicial = 2;
		listaVariaveisDasExpressoes.get(0).setValor(valorInicial);
		
		CalculadoraAlgebrica calculadoraAlgebrica = new CalculadoraAlgebrica();
		for(int i = 1; i < listaVariaveisDasExpressoes.size(); i++){
			
			ExpressaoAlgebrica expressaoAlgebrica = listaExpressaoIndice.get(i - 1).geraExpressaoAlgebrica();
			Variavel variavelAnterior;
			String sinal = "+";
			int j = i - 1;
			
			do{
				variavelAnterior = listaVariaveisDasExpressoes.get(j);
				j--;
			}while(j > -1 && (variavelAnterior.getValor() != 0 && !expressaoAlgebrica.possuiVarivel(variavelAnterior)));
			
			Variavel variavelAtual = listaVariaveisDasExpressoes.get(i);
			
			calculadoraAlgebrica.aplicaCondicao(expressaoAlgebrica, variavelAnterior, sinal);
			calculadoraAlgebrica.isolaVariavel(expressaoAlgebrica, variavelAtual.getId());
			
			variavelAtual = calculadoraAlgebrica.parsearParaVariavel(expressaoAlgebrica);
			listaVariaveisDasExpressoes.set(i, variavelAtual);
		}
		
		for (Variavel var : listaVariaveisDasExpressoes) {
			var.setValor(var.getValor() / valorInicial);
		}
	}
	
	public List<Variavel> carregaVariaveisDasExpressoesDeIndice(List<ExpressaoDeIndice> listaExpressaoIndice){
		
		List<String> listaIdentificadorVariaveisDasExpressoesDeIndice = new ArrayList<String>();
		for (ExpressaoDeIndice expressaoDeIndice : listaExpressaoIndice) {
			for (IndiceMetodoAlgebrico indice : expressaoDeIndice.getListaIndiceReagente()) {
				if(!listaIdentificadorVariaveisDasExpressoesDeIndice.contains(indice.getVariavel().getId())){
					listaIdentificadorVariaveisDasExpressoesDeIndice.add(indice.getVariavel().getId());
				}
			}
			for (IndiceMetodoAlgebrico indice : expressaoDeIndice.getListaIndiceProduto()) {
				if(!listaIdentificadorVariaveisDasExpressoesDeIndice.contains(indice.getVariavel().getId())){
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
	
	public ExpressaoDeIndice carregaExpressaoDeIndiceDoElemento(EnumSiglaElemento siglaElemento, EquacaoQuimica equacaoQuimica){
		
		ExpressaoDeIndice expressaoDeIndice = new ExpressaoDeIndice();
		expressaoDeIndice.setSiglaElemento(siglaElemento);
		
		int indiceSolucao = 0;
		for (Solucao solucao : equacaoQuimica.getReagentes()) {
			Integer valorCompanheiro = 0;
			for (Elemento elemento : solucao.getElementos()) {
				if(elemento.getSigla().equals(siglaElemento)){
					valorCompanheiro += elemento.getCoeficiente();
				}
			}
			if(valorCompanheiro != 0){
				expressaoDeIndice.adicionarIndiceReagente(new IndiceMetodoAlgebrico(indiceSolucao, valorCompanheiro, solucao));
			}
			indiceSolucao++;
		}
		
		for (Solucao solucao : equacaoQuimica.getProdutos()) {
			Integer valorCompanheiro = 0;
			for (Elemento elemento : solucao.getElementos()) {
				if(elemento.getSigla().equals(siglaElemento)){
					valorCompanheiro += elemento.getCoeficiente();
				}
			}
			if(valorCompanheiro != 0){
				expressaoDeIndice.adicionarIndiceProduto(new IndiceMetodoAlgebrico(indiceSolucao, valorCompanheiro, solucao));
			}
			indiceSolucao++;
		}
		
		expressaoDeIndice.geraExpressaoAlgebrica().toString();
		
		return expressaoDeIndice;
	}
}