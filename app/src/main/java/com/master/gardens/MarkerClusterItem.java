package com.master.gardens;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class MarkerClusterItem implements ClusterItem {

    private LatLng latLng;
    private String title;
    private String gatewall;


    public MarkerClusterItem(LatLng latLng, String title, BitmapDescriptor icon, String gatewall){
        this.latLng = latLng;
        this.title = title;
        this.gatewall = gatewall;
    }

    @Override
    public LatLng getPosition() {
        return latLng;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getSnippet() {
        return gatewall;
    }

}