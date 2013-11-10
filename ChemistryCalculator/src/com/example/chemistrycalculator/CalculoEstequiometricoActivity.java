package com.example.chemistrycalculator;

import ufms.calculadora.modelo.Solucao;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class CalculoEstequiometricoActivity extends Activity {
	
	private Solucao solucao;
	
	EditText inputSubstancia;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calculo_estequiometrico);
		
		inputSubstancia = (EditText) findViewById(R.id.inputSubstancia);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}
	
	public void onClickBtCalcular(View v){
		SolucaoCalculoEstequiometricoActivity.solucao =solucao;
		
		Intent telaSolucaoCalculoEstequiometrico = new Intent(this, SolucaoCalculoEstequiometricoActivity.class);
		startActivityForResult(telaSolucaoCalculoEstequiometrico,100);
	}
	
	public void onClickAddSolucao(View v){
		
		AdicionarSolucaoActicity.TITULO_PLURAL ="Substância";
		AdicionarSolucaoActicity.TITULO_SINGULAR ="Substância";
		
		Intent telaAdicionarSolucao = new Intent(this, AdicionarSolucaoActicity.class);
		startActivityForResult(telaAdicionarSolucao,100);
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		if(resultCode == RESULT_OK){
			solucao = (Solucao) data.getExtras().getSerializable("solucaoAtual");
			String solucaoString = (solucao.getIndice() != 1 || solucao.getCoeficiente() != 1  ? solucao.getIndice()+"(" : "") + solucao.toString() + (solucao.getIndice() != 1 || solucao.getCoeficiente() != 1 ? ")<sub>"+solucao.getCoeficiente()+"</sub>" : "");
			inputSubstancia.setText(Html.fromHtml(solucaoString));
		}
	}

}
