package com.hyem.Geofence_for_android;


import com.google.android.gms.maps.model.LatLng;

public class GeofenceStruct {
    String id = "k-water";
    double latitude=36.40560204911154, longitude=127.39539620921205;

    LatLng latLng = new LatLng(36.36084949840239, 127.34469744225767);

    public String getKey(){
        return id;
    }

    public LatLng getValue(){
//        return new LatLng(latitude,longitude);
    return latLng;
    }
}
