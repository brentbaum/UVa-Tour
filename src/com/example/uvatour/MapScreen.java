package com.example.uvatour;

import sofia.app.Screen;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapScreen extends Screen {

	// fields
	private LocationManager locationManager;
	private LatLng latLng;
	private GoogleMap map;
	private MarkerOptions marker;

	// this method is called every time the screen is created from scratch
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// initializes the location manager
		locationManager = (LocationManager) 
				this.getSystemService(Context.LOCATION_SERVICE);

		// checks to see if GPS in enabled
		boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER); 
		if(!gps)
		{
			// begins creation of an alert dialog
			AlertDialog.Builder builder = new AlertDialog.Builder(this);

			// set the title
			builder.setTitle("GPS not enabled");

			// set the dialog message
			builder.setMessage("The GPS is not on. Click Accept to turn it on.");
			builder.setCancelable(false);
			builder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
					startActivity(settingsIntent);
				}
			});
			
			// create the dialog and show it
			AlertDialog dialog = builder.create();
			dialog.show();
		}
	}
	
	@Override
	protected void onStop () {
		super.onStop();
		
	}
}
