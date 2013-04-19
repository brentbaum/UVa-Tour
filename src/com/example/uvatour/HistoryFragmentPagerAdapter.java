package com.example.uvatour;

import java.util.ArrayList;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class HistoryFragmentPagerAdapter extends FragmentPagerAdapter {

	// fields
	private static int NUM_OF_TOUR_STOPS = 6;
	private ArrayList<TourStop> tours;

	// constructor
	public HistoryFragmentPagerAdapter (FragmentManager fragmentManager, ArrayList<TourStop> tours) {
		super(fragmentManager);
		this.tours = tours;
	}

	@Override
	public Fragment getItem(int index) {
		switch(index) {
		case 0:
			// tour stop 1
			return new HistoryFragment(tours.get(0));
		case 1:
			// tour stop 2
			return new HistoryFragment(tours.get(1));
		case 2:
			// tour stop 2
			return new HistoryFragment(tours.get(2));
		case 3:
			// tour stop 2
			return new HistoryFragment(tours.get(3));
		case 4:
			// tour stop 2
			return new HistoryFragment(tours.get(4));
		case 5:
			// tour stop 2
			return new HistoryFragment(tours.get(5));
		default:
			// creates the first tour stop
			Fragment fragment = new HistoryFragment();
			return fragment;
		}
	}

	@Override
	public int getCount() {
		return NUM_OF_TOUR_STOPS;
	}

	@SuppressLint("ValidFragment")
	public static class HistoryFragment extends Fragment {
		
		// fields
		TourStop tourStop;
		
		public HistoryFragment() {
			super();
		}
		
		public HistoryFragment(TourStop tourStop) {
			super();
			this.tourStop = tourStop;
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.historyfragment, container, false);
			
			// title of tour stop
			((TextView) rootView.findViewById(R.id.name)).setText(tourStop.getTitle());
			
			// image of tour stop
			ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getActivity()).build();
			ImageLoader.getInstance().init(config);
			ImageLoader imageLoader = ImageLoader.getInstance();
			ImageView image = ((ImageView) rootView.findViewById(R.id.image));
			imageLoader.displayImage(tourStop.getPictureUrl(), image);
			
			// history of tour stop
			((TextView) rootView.findViewById(R.id.history_text)).setText(tourStop.getHistory());
			return rootView;
		}
	}
}