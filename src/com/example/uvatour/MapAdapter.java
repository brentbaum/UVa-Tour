package com.example.uvatour;

import java.util.List;

import android.graphics.Color;
import android.location.Location;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapAdapter {
	GoogleMap mMap;

	public MapAdapter(GoogleMap gMap) {
		this.mMap = gMap;
	}

	public void plotRoute(List<LatLng> points) {
		for (int x = 0; x < points.size() - 1; x++) {
			mMap.addPolyline(new PolylineOptions()
		     .add(points.get(x), points.get(x+1))
		     .width(5)
		     .color(Color.BLUE));
			System.out.println(points.get(x)+", "+points.get(x+1));
		}
	}
}
