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

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.congratscreen);
		showVideo();
		//Sets up viedeoview for congratscreen
		/*videoView = (VideoView) findViewById(R.id.videoView1);
		videoView.setKeepScreenOn(true);
		videoView.setVideoPath("wahoowa");
		videoView.requestFocus();   
		videoView.start();*/
	}

	// called when restartButton is clicked
	public void toMapScreen(View view) {
		Context context = this;
		Intent intent = new Intent(context, MapScreen.class);
		startActivity(intent);
	}

	private void showVideo()
	{
		VideoView vd = (VideoView)findViewById(R.id.videoView1);
		Uri uri = Uri.parse("android.resource://package/"+R.raw.wahoowa);
		MediaController mc = new MediaController(this);
		vd.setMediaController(mc);
		vd.setVideoURI(uri);
		vd.start();
	}

}
