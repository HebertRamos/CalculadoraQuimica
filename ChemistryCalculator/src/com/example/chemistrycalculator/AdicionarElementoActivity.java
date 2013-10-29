package com.example.chemistrycalculator;

import ufms.calculadora.modelo.Elemento;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class AdicionarElementoActivity extends Activity {

	
	private Elemento elementoAtual;
	
	EditText inputElemento;
	EditText inputCoeficiente;
	EditText inputIndice;
	
	Intent telaTabelaPeriodica;
	
	ImageButton botaoOk;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_adicionar_elemento);
		
		inputIndice = (EditText) findViewById(R.id.editTextIndiceElemento);
		inputCoeficiente = (EditText) findViewById(R.id.EditTextCoeficienteElemento);
		inputElemento = (EditText) findViewById(R.id.editTextElemento);
		
		telaTabelaPeriodica = new Intent(this, TabelaPeriodicaActivity.class);
		startActivityForResult(telaTabelaPeriodica,1);
		
		botaoOk = (ImageButton) findViewById(R.id.imageButtonOkElemento);
		botaoOk.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View viewAtual) {

				if (elementoAtual != null) {
					if (!(inputIndice.getText().toString().equals(""))) {
						Integer indice = Integer.parseInt(inputIndice.getText().toString());
						elementoAtual.setIndice(indice);
					}
					if (!(inputCoeficiente.getText().toString().equals(""))) {
						Integer coeficiente = Integer.parseInt(inputCoeficiente.getText().toString());
						elementoAtual.setCoeficiente(coeficiente);
					}

					AdicionarSolucaoActicity.solucaoAtual.adicionarElemento(elementoAtual);
				}

				finishActivity(1);
				Intent data = new Intent();
				setResult(RESULT_OK, data);
				finish();
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.adicionar_elemento, menu);
		return true;
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if ((resultCode == RESULT_OK)) {
			Elemento elementoSelecionado = (Elemento) data.getExtras().getSerializable("elementoSelecionado");
			
			elementoAtual = new Elemento();
			elementoAtual.setCoeficiente(elementoSelecionado.getCoeficiente());
			elementoAtual.setIndice(elementoSelecionado.getIndice());
			elementoAtual.setSigla(elementoSelecionado.getSigla());
			
			
			inputElemento.setText(elementoAtual.getSigla().name());
		}
	}
	
	
	
	

}
