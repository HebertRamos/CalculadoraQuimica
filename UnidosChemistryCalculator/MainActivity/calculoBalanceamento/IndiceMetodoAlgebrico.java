package ufms.calculadora.extensoes.calculoBalanceamento;

import java.util.ArrayList;
import java.util.List;

import ufms.calculadora.extensoes.calculadoraAlgebrica.Variavel;
import ufms.calculadora.modelo.Solucao;

public class IndiceMetodoAlgebrico {

	private static List<String> listaIndentificadoresVariaveis;

	private Integer id;
	private Variavel variavel;
	private Solucao solucao;

	public IndiceMetodoAlgebrico(Integer id, Integer valorCompanheiro, Solucao solucao) {
		this.id = id;
		this.variavel = new Variavel();
		this.variavel.setId(getIndentificadoresVariaveis().get(id));
		this.variavel.setValorCompanheiro(valorCompanheiro);

		this.solucao = solucao;
	}

	public IndiceMetodoAlgebrico(Integer id) {
		this.id = id;
		this.variavel = new Variavel();
		this.variavel.setId(getIndentificadoresVariaveis().get(id));
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Variavel getVariavel() {
		return variavel;
	}

	public void setVariavel(Variavel variavel) {
		this.variavel = variavel;
	}

	public Solucao getSolucao() {
		return solucao;
	}

	public void setSolucao(Solucao solucao) {
		this.solucao = solucao;
	}

	private static List<String> getIndentificadoresVariaveis() {

		if (listaIndentificadoresVariaveis == null) {
			listaIndentificadoresVariaveis = new ArrayList<String>();
		}

		listaIndentificadoresVariaveis.add("A");
		listaIndentificadoresVariaveis.add("B");
		listaIndentificadoresVariaveis.add("C");
		listaIndentificadoresVariaveis.add("D");
		listaIndentificadoresVariaveis.add("E");
		listaIndentificadoresVariaveis.add("F");
		listaIndentificadoresVariaveis.add("G");
		listaIndentificadoresVariaveis.add("H");
		listaIndentificadoresVariaveis.add("I");
		listaIndentificadoresVariaveis.add("J");
		listaIndentificadoresVariaveis.add("K");
		listaIndentificadoresVariaveis.add("L");
		listaIndentificadoresVariaveis.add("M");
		listaIndentificadoresVariaveis.add("N");
		listaIndentificadoresVariaveis.add("O");
		listaIndentificadoresVariaveis.add("P");
		listaIndentificadoresVariaveis.add("Q");
		listaIndentificadoresVariaveis.add("R");
		listaIndentificadoresVariaveis.add("S");
		listaIndentificadoresVariaveis.add("U");
		listaIndentificadoresVariaveis.add("V");
		listaIndentificadoresVariaveis.add("W");
		listaIndentificadoresVariaveis.add("X");
		listaIndentificadoresVariaveis.add("Y");
		listaIndentificadoresVariaveis.add("Z");

		return listaIndentificadoresVariaveis;
	}
}
