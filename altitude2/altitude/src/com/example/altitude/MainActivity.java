package com.example.altitude;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	Button pressaoAtmosferica;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
		/* capturado o evento de clicar no Botão de Obter Pressão*/ 
        pressaoAtmosferica = (Button) findViewById(R.id.btPressaoAtmosferica);
        pressaoAtmosferica.setOnClickListener(new View.OnClickListener() {
        	 
        	@Override
        	public void onClick(View arg0) {
        	 
        		/* Iniciando a troca da tela */
	        	Intent trocatela = new Intent(MainActivity.this,PressaoAtmosfericaLocalActivity.class);
	        	MainActivity.this.startActivity(trocatela);
        	}
        });
    }
    
   
		
}