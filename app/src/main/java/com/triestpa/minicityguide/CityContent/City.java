package com.triestpa.minicityguide.CityContent;

/* Class to store details of the city */
public class City {
    private String name;
    private String country;
    private String picURL;
    private String description;

    private int id;

    public City(String name, String country, String picURL, String description) {
        this.name = name;
        this.country = country;
        this.picURL = picURL;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPicURL() {
        return picURL;
    }

    public void setPicURL(String picURL) {
        this.picURL = picURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

}
