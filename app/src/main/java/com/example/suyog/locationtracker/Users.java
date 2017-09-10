package com.example.suyog.locationtracker;

/**
 * Created by SUYOG on 9/9/2017.
 */

public class Users {
    public String name;
    public String mobNo;
    public String state;
    public String country;

    public Users(){

    }

    public Users(String name, String mobNo, String state, String country) {
        this.name = name;
        this.mobNo = mobNo;
        this.state = state;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public String getMobNo() {
        return mobNo;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }
}
