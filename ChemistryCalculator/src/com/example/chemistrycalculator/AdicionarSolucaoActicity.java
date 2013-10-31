package com.example.chemistrycalculator;

import ufms.calculadora.modelo.Solucao;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AdicionarSolucaoActicity extends Activity {
	
	
	public static Integer TIPO_SOLUCAO_ATUAL;
	public final static Integer SOLUCAO_REAGENTE = 1;
	public final static Integer SOLUCAO_PRODUTO = 2;

	public static Solucao solucaoAtual;
	
	EditText inputsolucao;
	EditText inputCoeficiente;
	EditText inputIndice;
	
	TextView tituloCampoSolucao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_adicionar_solucao);
		this.setTitle(carregaTitulo(TIPO_SOLUCAO_ATUAL));
		
		solucaoAtual = new Solucao();
		
		inputIndice = (EditText) findViewById(R.id.editTextIndiceSolucao);
		inputCoeficiente = (EditText) findViewById(R.id.editTextCoeficienteSolucao);
		inputsolucao = (EditText) findViewById(R.id.editTextSolucao_);
		
		tituloCampoSolucao = (TextView) findViewById(R.id.textViewSolucao);
		tituloCampoSolucao.setText(carregaTituloCampoSolucao(TIPO_SOLUCAO_ATUAL));
	}

	public void adicionarElemento(View v) {
		Intent telaAdicionarElemento = new Intent(this, AdicionarElementoActivity.class);
		startActivityForResult(telaAdicionarElemento, 0);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		inputsolucao.setText(Html.fromHtml(solucaoAtual.toString()));
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
			
			adicionarSolucaoNaEquacao(TIPO_SOLUCAO_ATUAL, solucaoAtual);
		}

		Intent data = new Intent();
		setResult(RESULT_OK, data);
		finish();
	}
	
	private void adicionarSolucaoNaEquacao(Integer tipoSolucaoAtual,  Solucao solucaoAtual){
		if(tipoSolucaoAtual == SOLUCAO_REAGENTE){
			BalanceamentoActivity.balanceamentoController.getEquacaoQuimica().adicionarReagente(solucaoAtual);
		}else{
			BalanceamentoActivity.balanceamentoController.getEquacaoQuimica().adicionarProduto(solucaoAtual);
		}
	}
	
	private String carregaTituloCampoSolucao(Integer tipoSolucaoAtual){
		if(tipoSolucaoAtual == SOLUCAO_REAGENTE){
			return "Reagentes";
		}else{
			return "Produtos";
		}
	}

	
	private String carregaTitulo(Integer tipoSolucaoAtual){
		if(tipoSolucaoAtual == SOLUCAO_REAGENTE){
			return "Adicionar Reagente";
		}else{
			return "Adicionar Produto";
		}
	}

}
