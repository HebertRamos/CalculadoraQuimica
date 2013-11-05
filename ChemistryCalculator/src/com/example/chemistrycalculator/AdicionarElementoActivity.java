package com.example.chemistrycalculator;

import ufms.calculadora.modelo.Elemento;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * 
 * @author Agenlo Maggioni
 * @author Hebert Ramos 
 * 
 *  Classe responsável por gerenciar o layout activity_adicionar_elemento.xml.
 *  
 */
public class AdicionarElementoActivity extends Activity {

	
	private Elemento elementoAtual;
	
	EditText inputElemento;
	EditText inputCoeficiente;
	EditText inputIndice;
	
	Intent telaTabelaPeriodica;
	
	Button botaoOk;
	
	@Override
	/**
	 * Invoca a tela Tabela Periodica.
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_adicionar_elemento);
		
		inputIndice = (EditText) findViewById(R.id.editTextCoeficienteElemento);
		inputCoeficiente = (EditText) findViewById(R.id.editTextIndiceElemento);
		inputElemento = (EditText) findViewById(R.id.editTextElemento);
		
		telaTabelaPeriodica = new Intent(this, TabelaPeriodicaActivity.class);
		startActivityForResult(telaTabelaPeriodica,1);
		
		botaoOk = (Button) findViewById(R.id.imageButtonOkElemento);
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
					
				}

				finishActivity(1);
				Intent data = new Intent();
				data.putExtra("elementoAtual", elementoAtual);
				setResult(RESULT_OK, data);
				finish();
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.adicionar_elemento, menu);
		return true;
	}
	
	/**
	 * Capura o retorno da tela Tabela Periodica e seta o atribuito elementoAtual
	 */
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
