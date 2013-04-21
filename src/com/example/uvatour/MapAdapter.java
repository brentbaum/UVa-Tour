package com.example.uvatour;

import java.util.List;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapAdapter {
	GoogleMap mMap;

	public MapAdapter(GoogleMap gMap) {
		this.mMap = gMap;
	}

	public void plotRoute(List<LatLng> points) {
        PolylineOptions lineOptions = new PolylineOptions();
        lineOptions.addAll(points);
        lineOptions.width(10);
        lineOptions.color(0x880000ff);
        mMap.clear();
        mMap.addPolyline(lineOptions);
	}
}
