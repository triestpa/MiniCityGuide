package com.triestpa.minicityguide;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.triestpa.minicityguide.CityContent.City;
import com.triestpa.minicityguide.CityContent.CityContentManager;

/**
 * A fragment representing a single City detail screen.
 * This fragment is either contained in a {@link CityListActivity}
 * in two-pane mode (on tablets) or a {@link CityDetailActivity}
 * on handsets.
 */
public class CityDetailFragment extends Fragment {
    public static final String TAG = CityDetailFragment.class.getSimpleName();
    public static final String ARG_ITEM_ID = "City ID";

    private City mCity;

    public CityDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get the city ID from the passed args
        if (getArguments().containsKey(ARG_ITEM_ID)) {
            int id = getArguments().getInt(ARG_ITEM_ID);
            mCity = CityContentManager.getCity(id);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_city_detail, container, false);

        try {
            //Set the page title to the name of the city
            ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle(mCity.getName());
        }
        catch (Exception e) {
            //TODO This is a sloppy fix, should implement a better way to detect if the app is running on tablet
            Log.e(TAG, "Do not set action bar title in 2 pane layout");
        }

        TextView cityName = (TextView) rootView.findViewById(R.id.city_name_detail);
        ImageView cityView = (ImageView) rootView.findViewById(R.id.city_image_detail);

        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();

        if (mCity != null) {
            cityName.setText(mCity.getDescription());
            Picasso.with(getActivity()).load(mCity.getPicURL()).resize(width, height/3).centerCrop().into(cityView);
        }

        return rootView;
    }
}
