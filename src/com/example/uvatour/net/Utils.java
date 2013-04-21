package com.example.uvatour.net;

import com.example.uvatour.net.DirectionResponseObject.Route.Leg.Step;
import com.google.gson.Gson;

public class Utils {

	public static final String server_url = "https://maps.googleapis.com/maps/api/directions/json?";

	public static String constructRestUrlForDirectionRequest(double ilat,
			double ilon, double dlat, double dlon) {
		return server_url + "origin=" + ilat + "," + ilon + "&destination="
				+ dlat + "," + dlon + "&sensor=true"
				+ "&region=gb&mode=walking";
	}

	public static class DirectionObject {
		public String nextId;
		public String version;
		public String cachedOnUTC;
	}

	public static String convertStreamToString(java.io.InputStream is) {
		if (is != null) {
			java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
			return s.hasNext() ? s.next() : "";
		}
		return "";
	}

	public static String[] parseResponseForPolyPoints(String response) {
		Gson gson = new Gson();
		DirectionResponseObject dires = gson.fromJson(response,
				DirectionResponseObject.class);
		Step[] steps = dires.routes[0].legs[0].steps;
		String[] lines = new String[steps.length];
		for (int x = 0; x < steps.length; x++) {
			lines[x] = steps[x].polyline.points;
		}
		return lines;
	}

	public static float distance(double lat_a, double lng_a, double lat_b, double lng_b) {
		double earthRadius = 3958.75;
		double latDiff = Math.toRadians(lat_b - lat_a);
		double lngDiff = Math.toRadians(lng_b - lng_a);
		double a = Math.sin(latDiff / 2) * Math.sin(latDiff / 2)
				+ Math.cos(Math.toRadians(lat_a))
				* Math.cos(Math.toRadians(lat_b)) * Math.sin(lngDiff / 2)
				* Math.sin(lngDiff / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distance = earthRadius * c;

		int meterConversion = 1609;

		return new Float(distance * meterConversion).floatValue();
	}
}
