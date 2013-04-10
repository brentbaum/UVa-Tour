package com.example.uvatour;

import com.google.android.gms.maps.model.LatLng;

public class TourStop {
	private LatLng coordinates;
	private String pictureUrl;
	private String history;
	
	public TourStop(double lat, double lon, String url, String his) {
		this.coordinates = new LatLng(lat, lon);
		this.pictureUrl = url;
		this.history = his;
	}

	public double getLatitude() {
		return coordinates.latitude;
	}
	
	public double getLongitude() {
		return coordinates.longitude;
	}
}
