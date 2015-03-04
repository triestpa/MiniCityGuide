package com.triestpa.minicityguide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
    public static final String ARG_ITEM_ID = "item_id";

    private City thisCity;

    public CityDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            thisCity = CityContentManager.getCity(ARG_ITEM_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_city_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (thisCity != null) {
            ((TextView) rootView.findViewById(R.id.city_detail)).setText(thisCity.getDescription());
        }

        return rootView;
    }
}
