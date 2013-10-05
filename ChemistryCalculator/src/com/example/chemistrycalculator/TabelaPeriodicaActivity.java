package com.example.chemistrycalculator;

import java.util.List;

import ufms.calculadora.modelo.Elemento;
import ufms.calculadora.modelo.EnumSiglaElemento;
import ufms.calculadora.negocio.TabelaPeriodica;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class TabelaPeriodicaActivity extends Activity {
	
	Elemento elementoSelecionado;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tabela_periodica);
		carregaTabelaPeriodica();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tabela_periodica, menu);
		
		return true;
	}
	
	
	public void carregaTabelaPeriodica(){
		
		List<Elemento> elementos = TabelaPeriodica.getElementos();
		
		
		for (Elemento elemento : elementos) {
			
			Integer resourceId = getResources().getIdentifier("elemento"+elemento.getSigla().name() ,"id", this.getBaseContext().getPackageName());
			
			if(resourceId !=  0){
				Button botaoElemento = (Button) findViewById(resourceId);
				
				botaoElemento.setText(elemento.getSigla().name());
				botaoElemento.setOnClickListener(new View.OnClickListener() {
		        	 
		        	@Override
		        	public void onClick(View viewAtual) {
		        	 
		        		Intent data = new Intent();
		        		
		        		Elemento elementoSelecionado = new Elemento();
		        		String siglaString = ((Button)viewAtual).getText().toString();
		        		elementoSelecionado.setSigla(EnumSiglaElemento.valueOf(siglaString));
		        		
		        		data.putExtra("elementoSelecionado", elementoSelecionado);
		        		setResult(RESULT_OK, data);
		        		finish();
		        		
		        	}
				});
				
			}
		}
		 
	}
	
}