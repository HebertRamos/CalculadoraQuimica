package ufms.calculadora.test.extensoes;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

import ufms.calculadora.extensoes.calculadoraAlgebrica.OperacaoNaoSuportadaException;
import ufms.calculadora.extensoes.calculoBalanceamento.CalculoBalanceamento;
import ufms.calculadora.extensoes.calculoBalanceamento.MetodoAlgebrico;
import ufms.calculadora.modelo.Elemento;
import ufms.calculadora.modelo.EnumSiglaElemento;
import ufms.calculadora.modelo.EquacaoQuimica;
import ufms.calculadora.modelo.Solucao;

public class MetodoAlgebricoTest extends TestCase {
	
	public void testDadoUmaQuacaoQuimicaDaAguaRetornaAoCarregarAsSiglasDosElementosDeveRetornar_H_O() {

		// REAGENTES
		Elemento oxigenioRT = new Elemento();
		oxigenioRT.setSigla(EnumSiglaElemento.O);

		Solucao reagente1T = new Solucao();
		reagente1T.adicionarElemento(oxigenioRT);

		Elemento hidrogenioRT = new Elemento();
		hidrogenioRT.setSigla(EnumSiglaElemento.H);
		hidrogenioRT.setCoeficiente(2);

		Solucao reagente2T = new Solucao();
		reagente2T.adicionarElemento(hidrogenioRT);

		// PRODUTO
		Elemento oxigenioPT = new Elemento();
		oxigenioPT.setSigla(EnumSiglaElemento.O);

		Elemento hidrogenioPT = new Elemento();
		hidrogenioPT.setSigla(EnumSiglaElemento.H);
		hidrogenioPT.setCoeficiente(2);

		Solucao produtoT = new Solucao();
		produtoT.adicionarElemento(oxigenioPT);
		produtoT.adicionarElemento(hidrogenioPT);

		EquacaoQuimica equacaoQuimicaTest = new EquacaoQuimica();
		equacaoQuimicaTest.adicionarReagente(reagente1T);
		equacaoQuimicaTest.adicionarReagente(reagente2T);
		equacaoQuimicaTest.adicionarProduto(produtoT);

		CalculoBalanceamento calculoBalanceamento = new MetodoAlgebrico();

		List<EnumSiglaElemento> listaResultado = calculoBalanceamento.carregaSiglasElementos(equacaoQuimicaTest.getReagentes());
		List<EnumSiglaElemento> listaEsperada = Arrays.asList(new EnumSiglaElemento[] { EnumSiglaElemento.O, EnumSiglaElemento.H });

		Assert.assertEquals(listaEsperada, listaResultado);

	}

