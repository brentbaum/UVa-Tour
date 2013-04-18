package com.example.uvatour;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class TourScreen extends Activity {

	// fields
	// these will be automatically filled by sofia

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tourscreen);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tourscreen, menu);
		return true;
	}

	// called when mapButton is clicked
	public void toMapScreen(View view) {
		Context context = this;
		Intent intent = new Intent(context, MapScreen.class);
		startActivity(intent);
	}
}
