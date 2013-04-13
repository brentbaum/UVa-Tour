package com.example.uvatour.net;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.AsyncTask;
import android.util.Log;

/**
 * An AsyncTask implementation for performing GETs on the Hypothetical REST
 * APIs.
 */
public class GetTask extends AsyncTask<String, String, String> {

	private String mRestUrl, type;
	private GetCallback mCallback;

	/**
	 * Creates a new instance of GetTask with the specified URL and callback.
	 * 
	 * @param restUrl
	 *            The URL for the REST API.
	 * @param current
	 * @param getCallback
	 *            The callback to be invoked when the HTTP request completes.
	 * 
	 */
	public GetTask(String restUrl, String type, GetCallback getCallback) {
		this.mRestUrl = restUrl;
		this.mCallback = getCallback;
		this.type = type;
	}

	@Override
	protected String doInBackground(String... params) {
		String response = "0";
		HttpURLConnection urlConnection = null;
		try {
			URL url = new URL(mRestUrl);
			urlConnection = (HttpURLConnection) url.openConnection();

			InputStream in = new BufferedInputStream(
					urlConnection.getInputStream());
			response = Utils.convertStreamToString(in);
			in.close();
			urlConnection.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			Log.e("error:", "Error at " + type);
			Log.e("error:",
					Utils.convertStreamToString(urlConnection.getErrorStream()));
		}
		System.out.println(response);
		return response;
	}

	@Override
	protected void onPostExecute(String result) {
		mCallback.onDataReceived(result);
		super.onPostExecute(result);
	}
}
