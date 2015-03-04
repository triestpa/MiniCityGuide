package com.triestpa.minicityguide.CityContent;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by patrick on 3/3/15.
 */
public class CSVParse {
    private final String TAG = CSVParse.class.getSimpleName();
    InputStream inputStream;

    public CSVParse(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public ArrayList<City> readCities() {
        City thisCity;
        ArrayList resultList = new ArrayList<City>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;
            while ((csvLine = reader.readLine()) != null) {
                //replace the quotation marks in the string
                csvLine = csvLine.replace("\"", "");
                String[] row = csvLine.split(",");
                thisCity = new City(row[0], row[1], row[2], row[3]);
                resultList.add(thisCity);
            }
        } catch (IOException e) {
            Log.e(TAG, "Error reading CSV File: " + e.getMessage());
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                Log.e(TAG, "Error closing input stream: " + e.getMessage());
            }
        }
        return resultList;
    }
}
