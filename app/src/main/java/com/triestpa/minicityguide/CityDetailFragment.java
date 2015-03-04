package com.triestpa.minicityguide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
            Log.e(TAG, ""+id);
            Log.e(TAG, mCity.getDescription());
            Log.e(TAG, mCity.getPicURL());

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_city_detail, container, false);

        if (mCity != null) {
            ((TextView) rootView.findViewById(R.id.city_name_detail)).setText(mCity.getDescription());
        }

        return rootView;
    }
}
