package com.example.suyog.locationtracker;

import java.util.Date;

/**
 * Created by SUYOG on 9/9/2017.
 */

public class Remainder {

    String id;
    String remianderName;
    String remainderDate;
    String remainderTime;
    String placename;
    String placeaddress;
    Double latitude;
    Double logitude;

    public Remainder() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        id = id;
    }

    public String getRemianderName() {
        return remianderName;
    }

    public void setRemianderName(String remianderName) {
        this.remianderName = remianderName;
    }

    public String getRemainderDate() {
        return remainderDate;
    }

    public void setRemainderDate(String remainderDate) {
        this.remainderDate = remainderDate;
    }

    public String getRemainderTime() {
        return remainderTime;
    }

    public void setRemainderTime(String remainderTime) {
        this.remainderTime = remainderTime;
    }

    public String getPlacename() {
        return placename;
    }

    public void setPlacename(String placename) {
        this.placename = placename;
    }

    public String getPlaceaddress() {
        return placeaddress;
    }

    public void setPlaceaddress(String placeaddress) {
        this.placeaddress = placeaddress;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLogitude() {
        return logitude;
    }

    public void setLogitude(Double logitude) {
        this.logitude = logitude;
    }
}
