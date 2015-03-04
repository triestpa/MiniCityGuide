package com.triestpa.minicityguide.CityContent;

import java.util.ArrayList;

public class CityContentManager {

    private static ArrayList<City> citiesInstance;

    protected CityContentManager() {
    }

    public static ArrayList<City> getCities() {
        if(citiesInstance == null) {
            citiesInstance = new ArrayList<City>();
        }
        return citiesInstance;
    }

    public static void setCities(ArrayList<City> cities) {
        citiesInstance = cities;
    }

    public static void addCity(City city) {
        if (citiesInstance == null) {
            citiesInstance = new ArrayList<City>();
        }
        citiesInstance.add(city);
    }

    public static City getCity(String name) {
        for (int i = 0; i < citiesInstance.size(); i++) {
            if (citiesInstance.get(i).getName().contentEquals(name)) {
                return citiesInstance.get(i);
            }
        }
        return null;
    }
}
