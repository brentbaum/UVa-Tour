package com.example.uvatour.net;

import android.app.Activity;

public class ServerAPI {
	private Activity current;

	public static ServerAPI getInstance(Activity a) {
		return new ServerAPI(a);
	}

	private ServerAPI(Activity a) {
		this.current = a;
	}

	public void getDirectionRequest(double ilat, double ilon, double dlat,
			double dlon, final GetCallback callback) {
		String restUrl = Utils.constructRestUrlForDirectionRequest(ilat, ilon,
				dlat, dlon);
		new GetTask(restUrl, "Get Directions", new GetCallback(current) {
			@Override
			public void onDataReceived(String response) {
				System.out.print("Task Complete.");
				callback.onDataReceived(response);
			}
		}).execute();
	}
}