	@Test
	public void testDadoUmaEquacaoComUmaSolucaoDeH2MaisUmaDeOxigenioEGeraUmaSolucaoH2OAoBalancearDeveRetornarUmaEquacaoComUmaSolucaoDeH2MaisUmaDeOxigenioEGeraUmaSolucaoH2O() {

		/** Equação Test **/
		// REAGENTES
		Elemento oxigenioRT = new Elemento();
		oxigenioRT.setSigla(EnumSiglaElemento.O);

		Solucao reagente1T = new Solucao();
		reagente1T.adicionarElemento(oxigenioRT);

		Elemento hidrogenioRT = new Elemento();
		hidrogenioRT.setSigla(EnumSiglaElemento.H);
		hidrogenioRT.setCoeficiente(2);

		Solucao reagente2T = new Solucao();
		reagente2T.adicionarElemento(hidrogenioRT);

		// PRODUTO
		Elemento oxigenioPT = new Elemento();
		oxigenioPT.setSigla(EnumSiglaElemento.O);

		Elemento hidrogenioPT = new Elemento();
		hidrogenioPT.setSigla(EnumSiglaElemento.H);
		hidrogenioPT.setCoeficiente(2);

		Solucao produtoT = new Solucao();
		produtoT.adicionarElemento(oxigenioPT);
		produtoT.adicionarElemento(hidrogenioPT);

		EquacaoQuimica equacaoQuimicaTest = new EquacaoQuimica();
		equacaoQuimicaTest.adicionarReagente(reagente1T);
		equacaoQuimicaTest.adicionarReagente(reagente2T);
		equacaoQuimicaTest.adicionarProduto(produtoT);

		/** Equação Esperada **/
		// REAGENTES
		Elemento oxigenioRE = new Elemento();
		oxigenioRE.setSigla(EnumSiglaElemento.O);

		Solucao reagente1E = new Solucao();
		reagente1E.adicionarElemento(oxigenioRE);

		Elemento hidrogenioRE = new Elemento();
		hidrogenioRE.setSigla(EnumSiglaElemento.H);
		hidrogenioRE.setCoeficiente(2);

		Solucao reagente2E = new Solucao();
		reagente2E.adicionarElemento(hidrogenioRE);

		// PRODUTO
		Elemento oxigenioPE = new Elemento();
		oxigenioPE.setSigla(EnumSiglaElemento.O);

		Elemento hidrogenioPE = new Elemento();
		hidrogenioPE.setSigla(EnumSiglaElemento.H);
		hidrogenioPE.setCoeficiente(2);

		Solucao produtoE = new Solucao();
		produtoE.adicionarElemento(oxigenioPE);
		produtoE.adicionarElemento(hidrogenioPE);

		EquacaoQuimica equacaoEsperada = new EquacaoQuimica();
		equacaoEsperada.adicionarReagente(reagente1E);
		equacaoEsperada.adicionarReagente(reagente2E);
		equacaoEsperada.adicionarProduto(produtoE);

		CalculoBalanceamento calculoBalanceamento = new MetodoAlgebrico();
		EquacaoQuimica equacaoBalanceada;
		try {
			equacaoBalanceada = calculoBalanceamento.balancearEquacao(equacaoQuimicaTest);

		} catch (OperacaoNaoSuportadaException e) {
			Assert.fail(e.getMessage());
			return;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Assert.fail(e.getMessage());
			return;
		}

		Assert.assertEquals(true, equacaoBalanceada.equals(equacaoEsperada));

	}

