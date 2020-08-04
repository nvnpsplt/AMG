package com.master.gardens;

import android.content.Context;
import com.google.android.gms.maps.GoogleMap;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;

public class MarkerClusterRenderer<T extends ClusterItem> extends DefaultClusterRenderer<T> {

    public MarkerClusterRenderer(Context context, GoogleMap googleMap, ClusterManager<T> clusterManager){
        super(context, googleMap, clusterManager);
    }
}