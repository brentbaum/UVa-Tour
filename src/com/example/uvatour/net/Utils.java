package com.example.uvatour.net;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {

	public static final String server_url = "https://maps.googleapis.com/maps/api/directions/json?";

	public static String constructRestUrlForDirectionRequest(double ilat, double ilon, double dlat, double dlon) {
		return server_url + "origin=" + ilat + "," +  ilon + "&destination=" + dlat + "," + dlon +"&sensor=true"+
				"&region=gb&mode=walking";
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
		ObjectMapper m = new ObjectMapper();
		// can either use mapper.readTree(source), or mapper.readValue(source, JsonNode.class);
		JsonNode rootNode;
		try {
			rootNode = m.readTree(response);
			JsonNode nameNode = rootNode.path("status");
			System.out.println(nameNode.asText());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String[5];
	}
}
