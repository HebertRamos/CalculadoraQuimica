package com.example.chemistrycalculator;

import ufms.calculadora.modelo.Elemento;
import ufms.calculadora.modelo.Solucao;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AdicionarSolucaoActicity extends Activity {
	
	
	public static String TITULO_SINGULAR;
	public static String TITULO_PLURAL;
	
	private Solucao solucaoAtual;
	
	EditText inputsolucao;
	EditText inputCoeficiente;
	EditText inputIndice;
	
	TextView tituloCampoSolucao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_adicionar_solucao);
		this.setTitle("Adicionar "+TITULO_PLURAL);
		
		solucaoAtual = new Solucao();
		
		inputIndice = (EditText) findViewById(R.id.editTextIndiceSolucao);
		inputCoeficiente = (EditText) findViewById(R.id.editTextCoeficienteSolucao);
		inputsolucao = (EditText) findViewById(R.id.editTextSolucao_);
		
		tituloCampoSolucao = (TextView) findViewById(R.id.textViewSolucao);
		tituloCampoSolucao.setText(TITULO_SINGULAR);
	}

	public void adicionarElemento(View v) {
		Intent telaAdicionarElemento = new Intent(this, AdicionarElementoActivity.class);
		startActivityForResult(telaAdicionarElemento, 0);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if(resultCode == RESULT_OK){
			Elemento elementoAtual = (Elemento) data.getExtras().getSerializable("elementoAtual");
			solucaoAtual.adicionarElemento(elementoAtual);
			inputsolucao.setText(Html.fromHtml(solucaoAtual.toString()));
		}
	}
	
	public void acionaBotaoOk(View view) {
		
		if(solucaoAtual != null){
			if (!(inputCoeficiente.getText().toString().equals(""))) {
				Integer coeficiente = Integer.parseInt(inputCoeficiente.getText().toString());
				solucaoAtual.setIndice(coeficiente);
			}
			if (!(inputIndice.getText().toString().equals(""))) {
				Integer indice = Integer.parseInt(inputIndice.getText().toString());
				solucaoAtual.setCoeficiente(indice);
			}
			
		}

		Intent data = new Intent();
		data.putExtra("solucaoAtual", solucaoAtual);
		setResult(RESULT_OK, data);
		finish();
	}
	
	
}
