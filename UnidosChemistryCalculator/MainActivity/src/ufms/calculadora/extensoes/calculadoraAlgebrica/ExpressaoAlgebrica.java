package ufms.calculadora.extensoes.calculadoraAlgebrica;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que modela a expressão Algébrica.
 * 
 * @author Hebert Ramos
 *
 */
public class ExpressaoAlgebrica {

	private List<Variavel> listaVariavel = new ArrayList<Variavel>();

	private List<String> ladoDireito = new ArrayList<String>();
	private List<String> ladoEsquerdo = new ArrayList<String>();

	private boolean setouIgualdade;

	public ExpressaoAlgebrica setValorImediato(Integer variavel) {
		getLado(this.setouIgualdade).add(String.valueOf(variavel));
		return this;
	}

	public ExpressaoAlgebrica setVariavel(String variavelString) {
		return this.setVariavel(new Variavel(variavelString));
	}

	public ExpressaoAlgebrica setVariavel(Variavel variavel) {
		getLado(this.setouIgualdade).add(variavel.getValorCompanheiro() + variavel.getId());
		listaVariavel.add(variavel);
		return this;
	}

	public List<Variavel> getListaVariavel() {
		return this.listaVariavel;
	}

	public boolean possuiVarivel(Variavel variavel) {
		if (this.listaVariavel != null) {
			for (Variavel var : this.listaVariavel) {
				if (var.getId().equals(variavel.getId())) {
					return true;
				}
			}
		}
		return false;
	}

	public List<String> getLadoDireito() {
		return ladoDireito;
	}

	public void setLadoDireito(List<String> ladoDireito) {
		this.ladoDireito = ladoDireito;
	}

	public List<String> getLadoEsquerdo() {
		return ladoEsquerdo;
	}

	public void setLadoEsquerdo(List<String> ladoEsquerdo) {
		this.ladoEsquerdo = ladoEsquerdo;
	}

	public ExpressaoAlgebrica setOperacao(EnumOperacaoesCalculadoraAlgebrica operacao) {

		if (EnumOperacaoesCalculadoraAlgebrica.IGUALDADE.equals(operacao)) {
			this.setouIgualdade = true;
		} else {
			getLado(this.setouIgualdade).add(EnumOperacaoesCalculadoraAlgebrica.getSimboloOperacao(operacao));
		}
		return this;
	}

	public boolean ladoContemVariavel(List<String> lado, String variavel) {
		for (String string : lado) {
			if (string.contains(variavel)) {
				return true;
			}
		}

		return false;
	}

	public String toString() {
		String expressao = "";
		for (String algarismo : this.ladoEsquerdo) {
			expressao += algarismo;
		}

		expressao += "=";

		for (String algarismo : this.ladoDireito) {
			expressao += algarismo;
		}
		return expressao;

	}

	private List<String> getLado(boolean setouIgualdade) {
		if (setouIgualdade) {
			return this.ladoDireito;
		}
		return this.ladoEsquerdo;
	}

}
