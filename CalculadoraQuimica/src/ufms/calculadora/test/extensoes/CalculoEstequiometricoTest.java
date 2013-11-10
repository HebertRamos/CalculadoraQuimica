package ufms.calculadora.test.extensoes;

import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

import ufms.calculadora.extensoes.calculoEstequiometrico.CalculoEstequiometrico;
import ufms.calculadora.extensoes.calculoEstequiometrico.EnumGrandezaQuimica;
import ufms.calculadora.extensoes.leitorXML.TabelaPeriodica;
import ufms.calculadora.modelo.Elemento;
import ufms.calculadora.modelo.EnumSiglaElemento;
import ufms.calculadora.modelo.Solucao;

public class CalculoEstequiometricoTest extends TestCase {

	private Solucao getSolucaoAgua() {

		List<Elemento> elementosTabelaPeriodica = TabelaPeriodica
				.getElementos();

		Elemento hidrogenio = null;
		Elemento oxigenio = null;

		for (Elemento elemento : elementosTabelaPeriodica) {

			if (elemento.getSigla().equals(EnumSiglaElemento.H)) {
				hidrogenio = elemento;
			} else if (elemento.getSigla().equals(EnumSiglaElemento.O)) {
				oxigenio = elemento;
			}

			if (oxigenio != null && hidrogenio != null) {
				break;
			}

		}

		hidrogenio.setCoeficiente(2);

		Solucao solucao = new Solucao();
		solucao.adicionarElemento(hidrogenio);
		solucao.adicionarElemento(oxigenio);

		return solucao;
	}

	public void testEncontrarMassaMorlarDaAgua() {

		Solucao solucao = this.getSolucaoAgua();

		
		Double valorMassaMolar = CalculoEstequiometrico
				.encontraValorDaMassaMolar(solucao);

		Double valorEsperado = 18.01528;

		Assert.assertEquals(valorEsperado, valorMassaMolar);
	}

	public void testConverterMolEmMassaMolarAgua() {

		
		Solucao agua = this.getSolucaoAgua();

		Double valorMassaMolar = CalculoEstequiometrico.converteGrandeza(
				EnumGrandezaQuimica.MOL, (double) 2,
				EnumGrandezaQuimica.MASSA_MOLAR, agua);

		Double massaEsperada = 36.03056;

		Assert.assertEquals(massaEsperada, valorMassaMolar);
	}

	public void testConverterMassaMolarEmMolAgua() {

		Solucao agua = this.getSolucaoAgua();

		Double valorMol = CalculoEstequiometrico.converteGrandeza(
				EnumGrandezaQuimica.MASSA_MOLAR, (double) 66.01348,
				EnumGrandezaQuimica.MOL, agua);

		Double molEsperado = 2.0;

		Assert.assertEquals(molEsperado, valorMol);
	}
	
	public void testConverterVolumeEmMol() {

		
		Double valorMol = CalculoEstequiometrico.converteGrandeza(EnumGrandezaQuimica.VOLUME, (double) 156.8 ,EnumGrandezaQuimica.MOL, null);

		Double molEsperado = 7.0;

		Assert.assertEquals(molEsperado, valorMol);
	}
}
