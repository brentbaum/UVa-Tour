package com.example.uvatour;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TourScreen extends Activity {

	// fields

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tourscreen);
	}

	// called when mapButton is clicked
	public void toMapScreen(View view) {
		Context context = this;
		Intent intent = new Intent(context, MapScreen.class);
		startActivity(intent);
	}
}
