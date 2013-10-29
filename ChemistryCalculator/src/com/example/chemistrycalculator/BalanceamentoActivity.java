package com.example.chemistrycalculator;

import ufms.calculadora.modelo.Solucao;
import ufms.calculadora.negocio.BalanceamentoController;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class BalanceamentoActivity extends Activity {
	
	public static BalanceamentoController balanceamentoController;
	
	EditText inputReagentes;
	EditText inputProdutos;
	Button btBalancear;
	Button btAdicionarReagente;
	Button brAdicionarProduto;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_balanceamento);

		inputReagentes = (EditText) findViewById(R.id.inputReagentes);
		inputProdutos = (EditText) findViewById(R.id.inputProdutos);
		btBalancear = (Button) findViewById(R.id.btBalancear);
		
		balanceamentoController = new BalanceamentoController();
	};

	public void onClickAddReagente(View view) {

		AdicionarSolucaoActicity.TIPO_SOLUCAO_ATUAL = AdicionarSolucaoActicity.SOLUCAO_REAGENTE;
		Intent telaAdicionarSolucao = new Intent(this, AdicionarSolucaoActicity.class);
		startActivityForResult(telaAdicionarSolucao,100);
		
		

	}

	public void onClickAddProduto(View view) {

		AdicionarSolucaoActicity.TIPO_SOLUCAO_ATUAL = AdicionarSolucaoActicity.SOLUCAO_PRODUTO;
		Intent telaAdicionarSolucao = new Intent(this, AdicionarSolucaoActicity.class);
		startActivityForResult(telaAdicionarSolucao,100);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		String reagentes = "";
		if(BalanceamentoActivity.balanceamentoController.getEquacaoQuimica().getReagentes() != null) {
			Integer pos = 0;
			for (Solucao solucao : BalanceamentoActivity.balanceamentoController.getEquacaoQuimica().getReagentes()) {
				if(pos != 0){
					reagentes += " + ";
				}
				reagentes += (solucao.getElementos().size() > 1 ? "(" : "") + solucao.toString() + (solucao.getElementos().size() > 1 ? ")" : "");
			}
		}
		inputReagentes.setText(reagentes);
		
		String produtos = "";
		if(BalanceamentoActivity.balanceamentoController.getEquacaoQuimica().getProdutos() != null) {
			Integer pos = 0;
			for (Solucao solucao : BalanceamentoActivity.balanceamentoController.getEquacaoQuimica().getProdutos()) {
				if(pos != 0){
					produtos += " + ";
				}
				produtos += (solucao.getElementos().size() > 1 ? "(" : "") + solucao.toString() + (solucao.getElementos().size() > 1 ? ")" : "");
			}
		}
		inputProdutos.setText(produtos);
	}
	
	public void onClickBtBalancear(View view) {
		//TODO Vai para a tela de resultado.
	}


}
