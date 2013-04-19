package com.example.uvatour;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.VideoView;

public class CongratsScreen extends Activity {

	// fields

		@Override
		
		
		public void onCreate(Bundle savedInstanceState) {
		       super.onCreate(savedInstanceState);
		       setContentView(R.layout.congratscreen);
		       
		       //Sets up viedeoview for congratscreen
		       VideoView videoView = (VideoView)findViewById(R.id.videoView1);
		       videoView.setKeepScreenOn(true);
		       videoView.setVideoPath("assets/wahoowa.mp4");
		       videoView.start();
		       videoView.requestFocus();   
		}

		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.tourscreen, menu);
			return true;
		}

		// called when restartButton is clicked
		public void toMapScreen(View view) {
			Context context = this;
			Intent intent = new Intent(context, MapScreen.class);
			startActivity(intent);
		}
		
}
