package com.example.chemistrycalculator;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;


public class AdicionarProdutoActicity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_adicionar_produto);
	}

	public void onClick(View view) {
		finish();
	}

	@Override
	public void finish() {
		Intent data = new Intent();

		EditText inputText = (EditText) findViewById(R.id.inputSolucao);
		String returnString = inputText.getText().toString();	
		data.putExtra("solucao", returnString);
		setResult(RESULT_OK, data);
		super.finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.adicionar_produto, menu);
		return true;
	}

}
