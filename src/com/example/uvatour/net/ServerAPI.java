package com.example.uvatour.net;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.example.uvatour.net.Utils.DirectionObject;

import android.app.Activity;
import android.util.Log;

public class ServerAPI {
	private String auth;
	private Activity current;

	public static ServerAPI getInstance(Activity a) {
		return new ServerAPI(a);
	}

	private ServerAPI(Activity a) {
		this.current = a;
	}

	public void getDirectionRequest(double ilat, double ilon, double dlat, double dlon, final GetCallback callback) {
		String restUrl = Utils.constructRestUrlForDirectionRequest(ilat, ilon, dlat, dlon);
		new GetTask(restUrl, "Get Home Page", new GetCallback(current) {
			@Override
			public void onTaskComplete(String response) {
				callback.onDataReceived(response);
			}
		}).execute();
	}
}