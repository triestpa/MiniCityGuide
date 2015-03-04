package com.triestpa.minicityguide;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import com.triestpa.minicityguide.CityContent.CSVParse;
import com.triestpa.minicityguide.CityContent.City;
import com.triestpa.minicityguide.CityContent.CityContentManager;

import java.io.InputStream;
import java.util.ArrayList;

//ListFragment containing the list of cities
public class CityListFragment extends ListFragment {

    private static final String STATE_ACTIVATED_POSITION = "activated_position";
    private Callbacks mCallbacks = inactiveCallbacks;

    private int mActivatedPosition = ListView.INVALID_POSITION;

    public interface Callbacks {
        public void onItemSelected(int id);
    }

    private static Callbacks inactiveCallbacks = new Callbacks() {
        @Override
        public void onItemSelected(int id) {
        //Do nothing
       }
    };

    public CityListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Read city list from csv file if it has not yet been read
        if (CityContentManager.getCities() == null || CityContentManager.getCities().isEmpty()) {
            InputStream inputStream = getResources().openRawResource(R.raw.citydata);
            CSVParse csvFile = new CSVParse(inputStream);
            ArrayList<City> cityList = csvFile.readCities();
            CityContentManager.setCities(cityList);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.setBackgroundColor(getResources().getColor(R.color.grey_1000));

        ArrayList<City> beans =
                new ArrayList<City>(CityContentManager.getCities());

        //Populate the list with cities
        CityListAdapter adapter = new CityListAdapter(getActivity(), R.layout.row_city, CityContentManager.getCities());
        setListAdapter(adapter);

        // Restore the previously serialized activated item position.
        if (savedInstanceState != null
                && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
            setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION));
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // Activities containing this fragment must implement its callbacks.
        if (!(activity instanceof Callbacks)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        }

        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = inactiveCallbacks;
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);
        City thisCity = CityContentManager.getCities().get(position);
        mCallbacks.onItemSelected(thisCity.getId());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mActivatedPosition != ListView.INVALID_POSITION) {
            // Serialize and persist the activated item position.
            outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
        }
    }

    /**
     * Turns on activate-on-click mode. When this mode is on, list items will be
     * given the 'activated' state when touched.
     */
    public void setActivateOnItemClick(boolean activateOnItemClick) {
        // When setting CHOICE_MODE_SINGLE, ListView will automatically
        // give items the 'activated' state when touched.
        getListView().setChoiceMode(activateOnItemClick
                ? ListView.CHOICE_MODE_SINGLE
                : ListView.CHOICE_MODE_NONE);
    }

    private void setActivatedPosition(int position) {
        if (position == ListView.INVALID_POSITION) {
            getListView().setItemChecked(mActivatedPosition, false);
        } else {
            getListView().setItemChecked(position, true);
        }

        mActivatedPosition = position;
    }
}
