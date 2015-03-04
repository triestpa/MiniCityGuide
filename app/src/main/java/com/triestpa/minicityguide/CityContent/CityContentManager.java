package com.triestpa.minicityguide.CityContent;

import java.util.ArrayList;

/**
 * Created by patrick on 3/3/15.
 */
public class CityContentManager {

    private static ArrayList<City> citiesInstance;

    protected CityContentManager() {

    }

    public ArrayList<City> getCities() {
        if(citiesInstance == null) {
            citiesInstance = new ArrayList<City>();
        }
        return citiesInstance;
    }

    public void addCity(City city) {
        if (citiesInstance == null) {
            citiesInstance = new ArrayList<City>();
        }
        else {
            citiesInstance.add(city);
        }
    }


}
