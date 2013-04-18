package com.example.uvatour;

import com.google.android.gms.maps.model.LatLng;

public class TourStop {
	private LatLng coordinates;
	private String title;
	private String pictureUrl;
	private String history;
	private TourStop nextStop;

	public TourStop(String title, String url, String his, double lat, double lon) {
		this.coordinates = new LatLng(lat, lon);
		this.pictureUrl = url;
		this.history = his;
		this.title = title;
	}
	public double getLatitude() {
		return this.coordinates.latitude;
	}

	public double getLongitude() {
		return this.coordinates.longitude;
	}

	public String getPictureUrl() {
		return this.pictureUrl;
	}

	public String getHistory() {
		return this.history;
	}

	public void setNext(TourStop temp) {
		this.nextStop = temp;
	}
	
	public TourStop getNext() {
		return this.nextStop;
	}
	
	@Override
	public String toString() {
		return "Name: " + title + "\nurl: " + pictureUrl + "lat/lng: " + coordinates;
	}
}
