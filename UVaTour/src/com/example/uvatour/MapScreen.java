package com.example.uvatour;

import sofia.app.Screen;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Message;
import android.provider.Settings;

public class MapScreen extends Screen {

	// fields
	private LocationManager locationManager;
	final Context context = this;

	// this method is called every time the screen is created from scratch
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// initializes the location manager
		locationManager = (LocationManager) 
				this.getSystemService(Context.LOCATION_SERVICE);

		// checks to see if GPS in enabled
		if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
		{
			// begins creation of an alert dialog
			AlertDialog.Builder builder = new AlertDialog.Builder(context);

			// set the title
			builder.setTitle("GPS not enabled");

			// set the dialog message
			builder.setMessage("The GPS is not on. Click Accept to turn it on.")
			.setCancelable(false)
			.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					enableLocationSettings();
				}
			});
			
			// create the dialog and show it
			AlertDialog alertDialog = builder.create();
			alertDialog.show();
		}

		// initializes the location provider to the GPS
		
		// sets the view
		setContentView(R.layout.mapscreen);
	}

	private void enableLocationSettings() {
		Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		startActivity(settingsIntent);
	}
	
	//public void onLocationChanged(Location location) {
	//	Message.obtain();
	//}
	
	@Override
	protected void onStop () {
		super.onStop();
		
	}
}
