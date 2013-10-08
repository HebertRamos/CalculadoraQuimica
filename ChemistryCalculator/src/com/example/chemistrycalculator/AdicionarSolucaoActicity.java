package com.example.chemistrycalculator;

import ufms.calculadora.modelo.Elemento;
import ufms.calculadora.modelo.Solucao;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class AdicionarSolucaoActicity extends Activity {

	private Solucao solucaoAtual;
	EditText inputCoeficiente;
	EditText inputIndice;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_adicionar_solucao);
		solucaoAtual = new Solucao();
		inputIndice = (EditText) findViewById(R.id.IndiceSolucao1);
		inputCoeficiente = (EditText) findViewById(R.id.CoeficienteSolucao);
	}

	public void onClick(View view) {
		finish();
	}

	@Override
	public void finish() {
		Intent data = new Intent();

		Integer indice = 1;
		Integer coeficiente = 1;
		if (!(inputIndice.getText().toString().equals(""))) {
			indice = Integer.parseInt(inputIndice.getText()
					.toString());
		}
		if (!(inputCoeficiente.getText().toString().equals(""))) {
			coeficiente = Integer.parseInt(inputCoeficiente
					.getText().toString());
		}
		
		solucaoAtual.setIndice(indice);
		solucaoAtual.setCoeficiente(coeficiente);

		data.putExtra("solucaoAtual", solucaoAtual);
		setResult(RESULT_OK, data);
		super.finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.adicionar_produto, menu);
		return true;
	}

	public void adicionarElemento(View v) {
		Intent trocatela = new Intent(this, TabelaPeriodicaActivity.class);
		startActivityForResult(trocatela, 100);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if ((resultCode == RESULT_OK)) {
			Elemento elementoSelecionado = (Elemento) data.getExtras()
					.getSerializable("elementoSelecionado");

			EditText inputText = (EditText) findViewById(R.id.inputSolucao1);
			if (inputText.getText().length() != 0) {
				// inputText.append(" + ");
			}
			inputText.append(elementoSelecionado.getIndice().toString() + "(");
			inputText.append(elementoSelecionado.getSigla().name());
			inputText.append(")"
					+ elementoSelecionado.getCoeficiente().toString());
			
			solucaoAtual.adicionarElemento(elementoSelecionado);

		}
	}

}
