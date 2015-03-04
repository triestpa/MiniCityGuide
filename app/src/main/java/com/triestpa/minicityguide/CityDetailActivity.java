package com.triestpa.minicityguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

//This is only used if the app is not in 2 pane mode
public class CityDetailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Add the detail fragment, if the page has not already been rendered
        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putInt(CityDetailFragment.ARG_ITEM_ID,
                    getIntent().getIntExtra(CityDetailFragment.ARG_ITEM_ID, 0));
            CityDetailFragment fragment = new CityDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.city_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpTo(this, new Intent(this, CityListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
