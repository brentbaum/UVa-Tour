package com.example.uvatour;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.VideoView;

public class CongratsScreen extends Activity {

	// fields
	private VideoView videoView = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.congratscreen);
		playVideo();
		//Sets up viedeoview for congratscreen
		/*videoView = (VideoView) findViewById(R.id.videoView1);
		videoView.setKeepScreenOn(true);
		videoView.setVideoPath("wahoowa");
		videoView.requestFocus();   
		videoView.start();*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mapscreenmenu, menu);
		return true;
	}

	// called when restartButton is clicked
	public void toMapScreen(View view) {
		Context context = this;
		Intent intent = new Intent(context, MapScreen.class);
		startActivity(intent);
	}

	public void playVideo() {
		videoView = (VideoView) findViewById(R.id.videoView1);
		setContentView(R.layout.congratscreen);
		Uri video = Uri.parse("android.resource://" + getPackageName() + "/"
				+ R.raw.wahoowa);
		videoView.setVideoURI(video);
		videoView.requestFocus();
		videoView.start();
	}
}
