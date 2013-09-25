package com.example.chemistrycalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	Button btBalanceamento;
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
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
