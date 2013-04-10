package com.example.uvatour.net;

import java.io.File;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

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
		Gson gson = new Gson();
		DirectionResponseObject dires = gson.fromJson(response, DirectionResponseObject.class);
		System.out.println(dires.toString());
		return new String[5];
	}
}
