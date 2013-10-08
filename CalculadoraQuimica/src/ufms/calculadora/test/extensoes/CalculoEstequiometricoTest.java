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

		oxigenio.setCoeficiente(2);

		Solucao solucao = new Solucao();
		solucao.adicionarElemento(hidrogenio);
		solucao.adicionarElemento(oxigenio);

		return solucao;
	}

	public void testEncontrarMassaMorlarDaAgua() {

		Solucao solucao = this.getSolucaoAgua();

		CalculoEstequiometrico calculoEstequiometrico = new CalculoEstequiometrico();

		Double valorMassaMolar = calculoEstequiometrico
				.encontraValorDaMassaMolar(solucao);

		Double valorEsperado = 33.00674;

		Assert.assertEquals(valorEsperado, valorMassaMolar);
	}

	public void testConverterMolEmMassaMolarAgua() {

		CalculoEstequiometrico calculoEstequiometrico = new CalculoEstequiometrico();

		Solucao agua = this.getSolucaoAgua();

		Double valorMassaMolar = calculoEstequiometrico.converteGrandeza(
				EnumGrandezaQuimica.MOL, (double) 2,
				EnumGrandezaQuimica.MASSA_MOLAR, agua);

		Double massaEsperada = 66.01348;

		Assert.assertEquals(massaEsperada, valorMassaMolar);
	}

	public void testConverterMassaMolarEmMolAgua() {

		CalculoEstequiometrico calculoEstequiometrico = new CalculoEstequiometrico();

		Solucao agua = this.getSolucaoAgua();

		Double valorMol = calculoEstequiometrico.converteGrandeza(
				EnumGrandezaQuimica.MASSA_MOLAR, (double) 66.01348,
				EnumGrandezaQuimica.MOL, agua);

		Double molEsperado = 2.0;

		Assert.assertEquals(molEsperado, valorMol);
	}
	
	public void testConverterVolumeEmMol() {

		CalculoEstequiometrico calculoEstequiometrico = new CalculoEstequiometrico();

		Double valorMol = calculoEstequiometrico.converteGrandeza(EnumGrandezaQuimica.VOLUME, (double) 156.8 ,EnumGrandezaQuimica.MOL, null);

		Double molEsperado = 7.0;

		Assert.assertEquals(molEsperado, valorMol);
	}
}
