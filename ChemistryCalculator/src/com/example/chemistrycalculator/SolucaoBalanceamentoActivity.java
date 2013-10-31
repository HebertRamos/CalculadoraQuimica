package com.example.chemistrycalculator;

import ufms.calculadora.modelo.Solucao;
import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class SolucaoBalanceamentoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_solucao_balanceamento);
		
		
		
		TextView textViewreagentes = (TextView) findViewById(R.id.textViewReagentesSolucaoBalanceamento);
		String reagentes = "";
		if(BalanceamentoActivity.balanceamentoController.getEquacaoQuimica().getReagentes() != null) {
			Integer pos = 0;
			for (Solucao solucao : BalanceamentoActivity.balanceamentoController.getEquacaoQuimica().getReagentes()) {
				if(pos != 0){
					reagentes += " + ";
				}
				reagentes += (solucao.getIndice() != 1 || solucao.getCoeficiente() != 1  ? solucao.getIndice()+"(" : "") + solucao.toString() + (solucao.getIndice() != 1 || solucao.getCoeficiente() != 1 ? ")<sub>"+solucao.getCoeficiente()+"</sub>" : "");
				pos++;
			}
		}
		textViewreagentes.setText(Html.fromHtml(reagentes));
		
		TextView textViewProdutos = (TextView) findViewById(R.id.TextViewProdutosSolucaoBalanceamento);
		String produtos = "";
		if(BalanceamentoActivity.balanceamentoController.getEquacaoQuimica().getProdutos() != null) {
			Integer pos = 0;
			for (Solucao solucao : BalanceamentoActivity.balanceamentoController.getEquacaoQuimica().getProdutos()) {
				if(pos != 0){
					produtos += " + ";
				}
				produtos += (solucao.getIndice() != 1 || solucao.getCoeficiente() != 1  ? solucao.getIndice()+"(" : "") + solucao.toString() + (solucao.getIndice() != 1 || solucao.getCoeficiente() != 1 ? ")<sub>"+solucao.getCoeficiente()+"</sub>" : "");
				pos++;
			}
		}
		textViewProdutos.setText(Html.fromHtml(produtos));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.solucao_balanceamento, menu);
		return true;
	}
	
	public void acionaBotaoOk(View v){
		
	}

}
