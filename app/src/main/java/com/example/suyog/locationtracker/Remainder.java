package com.example.suyog.locationtracker;

import java.util.Date;

/**
 * Created by SUYOG on 9/9/2017.
 */

public class Remainder {
    String remianderName;
    String remainderDate;
    String remainderTime;
    String placename;
    String placeaddress;
    Double latitude;
    Double logitude;

    public Remainder(String remianderName, String remainderDate, String remainderTime, String placename, String placeaddress, Double latitude, Double logitude) {
        this.remianderName = remianderName;
        this.remainderDate = remainderDate;
        this.remainderTime = remainderTime;
        this.placename = placename;
        this.placeaddress = placeaddress;
        this.latitude = latitude;
        this.logitude = logitude;
    }

    public Remainder() {
    }



    public String getRemianderName() {
        return remianderName;
    }

    public String getRemainderDate() {
        return remainderDate;
    }

    public String getRemainderTime() {
        return remainderTime;
    }

    public String getPlacename() {
        return placename;
    }

    public String getPlaceaddress() {
        return placeaddress;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLogitude() {
        return logitude;
    }
}
