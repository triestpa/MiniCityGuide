package com.triestpa.minicityguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/* The launch activity, this determines if the app UI will run in two pane mode(for tablets),
*  instantiates the list fragment, and handles callbacks from the listview */
public class CityListActivity extends FragmentActivity implements CityListFragment.Callbacks {
    private final String TAG = CityListActivity.class.getSimpleName();
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);

        //Check if we should use tablet interface(split-view)
        if (findViewById(R.id.city_detail_container) != null) {
            mTwoPane = true;
            ((CityListFragment) getSupportFragmentManager().findFragmentById(R.id.city_list)).setActivateOnItemClick(true);
        }
    }

    //Callback from city list fragment
    @Override
    public void onItemSelected(int id) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putInt(CityDetailFragment.ARG_ITEM_ID, id);
            CityDetailFragment fragment = new CityDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.city_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, CityDetailActivity.class);
            detailIntent.putExtra(CityDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }
}
