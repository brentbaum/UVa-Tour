package com.example.uvatour.net;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

import com.example.uvatour.TourScreen;
import com.example.uvatour.TourStop;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

public class DirectionProvider {
	GoogleMap mMap;
	ServerAPI server;
	TourScreen current;

	public DirectionProvider(GoogleMap map, Activity a) {
		this.mMap = map;
		server = ServerAPI.getInstance(a);
		current = (TourScreen) a;
	}

	public List<LatLng> query(LatLng latLng, TourStop tourStop) {
		server.getDirectionRequest(latLng.latitude, latLng.longitude, tourStop.getLatitude(),
				tourStop.getLongitude(), new GetCallback(current) {
					public void onDataReceived(String response) {
						String[] encodedPoints = Utils
								.parseResponseForPolyPoints(response);
						List<LatLng> directions = new ArrayList<LatLng>();
						for (String s : encodedPoints)
							directions.addAll(decode(s));
					}
				});
		return null;
	}

	public static List<LatLng> decode(String encoded_points) {
		int index = 0;
		int lat = 0;
		int lng = 0;
		List<LatLng> out = new ArrayList<LatLng>();
		try {
			int shift;
			int result;
			while (index < encoded_points.length()) {
				shift = 0;
				result = 0;
				while (true) {
					int b = encoded_points.charAt(index++) - '?';
					result |= ((b & 31) << shift);
					shift += 5;
					if (b < 32)
						break;
				}
				lat += ((result & 1) != 0 ? ~(result >> 1) : result >> 1);

				shift = 0;
				result = 0;
				while (true) {
					int b = encoded_points.charAt(index++) - '?';
					result |= ((b & 31) << shift);
					shift += 5;
					if (b < 32)
						break;
				}
				lng += ((result & 1) != 0 ? ~(result >> 1) : result >> 1);
				/* Add the new Lat/Lng to the Array. */
				out.add(new LatLng((lat * 10), (lng * 10)));
			}
			return out;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out;
	}
}
