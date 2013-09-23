package ufms.calculadora.extensoes.calculoBalanceamento;

import java.util.ArrayList;
import java.util.List;

import ufms.calculadora.extensoes.calculadoraAlgebrica.EnumOperacaoesCalculadoraAlgebrica;
import ufms.calculadora.extensoes.calculadoraAlgebrica.ExpressaoAlgebrica;
import ufms.calculadora.modelo.EnumSiglaElemento;

public class ExpressaoDeIndice {
	
	public EnumSiglaElemento siglaElemento;
	public List<IndiceMetodoAlgebrico> listaIndiceReagente;
	public List<IndiceMetodoAlgebrico> listaIndiceProduto;

	public EnumSiglaElemento getSiglaElemento() {
		return siglaElemento;
	}
	
	public void setSiglaElemento(EnumSiglaElemento siglaElemento) {
		this.siglaElemento = siglaElemento;
	}
	
	public List<IndiceMetodoAlgebrico> getListaIndiceReagente() {
		return listaIndiceReagente;
	}
	
	public void setListaIndiceReagente(
			List<IndiceMetodoAlgebrico> listaIndiceReagente) {
		this.listaIndiceReagente = listaIndiceReagente;
	}
	
	public List<IndiceMetodoAlgebrico> getListaIndiceProduto() {
		return listaIndiceProduto;
	}
	
	public void setListaIndiceProduto(List<IndiceMetodoAlgebrico> listaIndiceProduto) {
		this.listaIndiceProduto = listaIndiceProduto;
	}
	public void adicionarIndiceReagente(IndiceMetodoAlgebrico indice){
		if(this.listaIndiceReagente == null){
			this.listaIndiceReagente = new ArrayList<IndiceMetodoAlgebrico>();
		}
		this.listaIndiceReagente.add(indice);
	}
	
	public void adicionarIndiceProduto(IndiceMetodoAlgebrico indice){
		if(this.listaIndiceProduto == null){
			this.listaIndiceProduto = new ArrayList<IndiceMetodoAlgebrico>();
		}
		this.listaIndiceProduto.add(indice);
	}
	
	public ExpressaoAlgebrica geraExpressaoAlgebrica(){
		ExpressaoAlgebrica expressaoAlgebrica = new ExpressaoAlgebrica();
		
		expressaoAlgebrica.setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA);
		for (int i = 0; i < this.listaIndiceReagente.size(); i++) {
			if(i != 0 && (i + 1)%2 == 0){
				expressaoAlgebrica.setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA);
			}
			expressaoAlgebrica.setVariavel(listaIndiceReagente.get(i).getVariavel());
		}
		
		expressaoAlgebrica.setOperacao(EnumOperacaoesCalculadoraAlgebrica.IGUALDADE);
		expressaoAlgebrica.setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA);
		for (int i = 0; i < this.listaIndiceProduto.size(); i++) {
			if(i != 0 && (i + 1)%2 == 0){
				expressaoAlgebrica.setOperacao(EnumOperacaoesCalculadoraAlgebrica.SOMA);
			}
			expressaoAlgebrica.setVariavel(listaIndiceProduto.get(i).getVariavel());
		}
		
		return expressaoAlgebrica;
	}
}