	@Test
	public void testDadoUmaQuacaoBalancear1() {

		/** Equação Test **/
		// REAGENTES
		Elemento nitrogenioTR1 = new Elemento();
		nitrogenioTR1.setSigla(EnumSiglaElemento.N);

		Elemento hidrogenioTR1 = new Elemento();
		hidrogenioTR1.setSigla(EnumSiglaElemento.H);
		hidrogenioTR1.setCoeficiente(4);

		Elemento nitrogenioTR2 = new Elemento();
		nitrogenioTR2.setSigla(EnumSiglaElemento.N);

		Elemento oxigenioTR1 = new Elemento();
		oxigenioTR1.setSigla(EnumSiglaElemento.O);
		oxigenioTR1.setCoeficiente(3);

		Solucao reagenteT1 = new Solucao();
		reagenteT1.adicionarElemento(nitrogenioTR1);
		reagenteT1.adicionarElemento(hidrogenioTR1);
		reagenteT1.adicionarElemento(nitrogenioTR2);
		reagenteT1.adicionarElemento(oxigenioTR1);

		// PRODUTO
		Elemento nitrogenioTP1 = new Elemento();
		nitrogenioTP1.setSigla(EnumSiglaElemento.N);
		nitrogenioTP1.setCoeficiente(2);

		Elemento oxigenioTP1 = new Elemento();
		oxigenioTP1.setSigla(EnumSiglaElemento.O);

		Solucao produtoT1 = new Solucao();
		produtoT1.adicionarElemento(nitrogenioTP1);
		produtoT1.adicionarElemento(oxigenioTP1);

		Elemento hidrogenioTP1 = new Elemento();
		hidrogenioTP1.setSigla(EnumSiglaElemento.H);
		hidrogenioTP1.setCoeficiente(2);

		Elemento oxigenioTP2 = new Elemento();
		oxigenioTP2.setSigla(EnumSiglaElemento.O);

		Solucao produtoT2 = new Solucao();
		produtoT2.adicionarElemento(hidrogenioTP1);
		produtoT2.adicionarElemento(oxigenioTP2);

		EquacaoQuimica equacaoQuimicaTest = new EquacaoQuimica();
		equacaoQuimicaTest.adicionarReagente(reagenteT1);
		equacaoQuimicaTest.adicionarProduto(produtoT1);
		equacaoQuimicaTest.adicionarProduto(produtoT2);

		/** Equação Esperada **/
		// REAGENTES
		Elemento nitrogenioER1 = new Elemento();
		nitrogenioER1.setSigla(EnumSiglaElemento.N);

		Elemento hidrogenioER1 = new Elemento();
		hidrogenioER1.setSigla(EnumSiglaElemento.H);
		hidrogenioER1.setCoeficiente(4);

		Elemento nitrogenioER2 = new Elemento();
		nitrogenioER2.setSigla(EnumSiglaElemento.N);

		Elemento oxigenioER1 = new Elemento();
		oxigenioER1.setSigla(EnumSiglaElemento.O);
		oxigenioER1.setCoeficiente(3);

		Solucao reagenteE1 = new Solucao();
		reagenteE1.adicionarElemento(nitrogenioER1);
		reagenteE1.adicionarElemento(hidrogenioER1);
		reagenteE1.adicionarElemento(nitrogenioER2);
		reagenteE1.adicionarElemento(oxigenioER1);

		// PRODUTO
		Elemento nitrogenioEP1 = new Elemento();
		nitrogenioEP1.setSigla(EnumSiglaElemento.N);
		nitrogenioEP1.setCoeficiente(2);

		Elemento oxigenioEP1 = new Elemento();
		oxigenioEP1.setSigla(EnumSiglaElemento.O);

		Solucao produtoE1 = new Solucao();
		produtoE1.adicionarElemento(nitrogenioEP1);
		produtoE1.adicionarElemento(oxigenioEP1);

		Elemento hidrogenioEP1 = new Elemento();
		hidrogenioEP1.setSigla(EnumSiglaElemento.H);
		hidrogenioEP1.setCoeficiente(2);

		Elemento oxigenioEP2 = new Elemento();
		oxigenioEP2.setSigla(EnumSiglaElemento.O);

		Solucao produtoE2 = new Solucao();
		produtoE2.adicionarElemento(hidrogenioEP1);
		produtoE2.adicionarElemento(oxigenioEP2);
		produtoE2.setIndice(2);

		EquacaoQuimica equacaoEsperada = new EquacaoQuimica();
		equacaoEsperada.adicionarReagente(reagenteE1);
		equacaoEsperada.adicionarProduto(produtoE1);
		equacaoEsperada.adicionarProduto(produtoE2);

		CalculoBalanceamento calculoBalanceamento = new MetodoAlgebrico();
		EquacaoQuimica equacaoBalanceada;
		try {
			equacaoBalanceada = calculoBalanceamento.balancearEquacao(equacaoQuimicaTest);
		} catch (OperacaoNaoSuportadaException e) {
			Assert.fail(e.getMessage());
			return;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Assert.fail(e.getMessage());
			return;
		}

		Assert.assertEquals(true, equacaoBalanceada.equals(equacaoEsperada));
		
		System.out.println(equacaoBalanceada);

	}

