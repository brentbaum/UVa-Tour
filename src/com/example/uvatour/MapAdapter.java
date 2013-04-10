package com.example.uvatour;

import java.util.List;

import android.graphics.Color;
import android.location.Location;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapAdapter {
	GoogleMap mMap;

	public MapAdapter(GoogleMap gMap) {
		this.mMap = gMap;
	}

	public void plotRoute(List<LatLng> points) {
        PolylineOptions lineOptions = new PolylineOptions();
        lineOptions.addAll(points);
        lineOptions.width(4);
        lineOptions.color(Color.BLUE);
        mMap.addPolyline(lineOptions);
	}
}
