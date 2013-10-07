package ufms.calculadora.extensoes.calculoEstequiometrico;

import java.util.List;

import ufms.calculadora.modelo.EquacaoQuimica;
import ufms.calculadora.modelo.Solucao;

public class CalculoEstequiometrico {

	private VariavelEstequiometrica variavel1;
	private VariavelEstequiometrica variavel2;

	public VariavelEstequiometrica calculaProporcao(EquacaoQuimica equacaoQuimica, Solucao solucaoChave, EnumMedidaGrandeza grandeza){
		
		
		VariavelEstequiometrica varivalSolucaoChave = FabricaVariavelEstequiometrica.criaVariavel(grandeza);
		varivalSolucaoChave.setValor(new Double(solucaoChave.getIndice()));
		
		if(!grandeza.equals(variavel1.getGrandeza()) || !grandeza.equals(variavel2.getGrandeza())){
			//execao grandezas diferentes.
		}

		return null;
	}

	public void setSolucao1(List<Solucao> listaSolucao1,Integer posicaoSolucao1, EnumMedidaGrandeza grandeza)throws ArrayIndexOutOfBoundsException {
		
			Solucao solucao = listaSolucao1.get(posicaoSolucao1);
			variavel1 = FabricaVariavelEstequiometrica.criaVariavel(grandeza);
			variavel1.setValor(new Double(solucao.getIndice()));
	}

	public void setSolucao2(List<Solucao> listaSolucao2, Integer posicaoSolucao2, EnumMedidaGrandeza grandeza) throws ArrayIndexOutOfBoundsException {

			Solucao solucao = listaSolucao2.get(posicaoSolucao2);
			variavel2 = FabricaVariavelEstequiometrica.criaVariavel(grandeza);
			variavel2.setValor(new Double(solucao.getIndice()));
	}

}