	@Test
	public void testDadoUmaQuacaoBalancear2() {

		/** Equação Test **/
		// REAGENTES
		Elemento ferroTR1 = new Elemento();
		ferroTR1.setSigla(EnumSiglaElemento.Fe);
		ferroTR1.setCoeficiente(3);

		Elemento oxigenioTR1 = new Elemento();
		oxigenioTR1.setSigla(EnumSiglaElemento.O);
		oxigenioTR1.setCoeficiente(4);

		Solucao reagenteT1 = new Solucao();
		reagenteT1.adicionarElemento(ferroTR1);
		reagenteT1.adicionarElemento(oxigenioTR1);

		Elemento carbonoTR1 = new Elemento();
		carbonoTR1.setSigla(EnumSiglaElemento.C);

		Elemento oxigenioTR2 = new Elemento();
		oxigenioTR2.setSigla(EnumSiglaElemento.O);

		Solucao reagenteT2 = new Solucao();
		reagenteT2.adicionarElemento(carbonoTR1);
		reagenteT2.adicionarElemento(oxigenioTR2);

		// PRODUTO
		Elemento ferroTP1 = new Elemento();
		ferroTP1.setSigla(EnumSiglaElemento.Fe);

		Elemento oxigenioTP1 = new Elemento();
		oxigenioTP1.setSigla(EnumSiglaElemento.O);

		Solucao produtoT1 = new Solucao();
		produtoT1.adicionarElemento(ferroTP1);
		produtoT1.adicionarElemento(oxigenioTP1);

		Elemento carbonoTP1 = new Elemento();
		carbonoTP1.setSigla(EnumSiglaElemento.C);

		Elemento oxigenioTP2 = new Elemento();
		oxigenioTP2.setSigla(EnumSiglaElemento.O);
		oxigenioTP2.setCoeficiente(2);

		Solucao produtoT2 = new Solucao();
		produtoT2.adicionarElemento(carbonoTP1);
		produtoT2.adicionarElemento(oxigenioTP2);

		EquacaoQuimica equacaoQuimicaTest = new EquacaoQuimica();
		equacaoQuimicaTest.adicionarReagente(reagenteT1);
		equacaoQuimicaTest.adicionarReagente(reagenteT2);
		equacaoQuimicaTest.adicionarProduto(produtoT1);
		equacaoQuimicaTest.adicionarProduto(produtoT2);

		/** Equação Esperada **/
		// REAGENTES
		Elemento ferroER1 = new Elemento();
		ferroER1.setSigla(EnumSiglaElemento.Fe);
		ferroER1.setCoeficiente(3);

		Elemento oxigenioER1 = new Elemento();
		oxigenioER1.setSigla(EnumSiglaElemento.O);
		oxigenioER1.setCoeficiente(4);

		Solucao reagenteE1 = new Solucao();
		reagenteE1.adicionarElemento(ferroER1);
		reagenteE1.adicionarElemento(oxigenioER1);

		Elemento carbonoER1 = new Elemento();
		carbonoER1.setSigla(EnumSiglaElemento.C);

		Elemento oxigenioER2 = new Elemento();
		oxigenioER2.setSigla(EnumSiglaElemento.O);

		Solucao reagenteE2 = new Solucao();
		reagenteE2.adicionarElemento(carbonoER1);
		reagenteE2.adicionarElemento(oxigenioER2);

		// PRODUTO
		Elemento ferroEP1 = new Elemento();
		ferroEP1.setSigla(EnumSiglaElemento.Fe);

		Elemento oxigenioEP1 = new Elemento();
		oxigenioEP1.setSigla(EnumSiglaElemento.O);

		Solucao produtoE1 = new Solucao();
		produtoE1.adicionarElemento(ferroEP1);
		produtoE1.adicionarElemento(oxigenioEP1);
		produtoE1.setIndice(3);

		Elemento carbonoEP1 = new Elemento();
		carbonoEP1.setSigla(EnumSiglaElemento.C);

		Elemento oxigenioEP2 = new Elemento();
		oxigenioEP2.setSigla(EnumSiglaElemento.O);
		oxigenioEP2.setCoeficiente(2);

		Solucao produtoE2 = new Solucao();
		produtoE2.adicionarElemento(carbonoEP1);
		produtoE2.adicionarElemento(oxigenioEP2);

		EquacaoQuimica equacaoEsperada = new EquacaoQuimica();
		equacaoEsperada.adicionarReagente(reagenteE1);
		equacaoEsperada.adicionarReagente(reagenteE2);
		equacaoEsperada.adicionarProduto(produtoE1);
		equacaoEsperada.adicionarProduto(produtoE2);

		CalculoBalanceamento calculoBalanceamento = new MetodoAlgebrico();
		EquacaoQuimica equacaoBalanceada;
		try {
			equacaoBalanceada = calculoBalanceamento.balancearEquacao(equacaoQuimicaTest);
		} catch (OperacaoNaoSuportadaException e) {
			Assert.fail(e.getMessage());
			return;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Assert.fail(e.getMessage());
			return;
		}

		Assert.assertEquals(true, equacaoBalanceada.equals(equacaoEsperada));
	}
	
