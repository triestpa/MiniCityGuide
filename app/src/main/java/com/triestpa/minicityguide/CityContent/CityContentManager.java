package com.triestpa.minicityguide.CityContent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


// Singleton class to serve city data to the rest of the app. NOT INTENDED TO BE THREADSAFE.
public class CityContentManager {

    //ArrayList to keep an ordered representation of the cities
    private static ArrayList<City> citiesInstance;

    //HashMap to find the city quickly based on the ID
    private static HashMap<Integer, City> cityMap;

    protected CityContentManager() {
    }

    public static ArrayList<City> getCities() {
        return citiesInstance;
    }

    public static void setCities(ArrayList<City> cities) {
        for (City city : cities) {
            addCity(city);
        }
    }

    // Add city to the instance of the city list, assigning it a random ID and placing in the hashmap
    public static void addCity(City city) {
        if (citiesInstance == null) {
            citiesInstance = new ArrayList<City>();
            cityMap = new HashMap<Integer,City>();
        }

        Random rand = new Random();
        int randMax = 100;
        //Dynamically set the max size of the id based on how many cities we're dealing with
        if (citiesInstance != null && citiesInstance.size() > 20) {
            randMax = citiesInstance.size() * 5;
        }

        //Generate a random number to act as the city id
        int id = rand.nextInt(randMax + 1);

        //make sure the id is not already assigned to another city
        while (cityMap.get(id) != null) {
            id = rand.nextInt(randMax);
        }

        city.setId(id);
        cityMap.put(id, city);
        citiesInstance.add(city);
    }


    public static City getCity(int id) {
        return cityMap.get(id);
    }

    public static void clearCities() {
        citiesInstance.clear();
        cityMap.clear();
    }
}
