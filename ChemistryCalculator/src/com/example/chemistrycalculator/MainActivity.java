package com.example.chemistrycalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

/**
 * Classe que controla a tela inicial do sistema.
 * 
 * @author Angelo Maggioni
 * @author Hebert Ramos
 *
 */
public class MainActivity extends Activity {

	Button btBalanceamento;
	Button btCalculoEstequiometrico;
	/* 
	 * lallalala
	 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // capturado do layout o botão de Balanceamento
        btBalanceamento = (Button) findViewById(R.id.btBalanceamento);
        btBalanceamento.setOnClickListener(new View.OnClickListener() {
        	 
        	@Override
        	public void onClick(View arg0) {
        	 
	        	Intent trocatela = new Intent(MainActivity.this,BalanceamentoActivity.class);
	        	MainActivity.this.startActivity(trocatela);
	        	//MainActivity.this.finish();
	        	 
        	}
        });
        
        btCalculoEstequiometrico = (Button) findViewById(R.id.btnCalculoEstequiometrico);
        btCalculoEstequiometrico.setOnClickListener(new View.OnClickListener() {
        	 
        	@Override
        	public void onClick(View arg0) {
        	 
	        	Intent trocatela = new Intent(MainActivity.this,CalculoEstequiometricoActivity.class);
	        	MainActivity.this.startActivity(trocatela);
	        	//MainActivity.this.finish();
	        	 
        	}
        });
    }


    @Override
   /*
    * asdfasdf
    * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
    */
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
