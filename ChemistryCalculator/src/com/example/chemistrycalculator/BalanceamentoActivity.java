package com.example.chemistrycalculator;

import ufms.calculadora.extensoes.calculoBalanceamento.CalculoBalanceamento;
import ufms.calculadora.extensoes.calculoBalanceamento.MetodoAlgebrico;
import ufms.calculadora.modelo.EquacaoQuimica;
import ufms.calculadora.modelo.Solucao;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

		Intent i = new Intent(this, AdicionarSolucaoActicity.class);
		startActivityForResult(i, reagente);

	}

	public void onClickAddProduto(View view) {

		Intent i = new Intent(this, AdicionarSolucaoActicity.class);
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
			Solucao solucao = (Solucao) data.getExtras().getSerializable(
					"solucaoAtual");
			System.out.println(solucao.getElementos());
			if (requestCode == reagente) {
				if (!solucao.equals("")) {
					equacaoQuimica.adicionarReagente(solucao);
					if (inputReagentes.getText().length() != 0) {
						inputReagentes.append(" + ");
					}
					for (int i = 0; i < solucao.getElementos().size(); i++) {
						inputReagentes.append(solucao.getElementos().get(i)
								.getIndice().toString()
								+ "(");
						inputReagentes.append(solucao.getElementos().get(i)
								.getSigla().toString());
						inputReagentes.append(") "
								+ solucao.getElementos().get(i)
										.getCoeficiente().toString());
					}
					System.out.println("Equacao Reagente modificada");
				}
			} else if ((requestCode == produto)) {
				if (!solucao.equals("")) {
					equacaoQuimica.adicionarProduto(solucao);
					if (inputProdutos.getText().length() != 0) {
						inputProdutos.append(" + ");
					}
					for (int i = 0; i < solucao.getElementos().size(); i++) {
						inputProdutos.append(solucao.getElementos().get(i)
								.getIndice().toString()
								+ "(");
						inputProdutos.append(solucao.getElementos().get(i)
								.getSigla().toString());
						inputProdutos.append(") "
								+ solucao.getElementos().get(i)
										.getCoeficiente().toString());
					}
					System.out.println("Equacao Produto montada");
				}
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.balanceamento, menu);
		return true;
	}
}
