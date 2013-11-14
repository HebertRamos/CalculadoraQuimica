package com.example.chemistrycalculator;

import ufms.calculadora.modelo.Solucao;
import ufms.calculadora.negocio.BalanceamentoController;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Classe que controla a tela de balanceamento.
 * 
 * @author Hebert Ramos.
 *
 */
public class BalanceamentoActivity extends Activity {
	
	public static BalanceamentoController balanceamentoController;
	
	
	private static int SOLUCAO_REAGENTE = 1;
	private static int SOLUCAO_PRODUTO = 2;
	
	EditText inputReagentes;
	EditText inputProdutos;
	Button btBalancear;
	Button btAdicionarReagente;
	Button brAdicionarProduto;
	
	private int tipoSolucaoAtual;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_balanceamento);
		this.setTitle("Cálculo de Balanceamento");

		inputReagentes = (EditText) findViewById(R.id.inputSubstancia);
		inputProdutos = (EditText) findViewById(R.id.inputProdutos);
		btBalancear = (Button) findViewById(R.id.btBalancear);
		
		balanceamentoController = new BalanceamentoController();
	};

	public void onClickAddReagente(View view) {

		tipoSolucaoAtual = SOLUCAO_REAGENTE;
		AdicionarSolucaoActicity.TITULO_PLURAL ="Reagentes";
		AdicionarSolucaoActicity.TITULO_SINGULAR ="Reagente";
		
		Intent telaAdicionarSolucao = new Intent(this, AdicionarSolucaoActicity.class);
		startActivityForResult(telaAdicionarSolucao,100);
		
		

	}

	public void onClickAddProduto(View view) {

		tipoSolucaoAtual = SOLUCAO_PRODUTO;
		AdicionarSolucaoActicity.TITULO_PLURAL ="Produtos";
		AdicionarSolucaoActicity.TITULO_SINGULAR ="Produto";
				
		Intent telaAdicionarSolucao = new Intent(this, AdicionarSolucaoActicity.class);
		startActivityForResult(telaAdicionarSolucao,100);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		if(resultCode == RESULT_OK){
			Solucao solucaoAtual = (Solucao) data.getExtras().getSerializable("solucaoAtual");
			if(tipoSolucaoAtual == SOLUCAO_REAGENTE){
				BalanceamentoActivity.balanceamentoController.getEquacaoQuimica().adicionarReagente(solucaoAtual);
			}else if(tipoSolucaoAtual == SOLUCAO_PRODUTO){
				BalanceamentoActivity.balanceamentoController.getEquacaoQuimica().adicionarProduto(solucaoAtual);
			}
			
			mostraEquacao();
		}
		
	}
	
	public void mostraEquacao(){
		
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
		inputReagentes.setText(Html.fromHtml(reagentes));
		
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
		inputProdutos.setText(Html.fromHtml(produtos));
	}
	
	public void onClickBtBalancear(View view) {
		
		try{
			balanceamentoController.balancearEquacao();
			
			Intent telaSolucaoBalanceamento = new Intent(this, SolucaoBalanceamentoActivity.class);
			startActivityForResult(telaSolucaoBalanceamento,101);
			
		}catch(Exception e){
			//Tela de Erro.
		}
		
		
	}


}
