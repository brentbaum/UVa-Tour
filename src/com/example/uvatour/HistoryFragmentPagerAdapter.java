package com.example.uvatour;

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

	// constructor
	public HistoryFragmentPagerAdapter (FragmentManager fragmentManager) {
		super(fragmentManager);
	}

	@Override
	public Fragment getItem(int index) {
		switch(index) {
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

	public static class HistoryFragment extends Fragment {
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.historyfragment, container, false);
			((TextView) rootView.findViewById(R.id.name)).setText(
					/*METHOD FOR TEXT INPUT GOES HERE*/"Testing!");
			((ImageView) rootView.findViewById(R.id.image)).setImageResource(R.drawable.ic_launcher);
			((TextView) rootView.findViewById(R.id.history_text)).setText(
					/*METHOD FOR TEXT INPUT GOES HERE*/"Cupcake ipsum dolor sit amet wafer gingerbread pastry danish. Sugar plum brownie cotton candy halvah. " +
							"Candy canes oat cake cake lollipop jujubes topping gingerbread. Donut marzipan lollipop lemon drops donut chocolate bar jujubes. " +
							"Lemon drops candy muffin halvah apple pie candy jelly-o jelly-o. Powder sweet roll candy canes jelly-o dessert. Lemon drops sweet " +
							"roll oat cake donut pie sweet. Carrot cake liquorice cotton candy. Toffee applicake cookie. Croissant bonbon sweet roll oat " +
							"cake cookie toffee cupcake croissant candy. Pastry lemon drops marzipan ice cream lollipop.\n\n\t Sweet jelly-o lemon drops. Tootsie roll " +
							"danish marzipan. Powder tart pastry sweet chupa chups faworki liquorice macaroon topping. Tart halvah soufflé chupa chups. Toffee " +
							"macaroon chupa chups macaroon jujubes jujubes dessert liquorice. Carrot cake croissant brownie faworki pudding jelly-o brownie jujubes." +
							" Croissant pudding jelly beans sweet roll donut. Pudding pie muffin sesame snaps wafer. Lollipop soufflé ice cream. Cheesecake " +
							"chocolate sugar plum chocolate bar. Carrot cake powder gingerbread wafer lollipop pastry jelly beans wypas. Tootsie roll carrot cake" +
					" topping bear claw caramels. Donut bonbon applicake cotton candy.");
			return rootView;
		}
	}
}