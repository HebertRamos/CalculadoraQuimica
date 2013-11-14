package com.example.chemistrycalculator;

import java.util.List;

import ufms.calculadora.extensoes.recursos.TabelaPeriodica;
import ufms.calculadora.modelo.Elemento;
import ufms.calculadora.modelo.EnumSiglaElemento;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

/**
 * Classe que controla a tela da tabela periódica.
 * 
 * @author Angelo Maggioni
 * @author Hebert Ramos
 *
 */
public class TabelaPeriodicaActivity extends Activity {

	Elemento elementoSelecionado;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("Tabela Periódica");
		setContentView(R.layout.activity_tabela_periodica);
		carregaTabelaPeriodica();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tabela_periodica, menu);

		return true;
	}

	public void carregaTabelaPeriodica() {

		List<Elemento> elementos = TabelaPeriodica.getElementos();
		
		if(elementos == null) {
			return;
		}

		for (Elemento elemento : elementos) {

			Integer resourceId = getResources().getIdentifier( "elemento" + elemento.getSigla().name(), "id", this.getBaseContext().getPackageName());

			if (resourceId != 0) {
				
				TextView botaoElemento = (TextView) findViewById(resourceId);
				
				if(botaoElemento != null) {

					botaoElemento.setText(elemento.getSigla().name());
					botaoElemento.setOnClickListener(new View.OnClickListener() {
	
						@Override
						public void onClick(View viewAtual) {
	
							Intent data = new Intent();
	
							String siglaString = ((TextView) viewAtual).getText().toString();
							Elemento elementoSelecionado = TabelaPeriodica.getElemento(EnumSiglaElemento.valueOf(siglaString));
	
							data.putExtra("elementoSelecionado", elementoSelecionado);
							setResult(RESULT_OK, data);
							finish();
	
						}
					});
				}

			}
		}

	}

}