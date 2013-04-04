
package com.example.uvatour;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class TourActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tour);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tour, menu);
		return true;
	}
	
	public void toGoogleMaps(View view) {
		setContentView(R.layout.google_maps);
	}

}
