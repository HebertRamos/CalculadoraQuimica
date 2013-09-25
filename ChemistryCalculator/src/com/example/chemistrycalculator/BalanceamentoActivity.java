package com.example.chemistrycalculator;

import ufms.calculadora.extensoes.calculoBalanceamento.CalculoBalanceamento;
import ufms.calculadora.extensoes.calculoBalanceamento.MetodoAlgebrico;
import ufms.calculadora.test.extensoes.MetodoAlgebricoTest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BalanceamentoActivity extends Activity {
	TextView txtResultado;
	Button btBalancear;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// setando o layout atual?
		setContentView(R.layout.activity_balanceamento);

		// capturado o click do botão
		// Button btBalancear = (Button) findViewById(R.id.btBalancear);
		// btBalancear.setOnClickListener(btBalancearClickListener);
		txtResultado = (TextView) findViewById(R.id.txtResultado);

		// capturado do layout o botão de Balanceamento
		btBalancear = (Button) findViewById(R.id.btBalancear);
		btBalancear.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				CalculoBalanceamento calculoBalanceamento = new MetodoAlgebrico();
				MetodoAlgebricoTest metodo = new MetodoAlgebricoTest();
				metodo.testDadoUmaQuacaoBalancear1();
				txtResultado.setText(metodo.getResultado());

			}
		});
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.balanceamento, menu);
		return true;
	}

}
