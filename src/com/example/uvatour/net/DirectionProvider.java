package com.example.uvatour.net;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

import com.example.uvatour.MapAdapter;
import com.example.uvatour.MapScreen;
import com.example.uvatour.TourScreen;
import com.example.uvatour.TourStop;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

public class DirectionProvider {
	GoogleMap mMap;
	ServerAPI server;
	MapScreen current;

	public DirectionProvider(GoogleMap map, Activity a) {
		this.mMap = map;
		server = ServerAPI.getInstance(a);
		current = (MapScreen) a;
	}

	public boolean query(LatLng latLng, TourStop tourStop) {
		System.out.println("Creating Request");
		server.getDirectionRequest(latLng.latitude, latLng.longitude,
				tourStop.getLatitude(), tourStop.getLongitude(),
				new GetCallback(current) {
					public void onDataReceived(String response) {
						System.out.println("Response Received");
						String[] encodedPoints = Utils
								.parseResponseForPolyPoints(response);
						List<LatLng> directions = new ArrayList<LatLng>();
						for (String s : encodedPoints) {
							directions.addAll(decodePoly(s));
							System.out.println(directions);
						}

						MapAdapter mAdapter = new MapAdapter(
								((MapScreen) current).getMap());
						mAdapter.plotRoute(directions);
					}
				});
		return true;
	}

	private List<LatLng> decodePoly(String encoded) {
		 
        List<LatLng> poly = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;
 
        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;
 
            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;
 
            LatLng p = new LatLng((((double) lat / 1E5)),
                                 (((double) lng / 1E5)));
            poly.add(p);
        }
        return poly;
    }
}
