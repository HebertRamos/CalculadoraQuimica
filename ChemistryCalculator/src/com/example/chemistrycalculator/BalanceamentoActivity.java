package com.example.chemistrycalculator;

import ufms.calculadora.extensoes.calculoBalanceamento.CalculoBalanceamento;
import ufms.calculadora.extensoes.calculoBalanceamento.MetodoAlgebrico;
import ufms.calculadora.modelo.Elemento;
import ufms.calculadora.modelo.EnumSiglaElemento;
import ufms.calculadora.modelo.EquacaoQuimica;
import ufms.calculadora.modelo.Solucao;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BalanceamentoActivity extends Activity {
	private static final int reagente = 5;
	private static final int produto = 4;

	TextView txtResultado;
	EditText inputReagentes;
	EditText inputProdutos;
	Button btBalancear;
	Button btAdicionarReagente;
	Button brAdicionarProduto;
	EquacaoQuimica equacaoQuimica;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_balanceamento);

		inputReagentes = (EditText) findViewById(R.id.inputReagentes);
		inputProdutos = (EditText) findViewById(R.id.inputProdutos);
		btBalancear = (Button) findViewById(R.id.btBalancear);
		txtResultado = (TextView) findViewById(R.id.txtResultado);

		equacaoQuimica = new EquacaoQuimica();
	};

	public void onClickAddReagente(View view) {

		Intent i = new Intent(this, AdicionarProdutoActicity.class);
		startActivityForResult(i, reagente);
		
	}

	public void onClickAddProduto(View view) {

		Intent i = new Intent(this, AdicionarProdutoActicity.class);
		startActivityForResult(i, produto);
	}

	public void onClickBtBalancear(View view) {
		CalculoBalanceamento calculoBalanceamento = new MetodoAlgebrico();
		EquacaoQuimica equacaoBalanceada;
		System.out.println(equacaoQuimica.toString());
		try {

			equacaoBalanceada = calculoBalanceamento
					.balancearEquacao(equacaoQuimica);
			txtResultado.setText(equacaoBalanceada.toString());
		} catch (Exception ex) {
			System.out.println("erro");
		}
		System.out.println("sai!");

	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if ((resultCode == RESULT_OK)) {

//			String solucao = data.getExtras().getString("solucao");
//			if (requestCode == reagente) {
//				if (!solucao.equals("")) {
//					colocaNoInput(solucao, inputReagentes);
//					montaEquacaoReagente(solucao);
//					System.out.println("Equacao Reagente montada");
//				}
//			} else if ((requestCode == produto)) {
//				if (!solucao.equals("")) {
//					colocaNoInput(solucao, inputProdutos);
//					montaEquacaoProduto(solucao);
//					System.out.println("Equacao Produto montada");
//				}
//			}
			
			Elemento elementoSelecionado = (Elemento) data.getExtras().getSerializable("elementoSelecionado");
		}
	}

	private void colocaNoInput(String solucao, EditText input) {
		Editable conteudo = input.getText();
		String result;
		if (conteudo.length() == 0) {
			result = conteudo + solucao;
		} else {
			result = conteudo + "+" + solucao;
		}
		input.setText(result);
	}

	public void montaEquacaoReagente(String text) {
		String elementoTemp = "";
		Integer coeficienteTemp = 0;
		Solucao solucao = new Solucao();
		char[] sol;
		sol = text.toCharArray();
		Elemento elemento = null;
		for (int i = 0; i < sol.length; i++) {
			if (Character.isLetter(sol[i])) {
				if (Character.isUpperCase(sol[i])) {
					elementoTemp = Character.toString(sol[i]);
					if (i + 1 < sol.length) {
						if (Character.isLowerCase(sol[i + 1])) {
							elementoTemp = elementoTemp
									+ Character.toString(sol[i + 1]);
							i++;
						}
					}
				}
				EnumSiglaElemento[] enus = EnumSiglaElemento.values();
				for (EnumSiglaElemento enu : enus) {
					if (enu.name().toUpperCase().equals(elementoTemp)) {

						// o Elemento
						elemento = new Elemento(enu.valueOf(elementoTemp));
						System.out.println("Novo Elemento Encontrado: " + elemento.getSigla());

						// a solucao
						solucao.adicionarElemento(elemento);
						// System.out.println("SOLUCAO: " + solucao.toString());
					}
				}
			} else if (Character.isDigit(sol[i])) {
				coeficienteTemp = Character.getNumericValue(sol[i]);
				System.out.println("Seu coeficiente eh: " + coeficienteTemp);
				elemento.setCoeficiente(coeficienteTemp);
			}
			// o reagente
			equacaoQuimica.adicionarReagente(solucao);
		}
	}

	public void montaEquacaoProduto(String text) {
		String elementoTemp = "";
		Integer coeficienteTemp = 0;
		Solucao solucao = new Solucao();
		char[] sol;
		sol = text.toCharArray();
		Elemento elemento = null;
		for (int i = 0; i < sol.length; i++) {
			if (Character.isLetter(sol[i])) {
				if (Character.isUpperCase(sol[i])) {
					elementoTemp = Character.toString(sol[i]);
					if (i + 1 < sol.length) {
						if (Character.isLowerCase(sol[i + 1])) {
							elementoTemp = elementoTemp
									+ Character.toString(sol[i + 1]);
							i++;
						}
					}
				}
				EnumSiglaElemento[] enus = EnumSiglaElemento.values();
				for (EnumSiglaElemento enu : enus) {
					if (enu.name().toUpperCase().equals(elementoTemp)) {

						elemento = new Elemento(enu.valueOf(elementoTemp));
						System.out.println("Novo Elemento Encontrado: " + elemento.getSigla());
						// a solucao
						solucao.adicionarElemento(elemento);
					}
				}
			} else if (Character.isDigit(sol[i])) {
				coeficienteTemp = Character.getNumericValue(sol[i]);
				System.out.println("Seu coeficiente eh: " + coeficienteTemp);
				elemento.setCoeficiente(coeficienteTemp);
			}
			// o produto
			equacaoQuimica.adicionarProduto(solucao);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.balanceamento, menu);
		return true;
	}
}