	@Test
	public void testDadoUmaQuacaoBalancear3() {
		
		/** Equação Esperada **/
		// REAGENTES
		Elemento carbonoER1 = new Elemento(EnumSiglaElemento.C);
		Solucao reagenteE1 = new Solucao();
		reagenteE1.adicionarElemento(carbonoER1);

		

		// PRODUTO
		Elemento carbonoEP1 = new Elemento(EnumSiglaElemento.C);
		Solucao produtoE1 = new Solucao();
		produtoE1.adicionarElemento(carbonoEP1);


		EquacaoQuimica equacao = new EquacaoQuimica();
		equacao.adicionarReagente(reagenteE1);
		equacao.adicionarProduto(produtoE1);
		
		System.out.println(equacao.toString());
		
	
	}
	
	@Test
	public void testDadoUmaQuacaoBalancear4() {
		
		
		/** Equação Test **/
		// REAGENTES
		Elemento sodioTR1 = new Elemento(EnumSiglaElemento.Na);
		sodioTR1.setCoeficiente(2);
		
		Elemento carbonoTR1 = new Elemento(EnumSiglaElemento.C);
		
		Elemento oxigenioTR1 = new Elemento(EnumSiglaElemento.O);
		oxigenioTR1.setCoeficiente(3);
		
		Solucao reagenteT1 = new Solucao();
		reagenteT1.adicionarElemento(sodioTR1);
		reagenteT1.adicionarElemento(carbonoTR1);
		reagenteT1.adicionarElemento(oxigenioTR1);
		
		Elemento hidrogenioTR1 = new Elemento(EnumSiglaElemento.H);
		Elemento cloroTR1 = new Elemento(EnumSiglaElemento.Cl);
		
		Solucao reagenteT2 = new Solucao();
		reagenteT2.adicionarElemento(hidrogenioTR1);
		reagenteT2.adicionarElemento(cloroTR1);
		
		// PRODUTO
		Elemento sodioTP1 = new Elemento(EnumSiglaElemento.Na);
		Elemento cloroTP1 = new Elemento(EnumSiglaElemento.Cl);
		
		Solucao produtoT1 = new Solucao();
		produtoT1.adicionarElemento(sodioTP1);
		produtoT1.adicionarElemento(cloroTP1);
		
		Elemento hidrogenioTP1 = new Elemento(EnumSiglaElemento.H);
		hidrogenioTP1.setCoeficiente(2);
		
		Elemento oxigenioTP1 = new Elemento(EnumSiglaElemento.O);
		
		Solucao produtoT2 = new Solucao();
		produtoT2.adicionarElemento(hidrogenioTP1);
		produtoT2.adicionarElemento(oxigenioTP1);
		
		Elemento carbonoTP1 = new Elemento(EnumSiglaElemento.C);
		
		Elemento oxigenioTP2 = new Elemento(EnumSiglaElemento.O);
		oxigenioTP2.setCoeficiente(2);
		
		Solucao produtoT3 = new Solucao();
		produtoT3.adicionarElemento(carbonoTP1);
		produtoT3.adicionarElemento(oxigenioTP2);
		
		EquacaoQuimica equacaoQuimicaTest = new EquacaoQuimica();
		equacaoQuimicaTest.adicionarReagente(reagenteT1);
		equacaoQuimicaTest.adicionarReagente(reagenteT2);
		equacaoQuimicaTest.adicionarProduto(produtoT1);
		equacaoQuimicaTest.adicionarProduto(produtoT2);
		equacaoQuimicaTest.adicionarProduto(produtoT3);

		CalculoBalanceamento calculoBalanceamento = new MetodoAlgebrico();
		EquacaoQuimica equacaoBalanceada;
		try {
			equacaoBalanceada = calculoBalanceamento.balancearEquacao(equacaoQuimicaTest);

		} catch (OperacaoNaoSuportadaException e) {
			Assert.fail(e.getMessage());
			return;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Assert.fail(e.getMessage());
			return;
		}
		
		
		String equacaoEsperadaString = "1(Na2)(C1)(O3) + 2(H1)(Cl1) => 2(Na1)(Cl1) + 1(H2)(O1) + 1(C1)(O2)";
		
		Assert.assertEquals(equacaoEsperadaString, equacaoBalanceada.toString());	
		
	}

}
