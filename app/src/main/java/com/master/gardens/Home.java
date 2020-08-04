package com.master.gardens;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.maps.android.clustering.ClusterManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class Home extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private JSONArray jsonArray;
    private List<MarkerOptions> listMarkers = new ArrayList<>();
    private ClusterManager<MarkerClusterItem> clusterManager;

    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        auth = FirebaseAuth.getInstance();

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        authListener = firebaseAuth -> {
            FirebaseUser user1 = firebaseAuth.getCurrentUser();
            if (user1 == null) {
                startActivity(new Intent(Home.this, Login.class));
                finish();
            }
        };

        jsonArray = readAssets();

        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.googleMap);
        supportMapFragment.getMapAsync(this);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        Intent a = new Intent(Home.this, Home.class);
                        startActivity(a);
                        break;
                    case R.id.search:
                        Intent b = new Intent(Home.this, Search.class);
                        startActivity(b);
                        break;
                    case R.id.about:
                        Intent c = new Intent(Home.this, About.class);
                        startActivity(c);
                        break;
                }
                return false;
            }
        });

    }





    private JSONArray readAssets() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getAssets().open("places.json")));
            String line = "";
            StringBuilder stringBuilder = new StringBuilder("");
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return new JSONArray(stringBuilder.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JSONArray();
    }

    private void addMarkers() {
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                double lat = jsonObject.getDouble("lat");
                double lng = jsonObject.getDouble("lng");
                String name = jsonObject.getString("name");
                String gatewall = jsonObject.getString("gatewall");


                // if (name.equals("Benisha") || name.equals("Himayuddin")) {
                gatewall = "Gate valve : " + gatewall;
                LatLng latLng = new LatLng(lat, lng);
                MarkerOptions markerOptions = new MarkerOptions().position(latLng).title(name).snippet(gatewall);
                listMarkers.add(markerOptions);
                //  }
            }
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(listMarkers.get(0).getPosition(), 18.0f));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupClusterManager() {
        setRenderer();
        addClusterItems();
        setClusterManagerClickListener();
        googleMap.setOnCameraIdleListener(clusterManager);
        googleMap.setOnMarkerClickListener(clusterManager);
        clusterManager.cluster();
    }

    private void setRenderer() {
        MarkerClusterRenderer<MarkerClusterItem> clusterRenderer = new MarkerClusterRenderer<>(this, googleMap, clusterManager);
        clusterManager.setRenderer(clusterRenderer);
    }

    private void setClusterManagerClickListener() {
        clusterManager.setOnClusterClickListener(cluster -> {
            Collection<MarkerClusterItem> listItems = cluster.getItems();
            List<String> listNames = new ArrayList<>();
            for (MarkerClusterItem item : listItems) {
                listNames.add(item.getTitle());
            }
            googleMap.animateCamera(CameraUpdateFactory.newLatLng(cluster.getPosition()), new GoogleMap.CancelableCallback() {
                @Override
                public void onFinish() {
                    ListViewDialog listViewDialog = new ListViewDialog(Home.this, listNames);
                    listViewDialog.showDialog();
                }

                @Override
                public void onCancel() {
                }
            });
            return true;
        });
    }

    private void addClusterItems() {
        for (MarkerOptions markerOptions : listMarkers) {
            BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.tree);
            MarkerClusterItem clusterItem = new MarkerClusterItem(markerOptions.getPosition(), markerOptions.getTitle(), markerOptions.getIcon(), markerOptions.getSnippet());

            clusterManager.addItem(clusterItem);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        clusterManager = new ClusterManager<>(this, googleMap);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        googleMap.setMyLocationEnabled(true);
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.getUiSettings().setMapToolbarEnabled(true);
        googleMap.getUiSettings().setZoomGesturesEnabled(true);
        googleMap.getUiSettings().setScrollGesturesEnabled(true);
        googleMap.getUiSettings().setTiltGesturesEnabled(true);
        googleMap.getUiSettings().setRotateGesturesEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.setMyLocationEnabled(true);
        addMarkers();
        setupClusterManager();

    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }
    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toasty.Config.getInstance()
                .setToastTypeface(Typeface.createFromAsset(getAssets(), "circular.ttf"))
                .allowQueue(false)
                .apply();
        Toasty.success(Home.this, "Please click BACK again to exit...!", Toast.LENGTH_SHORT, true).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}