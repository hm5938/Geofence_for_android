package com.hyem.Geofence_for_android;

public class GPSStruct {
    public Double lat;
    public Double lng;
    public Float accuracy;

    public GPSStruct(Double lat, Double lng, Float accuracy){
        this.lat = lat;
        this.lng =lng;
        this.accuracy = accuracy;
    }

}
