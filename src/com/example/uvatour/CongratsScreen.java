/*
 * Some of this code came from:
 * http://stackoverflow.com/questions/15733749/read-gif-images-using-webview-android
 */
package com.example.uvatour;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class CongratsScreen extends Activity {

	// fields
	private VideoView videoView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.congratscreen);

		// plays a video
		videoView = (VideoView)findViewById(R.id.videoView1);
		videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.wahoowa));
		videoView.setMediaController(new MediaController(this));
		videoView.requestFocus();
		videoView.start();
	}

	// called when restartButton is clicked
	public void toMapScreen(View view) {
		Context context = this;
		Intent intent = new Intent(context, MapScreen.class);
		startActivity(intent);
	}
}
