package com.example.chemistrycalculator;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class PressaoAtmosfericaLocalActivity extends Activity {

	/* definindo o GPS como fonte de dados de geolocalizacao */
	String PROVIDER = LocationManager.GPS_PROVIDER;

	/* definindo WIFI como fonte de dadods */
	// String PROVIDER = LocationManager.NETWORK_PROVIDER;

	LocationManager locationManager;
	double myLatitude, myLongitude, myAltitude, myPrecissao, pressaoAtm,
			pressaoMilibar;
	TextView textLatitude, textLongitude, textAltitude, textPrecissao,
			textPressaoAtm, textPressaoMilibar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pressao_atmosferica_local);

		/* capturando os botoes da tela */
		textLatitude = (TextView) findViewById(R.id.txtLatitude);
		textLongitude = (TextView) findViewById(R.id.txtLongitude);
		textAltitude = (TextView) findViewById(R.id.txtAltitude);
		textPrecissao = (TextView) findViewById(R.id.txtPrecissao);
		textPressaoMilibar = (TextView) findViewById(R.id.txtMb);
		textPressaoAtm = (TextView) findViewById(R.id.txtAtm);

		/* Iniciando o serviço de localização */
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Location lastLocation = locationManager.getLastKnownLocation(PROVIDER);
		if (lastLocation != null) {
			updateLoc(lastLocation);
		}
	}
	private void updateLoc(Location loc) {
		pressaoMilibar = 1013 - (loc.getAltitude() / 8);
		pressaoAtm = 0.0009869232667 * pressaoMilibar;

		textLatitude.setText("" + loc.getLatitude());
		textLongitude.setText("" + loc.getLongitude());
		textAltitude.setText("" + loc.getAltitude());
		textPrecissao.setText("" + loc.getAccuracy());
		textPressaoAtm.setText("" + pressaoAtm);
		textPressaoMilibar.setText("" + pressaoMilibar);
	}

	/* Método gerado automaticamente
	 *
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		locationManager.requestLocationUpdates(PROVIDER, 0, 0,
				myLocationListener);
	}

	/* 
	 * Método gerado automaticamente
	 */
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		locationManager.removeUpdates(myLocationListener);
	}

	/* 
	 * Método gerado automaticamente 
	 */
	private LocationListener myLocationListener = new LocationListener() {
		@Override
		public void onLocationChanged(Location location) {
			updateLoc(location);

		}

		@Override
		public void onProviderDisabled(String provider) {
			Message msg = handler.obtainMessage();
			msg.arg1 = 1;
			handler.sendMessage(msg);
		}
		/* 
		 * Método gerado para o tratamento do GPS estar Desligado
		 * oferecendo ao usuário a opção de liga-lo atraves de 
		 * um Popup
		 */
		@SuppressLint("HandlerLeak")
		private final Handler handler = new Handler() {
			public void handleMessage(Message msg) {
				if (msg.arg1 == 1) {
					if (!isFinishing()) {
						AlertDialog.Builder builder = new AlertDialog.Builder(
								PressaoAtmosfericaLocalActivity.this);
						builder.setMessage(
								"GPS Desligado! Gostaria de Habilitar?")
								.setCancelable(false)
								.setPositiveButton("Ligar GPS",
										new DialogInterface.OnClickListener() {
											public void onClick(
													DialogInterface dialog,
													int id) {
												Intent gpsOptionsIntent = new Intent(
														android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
												startActivity(gpsOptionsIntent);
											}
										});
						builder.setNegativeButton("Cancelar",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										dialog.cancel();
									}
								});
						AlertDialog alert = builder.create();
						alert.show();
					}
				}

			}
		};

		/* 
		 * Método gerado automaticamente 
		 */
		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub

		}
		/* 
		 * Método gerado automaticamente 
		 */
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub

		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pressao_atmosferica_local, menu);
		return true;
	}

}
