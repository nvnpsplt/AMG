package com.master.gardens;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Search extends AppCompatActivity {

    Spinner spinnerfruit;
    Spinner spinnergatewall;

    String[] FRUITS = {"All","Benisha","Sindhura","Himayuddin","Neelam","Khadar","Pickle Mango","Bengalur","Lemon","Orange","Alla Neredu","Amla","Chickoo","Guava","Jack Fruit","Juice Mango","Nara Dhabba","Neelesha","Punasa","Rama Phalam"};
    String[] GATEWALL = {"All","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17"};
    String SpinnerFruitValue;
    String SpinnerGatewallValue;
    Button Filter;
    Intent intent;

    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        auth = FirebaseAuth.getInstance();

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        authListener = firebaseAuth -> {
            FirebaseUser user1 = firebaseAuth.getCurrentUser();
            if (user1 == null) {
                startActivity(new Intent(Search.this, Login.class));
                finish();
            }
        };
        spinnerfruit =(Spinner)findViewById(R.id.spinnerfruit);
        spinnergatewall =(Spinner)findViewById(R.id.spinnergatewall);

        Filter = (Button)findViewById(R.id.btn);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.getMenu().findItem(R.id.search).setChecked(true);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        Intent a = new Intent(Search.this,Home.class);
                        startActivity(a);
                        break;
                    case R.id.search:
                        Intent b = new Intent(Search.this,Search.class);
                        startActivity(b);
                        break;
                    case R.id.about:
                        Intent c = new Intent(Search.this, About.class);
                        startActivity(c);
                        break;

                }
                return false;
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Search.this, android.R.layout.simple_list_item_1, FRUITS);
        ArrayAdapter<String> adaptergatewall = new ArrayAdapter<String>(Search.this, android.R.layout.simple_list_item_1, GATEWALL);

        spinnerfruit.setAdapter(adapter);
        spinnergatewall.setAdapter(adaptergatewall);



        spinnerfruit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                SpinnerFruitValue = (String)spinnerfruit.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        spinnergatewall.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                SpinnerGatewallValue =  (String)spinnergatewall .getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        Filter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                switch(SpinnerFruitValue){
                    case "All":
                        if(SpinnerGatewallValue.equals("1")) {
                            intent = new Intent(Search.this, Gatewall.class);
                            intent.putExtra("GValue", SpinnerGatewallValue);
                            startActivity(intent);
                            break;
                        }
                        else if(SpinnerGatewallValue.equals("2")) {
                            intent = new Intent(Search.this, Gatewall.class);
                            intent.putExtra("GValue", SpinnerGatewallValue);
                            startActivity(intent);
                            break;
                        }
                        else if(SpinnerGatewallValue.equals("3")) {
                            intent = new Intent(Search.this, Gatewall.class);
                            intent.putExtra("GValue", SpinnerGatewallValue);
                            startActivity(intent);
                            break;
                        }
                        else if(SpinnerGatewallValue.equals("4")) {
                            intent = new Intent(Search.this, Gatewall.class);
                            intent.putExtra("GValue", SpinnerGatewallValue);
                            startActivity(intent);
                            break;
                        }
                        else if(SpinnerGatewallValue.equals("5")) {
                            intent = new Intent(Search.this, Gatewall.class);
                            intent.putExtra("GValue", SpinnerGatewallValue);
                            startActivity(intent);
                            break;
                        }
                        else if(SpinnerGatewallValue.equals("6")) {
                            intent = new Intent(Search.this, Gatewall.class);
                            intent.putExtra("GValue", "6/7");
                            startActivity(intent);
                            break;
                        }
                        else if(SpinnerGatewallValue.equals("7")) {
                            intent = new Intent(Search.this, Gatewall.class);
                            intent.putExtra("GValue", "6/7");
                            startActivity(intent);
                            break;
                        }
                        else if(SpinnerGatewallValue.equals("8")) {
                            intent = new Intent(Search.this, Gatewall.class);
                            intent.putExtra("GValue", SpinnerGatewallValue);
                            startActivity(intent);
                            break;
                        }
                        else if(SpinnerGatewallValue.equals("9")) {
                            intent = new Intent(Search.this, Gatewall.class);
                            intent.putExtra("GValue", SpinnerGatewallValue);
                            startActivity(intent);
                            break;
                        }
                        else if(SpinnerGatewallValue.equals("10")) {
                            intent = new Intent(Search.this, Gatewall.class);
                            intent.putExtra("GValue", "10/11");
                            startActivity(intent);
                            break;
                        }
                        else if(SpinnerGatewallValue.equals("11")) {
                            intent = new Intent(Search.this, Gatewall.class);
                            intent.putExtra("GValue", "10/11");
                            startActivity(intent);
                            break;
                        }
                        else if(SpinnerGatewallValue.equals("12")) {
                            intent = new Intent(Search.this, Gatewall.class);
                            intent.putExtra("GValue", SpinnerGatewallValue);
                            startActivity(intent);
                            break;
                        }
                        else if(SpinnerGatewallValue.equals("13")) {
                            intent = new Intent(Search.this, Gatewall.class);
                            intent.putExtra("GValue", SpinnerGatewallValue);
                            startActivity(intent);
                            break;
                        }
                        else if(SpinnerGatewallValue.equals("14")) {
                            intent = new Intent(Search.this, Gatewall.class);
                            intent.putExtra("GValue", SpinnerGatewallValue);
                            startActivity(intent);
                            break;
                        }
                        else if(SpinnerGatewallValue.equals("15")) {
                            intent = new Intent(Search.this, Gatewall.class);
                            intent.putExtra("GValue", SpinnerGatewallValue);
                            startActivity(intent);
                            break;
                        }
                        else if(SpinnerGatewallValue.equals("16")) {
                            intent = new Intent(Search.this, Gatewall.class);
                            intent.putExtra("GValue", SpinnerGatewallValue);
                            startActivity(intent);
                            break;
                        }
                        else if(SpinnerGatewallValue.equals("17")) {
                            intent = new Intent(Search.this, Gatewall.class);
                            intent.putExtra("GValue", SpinnerGatewallValue);
                            startActivity(intent);
                            break;
                        }
                        else {
                            intent = new Intent(Search.this, Home.class);
                            intent.putExtra("GValue", SpinnerGatewallValue);
                            startActivity(intent);
                            break;
                        }

                    case "Benisha":
                        if (SpinnerGatewallValue.equals("All")) {
                            Intent intent = new Intent(getApplicationContext(), Fruit.class);
                            intent.putExtra("FruitName", SpinnerFruitValue);
                            startActivity(intent);
                            break;
                        }
                        else{
                            Intent intent = new Intent(getApplicationContext(), FruitGatewall.class);
                            intent.putExtra("GValue", SpinnerGatewallValue);
                            intent.putExtra("FruitName", SpinnerFruitValue);
                            startActivity(intent);
                            break;

                        }
                    case "Sindhura":
                        if (SpinnerGatewallValue.equals("All")) {
                            Intent intent = new Intent(getApplicationContext(), Fruit.class);
                            intent.putExtra("FruitName", SpinnerFruitValue);
                            startActivity(intent);
                            break;
                        }
                        else{
                            Intent intent = new Intent(getApplicationContext(), FruitGatewall.class);
                            intent.putExtra("GValue", SpinnerGatewallValue);
                            intent.putExtra("FruitName", SpinnerFruitValue);
                            startActivity(intent);
                            break;

                        }

                    case "Himayuddin":
                        if (SpinnerGatewallValue.equals("All")) {
                            Intent intent = new Intent(getApplicationContext(), Fruit.class);
                            intent.putExtra("FruitName", SpinnerFruitValue);
                            startActivity(intent);
                            break;
                        }
                        else{
                            Intent intent = new Intent(getApplicationContext(), FruitGatewall.class);
                            intent.putExtra("GValue", SpinnerGatewallValue);
                            intent.putExtra("FruitName", SpinnerFruitValue);
                            startActivity(intent);
                            break;

                        }
                    case "Neelam":
                        if (SpinnerGatewallValue.equals("All")) {
                            Intent intent = new Intent(getApplicationContext(), Fruit.class);
                            intent.putExtra("FruitName", SpinnerFruitValue);
                            startActivity(intent);
                            break;
                        }
                        else{
                            Intent intent = new Intent(getApplicationContext(), FruitGatewall.class);
                            intent.putExtra("GValue", SpinnerGatewallValue);
                            intent.putExtra("FruitName", SpinnerFruitValue);
                            startActivity(intent);
                            break;

                        }

                    case "Khadar":
                        if (SpinnerGatewallValue.equals("All")) {
                            Intent intent = new Intent(getApplicationContext(), Fruit.class);
                            intent.putExtra("FruitName", SpinnerFruitValue);
                            startActivity(intent);
                            break;
                        }
                        else{
                            Intent intent = new Intent(getApplicationContext(), FruitGatewall.class);
                            intent.putExtra("GValue", SpinnerGatewallValue);
                            intent.putExtra("FruitName", SpinnerFruitValue);
                            startActivity(intent);
                            break;

                        }

                    case "Pickle Mango":
                        if (SpinnerGatewallValue.equals("All")) {
                            Intent intent = new Intent(getApplicationContext(), Fruit.class);
                            intent.putExtra("FruitName", SpinnerFruitValue);
                            startActivity(intent);
                            break;
                        }
                        else{
                            Intent intent = new Intent(getApplicationContext(), FruitGatewall.class);
                            intent.putExtra("GValue", SpinnerGatewallValue);
                            intent.putExtra("FruitName", SpinnerFruitValue);
                            startActivity(intent);
                            break;

                        }

                    case "Bengalur":
                        if (SpinnerGatewallValue.equals("All")) {
                            Intent intent = new Intent(getApplicationContext(), Fruit.class);
                            intent.putExtra("FruitName", SpinnerFruitValue);
                            startActivity(intent);
                            break;
                        }
                        else{
                            Intent intent = new Intent(getApplicationContext(), FruitGatewall.class);
                            intent.putExtra("GValue", SpinnerGatewallValue);
                            intent.putExtra("FruitName", SpinnerFruitValue);
                            startActivity(intent);
                            break;

                        }

                    case "Lemon":
                        if (SpinnerGatewallValue.equals("All")) {
                            Intent intent = new Intent(getApplicationContext(), Fruit.class);
                            intent.putExtra("FruitName", SpinnerFruitValue);
                            startActivity(intent);
                            break;
                        }
                        else{
                            Intent intent = new Intent(getApplicationContext(), FruitGatewall.class);
                            intent.putExtra("GValue", SpinnerGatewallValue);
                            intent.putExtra("FruitName", SpinnerFruitValue);
                            startActivity(intent);
                            break;

                        }
                    case "Orange":
                        if (SpinnerGatewallValue.equals("All")) {
                            Intent intent = new Intent(getApplicationContext(), Fruit.class);
                            intent.putExtra("FruitName", SpinnerFruitValue);
                            startActivity(intent);
                            break;
                        }
                        else{
                            Intent intent = new Intent(getApplicationContext(), FruitGatewall.class);
                            intent.putExtra("GValue", SpinnerGatewallValue);
                            intent.putExtra("FruitName", SpinnerFruitValue);
                            startActivity(intent);
                            break;

                        }
                    case "Alla Neredu":
                        if (SpinnerGatewallValue.equals("All")) {
                            Intent intent = new Intent(getApplicationContext(), Fruit.class);
                            intent.putExtra("FruitName", SpinnerFruitValue);
                            startActivity(intent);
                            break;
                        }
                        else{
                            Intent intent = new Intent(getApplicationContext(), FruitGatewall.class);
                            intent.putExtra("GValue", SpinnerGatewallValue);
                            intent.putExtra("FruitName", SpinnerFruitValue);
                            startActivity(intent);
                            break;

                        }
                    case "Amla":
                        if (SpinnerGatewallValue.equals("All")) {
                            Intent intent = new Intent(getApplicationContext(), Fruit.class);
                            intent.putExtra("FruitName", SpinnerFruitValue);
                            startActivity(intent);
                            break;
                        }
                        else{
                            Intent intent = new Intent(getApplicationContext(), FruitGatewall.class);
                            intent.putExtra("GValue", SpinnerGatewallValue);
                            intent.putExtra("FruitName", SpinnerFruitValue);
                            startActivity(intent);
                            break;

                        }
                    case "Chickoo":
                        if (SpinnerGatewallValue.equals("All")) {
                            Intent intent = new Intent(getApplicationContext(), Fruit.class);
                            intent.putExtra("FruitName", SpinnerFruitValue);
                            startActivity(intent);
                            break;
                        }
                        else{
                            Intent intent = new Intent(getApplicationContext(), FruitGatewall.class);
                            intent.putExtra("GValue", SpinnerGatewallValue);
                            intent.putExtra("FruitName", SpinnerFruitValue);
                            startActivity(intent);
                            break;

                        }
                    case "Guava":
                        if (SpinnerGatewallValue.equals("All")) {
                            Intent intent = new Intent(getApplicationContext(), Fruit.class);
                            intent.putExtra("FruitName", SpinnerFruitValue);
                            startActivity(intent);
                            break;
                        }
                        else{
                            Intent intent = new Intent(getApplicationContext(), FruitGatewall.class);
                            intent.putExtra("GValue", SpinnerGatewallValue);
                            intent.putExtra("FruitName", SpinnerFruitValue);
                            startActivity(intent);
                            break;

                        }
                    case "Jack Fruit":
                        if (SpinnerGatewallValue.equals("All")) {
                            Intent intent = new Intent(getApplicationContext(), Fruit.class);
                            intent.putExtra("FruitName", SpinnerFruitValue);
                            startActivity(intent);
                            break;
                        }
                        else{
                            Intent intent = new Intent(getApplicationContext(), FruitGatewall.class);
                            intent.putExtra("GValue", SpinnerGatewallValue);
                            intent.putExtra("FruitName", SpinnerFruitValue);
                            startActivity(intent);
                            break;

                        }
                    case "Juice Mango":
                        if (SpinnerGatewallValue.equals("All")) {
                            Intent intent = new Intent(getApplicationContext(), Fruit.class);
                            intent.putExtra("FruitName", SpinnerFruitValue);
                            startActivity(intent);
                            break;
                        }
                        else{
                            Intent intent = new Intent(getApplicationContext(), FruitGatewall.class);
                            intent.putExtra("GValue", SpinnerGatewallValue);
                            intent.putExtra("FruitName", SpinnerFruitValue);
                            startActivity(intent);
                            break;

                        }
                    case "Nara Dhabba":
                        if (SpinnerGatewallValue.equals("All")) {
                            Intent intent = new Intent(getApplicationContext(), Fruit.class);
                            intent.putExtra("FruitName", SpinnerFruitValue);
                            startActivity(intent);
                            break;
                        }
                        else{
                            Intent intent = new Intent(getApplicationContext(), FruitGatewall.class);
                            intent.putExtra("GValue", SpinnerGatewallValue);
                            intent.putExtra("FruitName", SpinnerFruitValue);
                            startActivity(intent);
                            break;

                        }
                    case "Neelesha":
                        if (SpinnerGatewallValue.equals("All")) {
                            Intent intent = new Intent(getApplicationContext(), Fruit.class);
                            intent.putExtra("FruitName", SpinnerFruitValue);
                            startActivity(intent);
                            break;
                        }
                        else{
                            Intent intent = new Intent(getApplicationContext(), FruitGatewall.class);
                            intent.putExtra("GValue", SpinnerGatewallValue);
                            intent.putExtra("FruitName", SpinnerFruitValue);
                            startActivity(intent);
                            break;

                        }
                    case "Punasa":
                        if (SpinnerGatewallValue.equals("All")) {
                            Intent intent = new Intent(getApplicationContext(), Fruit.class);
                            intent.putExtra("FruitName", SpinnerFruitValue);
                            startActivity(intent);
                            break;
                        }
                        else{
                            Intent intent = new Intent(getApplicationContext(), FruitGatewall.class);
                            intent.putExtra("GValue", SpinnerGatewallValue);
                            intent.putExtra("FruitName", SpinnerFruitValue);
                            startActivity(intent);
                            break;

                        }
                    case "Rama Phalam":
                        if (SpinnerGatewallValue.equals("All")) {
                            Intent intent = new Intent(getApplicationContext(), Fruit.class);
                            intent.putExtra("FruitName", SpinnerFruitValue);
                            startActivity(intent);
                            break;
                        }
                        else{
                            Intent intent = new Intent(getApplicationContext(), FruitGatewall.class);
                            intent.putExtra("GValue", SpinnerGatewallValue);
                            intent.putExtra("FruitName", SpinnerFruitValue);
                            startActivity(intent);
                            break;

                        }

                        // "Alla Neredu","Amla","Chickoo","Guava","Jack Fruit","Juice Mango","Nara Dhabba","Neelesha","Punasa","Rama Phalam"};
                    default:
                        Intent intent = new Intent(getApplicationContext(), Home.class);
                        intent.putExtra("GValue", SpinnerGatewallValue);
                        intent.putExtra("FruitName", SpinnerFruitValue);
                        startActivity(intent);
                        break;

                }

            }
        });

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
}