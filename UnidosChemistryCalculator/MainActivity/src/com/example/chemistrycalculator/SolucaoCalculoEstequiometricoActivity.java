package com.example.chemistrycalculator;

import ufms.calculadora.modelo.Solucao;
import ufms.calculadora.negocio.CalculoEstequiometricoController;
import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Classe que controlua a solução do calculo estequiométrico.
 * 
 * @author Hebert Ramos.
 *
 */
public class SolucaoCalculoEstequiometricoActivity extends Activity {

	public static Solucao solucao;

	private CalculoEstequiometricoController controller;
	private EditText valor;
	private TextView labelGrandezaAtual;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_solucao_calculo_estequiometrico);

		TextView textViewSolucaoR = (TextView) findViewById(R.id.textViewSolucaoR);
		labelGrandezaAtual = (TextView) findViewById(R.id.textViewGrandezaAtual);
		valor = (EditText) findViewById(R.id.editTextValor);

		String solucaoString = (solucao.getIndice() != 1 || solucao.getCoeficiente() != 1 ? solucao.getIndice() + "(" : "") + solucao.toString() + (solucao.getIndice() != 1 || solucao.getCoeficiente() != 1 ? ")<sub>" + solucao.getCoeficiente() + "</sub>" : "");
		textViewSolucaoR.setText(Html.fromHtml(solucaoString));

		controller = new CalculoEstequiometricoController();
		controller.setSolucao(solucao);

		// Valor inicial em Mol
		valor.setText(String.valueOf(controller.converteParaNumeroMols()));
		labelGrandezaAtual.setText("Valor em Mol");

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.solucao_calculo_estequiometrico, menu);
		return true;
	}

	private Double carregaValor() {
		String valorString = valor.getText().toString().replace(",", ".");
		Double valorDouble = Double.parseDouble(valorString);
		return valorDouble;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_converte_para_mol:
			
			controller.setValorAtua(carregaValor());
			valor.setText(String.valueOf(controller.converteParaNumeroMols()));
			labelGrandezaAtual.setText("Valor em Mol");
			
			return true;
		case R.id.action_converte_para_massa_molar:
			
			controller.setValorAtua(carregaValor());
			valor.setText(String.valueOf(controller.converteParaMassaMolar()));
			
			labelGrandezaAtual.setText("Valor em Massa Molar");
			return true;
		case R.id.action_converte_para_volume:
			
			controller.setValorAtua(carregaValor());
			valor.setText(String.valueOf(controller.converteParaVolume()));
			labelGrandezaAtual.setText("Valor em Volume na CNTP");
			return true;
			
		case R.id.action_converte_para_numero_moleculas:
			
			controller.setValorAtua(carregaValor());
			valor.setText(String.valueOf(controller.converteParaNumeroMoleculas()));

			labelGrandezaAtual.setText("Valor em Número de Moléculas");
			return true;
		default:
			labelGrandezaAtual.setText("Grandeza não definida");
			return super.onOptionsItemSelected(item);
		}
	}
}
