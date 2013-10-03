package com.example.chemistrycalculator;

import ufms.calculadora.modelo.Elemento;
import ufms.calculadora.modelo.Solucao;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class AdicionarProdutoActicity extends Activity {
	
	
	private Solucao solucaoAtual;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_adicionar_produto);
		solucaoAtual = new Solucao();
	}

	public void onClick(View view) {
		finish();
	}

	@Override
	public void finish() {
		Intent data = new Intent();

		EditText inputText = (EditText) findViewById(R.id.inputSolucao);
		String returnString = inputText.getText().toString();
		data.putExtra("solucao", returnString);
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
		Intent trocatela = new Intent(AdicionarProdutoActicity.this, TabelaPeriodicaActivity.class);
		AdicionarProdutoActicity.this.startActivity(trocatela);
		startActivityForResult(trocatela, 100);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if ((resultCode == RESULT_OK)) {
			Elemento elementoSelecionado = (Elemento) data.getExtras().getSerializable("elementoSelecionado");
			solucaoAtual.adicionarElemento(elementoSelecionado);
			
			EditText inputText = (EditText) findViewById(R.id.inputSolucao);
			if(inputText.getText().length() != 0){
				inputText.append(" + ");
			}
			
			inputText.append(elementoSelecionado.getSigla().name());
			
		}
	}

}
