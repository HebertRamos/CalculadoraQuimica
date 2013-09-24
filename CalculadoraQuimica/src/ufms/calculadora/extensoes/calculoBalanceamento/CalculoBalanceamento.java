package ufms.calculadora.extensoes.calculoBalanceamento;

import java.util.ArrayList;
import java.util.List;

import ufms.calculadora.extensoes.calculadoraAlgebrica.OperacaoNaoSuportadaException;
import ufms.calculadora.modelo.Elemento;
import ufms.calculadora.modelo.EnumSiglaElemento;
import ufms.calculadora.modelo.EquacaoQuimica;
import ufms.calculadora.modelo.Solucao;

public class CalculoBalanceamento {

	public boolean estaBalanceada(EquacaoQuimica equacaoQuimica) {
		return true;
	}

	public void setEquacaoQuimica(EquacaoQuimica equacaoQuimica) {
		// TODO Auto-generated method stub

	}

	public EquacaoQuimica balancearEquacao(EquacaoQuimica equacaoQuimica) throws OperacaoNaoSuportadaException, Exception {
		return null;
	}

	public List<EnumSiglaElemento> carregaSiglasElementos(List<Solucao> solucoes) {

		List<EnumSiglaElemento> siglasElementos = new ArrayList<EnumSiglaElemento>();
		for (Solucao solucao : solucoes) {
			for (Elemento elemento : solucao.getElementos()) {
				if (!siglasElementos.contains(elemento.getSigla())) {
					siglasElementos.add(elemento.getSigla());
				}
			}

		}

		return siglasElementos;
	}

}
