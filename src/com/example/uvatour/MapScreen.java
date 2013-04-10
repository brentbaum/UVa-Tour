package com.example.uvatour;

import java.util.ArrayList;
import java.util.List;

import sofia.app.Screen;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;

import com.example.uvatour.net.DirectionProvider;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener;
import com.google.android.gms.maps.LocationSource.OnLocationChangedListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapScreen extends Screen {

	// fields
	private LocationManager locationManager;
	private LatLng latLng;
	private GoogleMap mMap;
	private boolean firstTime = true;
	public List<LatLng> routePoints;
	private DirectionProvider provider;
	private ArrayList<TourStop> stops;

	// this method is called every time the screen is created from scratch
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// initializes the location manager
		locationManager = (LocationManager) this
				.getSystemService(Context.LOCATION_SERVICE);

		// checks to see if GPS in enabled
		boolean gps = locationManager
				.isProviderEnabled(LocationManager.GPS_PROVIDER);
		if (!gps) {
			// begins creation of an alert dialog
			AlertDialog.Builder builder = new AlertDialog.Builder(this);

			// set the title
			builder.setTitle("GPS not enabled");

			// set the dialog message
			builder.setMessage("The GPS is not on. Click Accept to turn it on.");
			builder.setCancelable(false);
			builder.setPositiveButton("Accept",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Intent settingsIntent = new Intent(
									Settings.ACTION_LOCATION_SOURCE_SETTINGS);
							startActivity(settingsIntent);
						}
					});

			// create the dialog and show it
			AlertDialog dialog = builder.create();
			dialog.show();
		}
		mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();
		mMap.setMyLocationEnabled(true);
		mMap.setOnMyLocationChangeListener(new LocationListener());

		provider = new DirectionProvider(mMap, this);

		stops = loadStops();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	public GoogleMap getMap() {
		return mMap;
	}

	// loads stop's coordinates, history, and picture url from an external txt
	// file.
	private ArrayList<TourStop> loadStops() {
		ArrayList<TourStop> list = new ArrayList<TourStop>();
		list.add(new TourStop(38.0545958, -78.5347012, "www.google.com",
				"history"));
		return list;
	}

	// defines an OnMyLocationChangeListener which will animate the map to the
	// user's current
	// position whenever the user moves.
	private class LocationListener implements OnMyLocationChangeListener {
		@Override
		public void onMyLocationChange(Location location) {
			CameraUpdate update = CameraUpdateFactory.newLatLngZoom(new LatLng(
					location.getLatitude(), location.getLongitude()), 18.0f);
			mMap.animateCamera(update);
			latLng = new LatLng(location.getLatitude(), location.getLongitude());
			if (firstTime) {
				provider.query(latLng, stops.get(0));
				firstTime = false;
			}
		}
	}
}
