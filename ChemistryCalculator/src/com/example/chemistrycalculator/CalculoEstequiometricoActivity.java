package com.example.chemistrycalculator;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class CalculoEstequiometricoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calculo_estequiometrico);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.calculo_estequiometrico, menu);
		return true;
	}
	
	
	public void autalizaValoresCampos(){
		
	}

}
