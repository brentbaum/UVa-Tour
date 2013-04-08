
package com.example.uvatour;

import sofia.app.OptionsMenu;
import sofia.app.Screen;
import android.os.Bundle;
import android.view.Menu;

@OptionsMenu
public class TourScreen extends Screen {

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
		getMenuInflater().inflate(R.menu.tour, menu);
		return true;
	}
	
	// called when mapButton is clicked
	public void mapButtonClicked() {
		setContentView(R.layout.mapscreen);
	}

}
