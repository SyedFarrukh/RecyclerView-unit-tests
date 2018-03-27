package com.forzaassignment.Model;

/**
 * Created by Farrukh on 2/10/2018.
 */

public class TeamModel {
    private String name;
    private String national;
    private String country_name;

    public TeamModel(String name, String national, String country_name) {
        this.name = name;
        this.national = national;
        this.country_name = country_name;
    }

    public String getName() {
        return name;
    }

    public String getNational() {
        return national;
    }

    public String getCountryName() {
        return country_name;
    }
}
