package com.triestpa.minicityguide;

import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.triestpa.minicityguide.CityContent.City;

import java.util.List;

/* ListAdapter to render the city list */
public class CityListAdapter extends ArrayAdapter<City> {
    private Context mContext;
    private List<City> mData;
    private int mWidth, mHeight;

    public CityListAdapter(Context a, int layoutId,
                             List<City> data) {
        super(a, layoutId, data);
        mContext = a;
        mData = data;

        //Get Screen width and height
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        mWidth = display.getWidth();
        mHeight = display.getHeight();
    }

    private class ViewHolder {
        TextView name;
        TextView country;
        ImageView cityImage;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        City city = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_city, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.city_name_list);
            viewHolder.country = (TextView) convertView.findViewById(R.id.country_name_list);
            viewHolder.cityImage = (ImageView) convertView.findViewById(R.id.city_image_list);
            viewHolder.cityImage.setMinimumWidth(mWidth);
            viewHolder.cityImage.setMinimumHeight(mHeight/3);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Populate the data into the template view using the data object
        viewHolder.name.setText(city.getName());
        viewHolder.country.setText(city.getCountry());

        //Load image, and size to fill 1/3 of screen height
        Picasso.with(mContext).load(city.getPicURL()).resize(mWidth, mHeight/3).centerCrop().into(viewHolder.cityImage);


        // Return the completed view to render on screen
        return convertView;
    }
}
