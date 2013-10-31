package ufms.calculadora.negocio;

import ufms.calculadora.extensoes.calculadoraAlgebrica.OperacaoNaoSuportadaException;
import ufms.calculadora.extensoes.calculoBalanceamento.CalculoBalanceamento;
import ufms.calculadora.extensoes.calculoBalanceamento.MetodoAlgebrico;
import ufms.calculadora.modelo.EquacaoQuimica;

public class BalanceamentoController {
	
	private EquacaoQuimica equacaoQuimica;
	
	public BalanceamentoController() {
		equacaoQuimica = new EquacaoQuimica();
	}
	
	public EquacaoQuimica getEquacaoQuimica() {
		return equacaoQuimica;
	}
	
	
	public void balancearEquacao() throws OperacaoNaoSuportadaException, Exception{
		CalculoBalanceamento calculoBalanceamento = new MetodoAlgebrico();
		equacaoQuimica = calculoBalanceamento.balancearEquacao(equacaoQuimica);
	}

}
