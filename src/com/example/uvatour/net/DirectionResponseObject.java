package com.example.uvatour.net;

import com.example.uvatour.net.DirectionResponseObject.Route.Bound;
import com.example.uvatour.net.DirectionResponseObject.Route.Leg.Step.Poly;
import com.google.android.gms.maps.model.LatLng;

public class DirectionResponseObject {
	public String status;
	public Route[] routes;
	String copyrights;
	Poly overview_polyline;
	String[] warnings;
	int[] waypoint_order;
	Bound bounds;

	public class Route {
		String summary;
		Leg[] legs;

		public class Leg {
			public Step[] steps;

			public class Step {
				String travel_mode;
				LatLng start_location, end_location;
				Poly polyline;
				ValueText duration;
				String html_instructions;
				ValueText distance;

				public class Poly {
					public String points;
				}

				public class ValueText {
					int value;
					String text;
				}
			}
		}

		public class Bound {
			LatLng southwest, northeast;
		}
	}
}
