package com.example.uvatour;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.uvatour.net.DirectionProvider;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class MapScreen extends FragmentActivity {

	// fields
	private LocationManager locationManager;
	private LatLng latLng;
	private GoogleMap mMap;
	private boolean firstTime = true;
	public List<LatLng> routePoints;
	private DirectionProvider provider;
	private ArrayList<TourStop> tours = new ArrayList<TourStop>();
	private int currentStop = 0;
	private HistoryFragmentPagerAdapter historyFragmentPagerAdapter;
	private ViewPager viewPager;

	// this method is called every time the screen is created from scratch
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapscreen);

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
		mMap = ((SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map)).getMap();
		mMap.setMyLocationEnabled(true);
		mMap.setOnMyLocationChangeListener(new LocationListener());

		provider = new DirectionProvider(mMap, this);

		// loads tour stops into array list
		loadStops();

		// creates the adapter that serves up fragments
		historyFragmentPagerAdapter = new HistoryFragmentPagerAdapter(
				getSupportFragmentManager(), tours);

		// set up the ViewPager and associate it with the adapter
		viewPager = (ViewPager) findViewById(R.id.pager);
		viewPager.setAdapter(historyFragmentPagerAdapter);
	}

	// called when the Next Tour button is clicked
	public void toNextTour (View view) {
		if (currentStop < (tours.size()-1)) {
			currentStop++;
			provider.drawNext(latLng, tours.get(currentStop));
			updateColorBar();
		}
		else {
			Context context = this;
			Intent intent = new Intent(context, CongratsScreen.class);
			startActivity(intent);
		}
	}
	
	public void updateColorBar() {
		View bar = findViewById(R.id.color_bar);
		bar.setBackgroundResource(R.drawable.color_bar_red);
	}

	public GoogleMap getMap() {
		return mMap;
	}

	// defines an OnMyLocationChangeListener which will animate the map to the
	// user's current
	// position whenever the user moves.
	private class LocationListener implements OnMyLocationChangeListener {
		@Override
		public void onMyLocationChange(Location location) {
			updateColorBar();
			CameraUpdate update = CameraUpdateFactory.newLatLngZoom(new LatLng(
					location.getLatitude(), location.getLongitude()), 18.0f);
			mMap.animateCamera(update);
			latLng = new LatLng(location.getLatitude(), location.getLongitude());
			if (firstTime) {
				provider.drawNext(latLng, tours.get(currentStop));
				firstTime = false;
			}
		}
	}

	public boolean loadStops() {
		AssetManager manager = null;
		InputStream is = null;
		try {
			manager = this.getAssets();
			is = manager.open("stops.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scanner scanner = null;
		scanner = new Scanner(is);

		String title;
		String history;
		String url;
		double lat;
		double lon;

		// data guaranteed to always be in groups of 5 lines
		while (scanner.hasNext()) {
			title = scanner.nextLine();
			history = scanner.nextLine();
			url = scanner.nextLine();
			lat = scanner.nextDouble();
			lon = scanner.nextDouble();

			if (scanner.hasNext())
				scanner.nextLine();
			if (scanner.hasNext())
				scanner.nextLine();

			tours.add(new TourStop(title, url, history, lat, lon));
		}

		return true;
	}
}
