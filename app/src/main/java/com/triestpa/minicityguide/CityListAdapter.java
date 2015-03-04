package com.triestpa.minicityguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.triestpa.minicityguide.CityContent.City;

import java.util.List;

public class CityListAdapter extends ArrayAdapter<City> {
    private Context mContext;
    private List<City> mData;

    public CityListAdapter(Context a, int layoutId,
                             List<City> data) {
        super(a, layoutId, data);
        mContext = a;
        mData = data;
    }

    private class ViewHolder {
        TextView name;
        TextView country;
        ImageView cityImage;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        City city = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.city_row, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.city_name);
            viewHolder.country = (TextView) convertView.findViewById(R.id.country_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
        viewHolder.name.setText(city.getName());
        viewHolder.country.setText(city.getCountry());
        // Return the completed view to render on screen
        return convertView;
    }
}
