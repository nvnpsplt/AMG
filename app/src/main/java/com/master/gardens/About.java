package com.master.gardens;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import es.dmoral.toasty.Toasty;


public class About extends AppCompatActivity {
    private Button signOut;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    private ImageView facebook;
    private ImageView instagram;
    private ImageView whatsapp;
    private ImageView mappin;
    private ImageView gmail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        signOut = findViewById(R.id.sign_out);

        facebook =findViewById(R.id.fb);
        instagram =findViewById(R.id.insta);
        whatsapp =findViewById(R.id.whatsapp);
        mappin =findViewById(R.id.map);
        gmail =findViewById(R.id.gmail);


        auth = FirebaseAuth.getInstance();

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        authListener = firebaseAuth -> {
            FirebaseUser user1 = firebaseAuth.getCurrentUser();
            if (user1 == null) {
                startActivity(new Intent(About.this, Login.class));
                finish();
            }
        };


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        navigation.getMenu().findItem(R.id.about).setChecked(true);

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.home:
                        Intent a = new Intent(About.this,Home.class);
                        startActivity(a);
                        break;
                    case R.id.search:
                        Intent b = new Intent(About.this,Search.class);
                        startActivity(b);
                        break;
                    case R.id.about:
                }
                return false;
            }
        });

        facebook.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                launchFacebook();

            }
        });

        instagram.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://instagram.com/_u/" + "atturus_mango_garden"));
                    intent.setPackage("com.instagram.android");
                    startActivity(intent);
                }
                catch (android.content.ActivityNotFoundException anfe)
                {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.instagram.com/" + "atturus_mango_garden")));
                }

            }
        });

        whatsapp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    String mobile = "+919440579724";
                    String msg = "Message Request From AMG APP: ";
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=" + mobile + "&text=" + msg)));
                } catch (Exception e) {
                    Toasty.info(About.this, "Whatsapp not installed.", Toast.LENGTH_SHORT, true).show();
                }
            }
            });

        gmail.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    String[] strTo = { "atturusmangogarden@gmail.com" };
                    intent.putExtra(Intent.EXTRA_EMAIL, strTo);
                    intent.putExtra(Intent.EXTRA_SUBJECT, "AMG_Contact_Request");
                    intent.putExtra(Intent.EXTRA_TEXT, "Contact Request Message from AMG:");
                    intent.setType("message/rfc822");
                    intent.setPackage("com.google.android.gm");
                    startActivity(intent);
                } catch(Exception e) {
                    Toasty.info(About.this, "Sorry...You don't have any mail app", Toast.LENGTH_SHORT, true).show();
                    e.printStackTrace();
                }
            }
        });


        mappin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse("geo:13.5232433,79.3743227"));
                    i.setClassName("com.google.android.apps.maps",
                            "com.google.android.maps.MapsActivity");
                    startActivity(i);
                } catch (Exception e) {
                    Toasty.info(About.this, "Google Maps App not installed.", Toast.LENGTH_SHORT, true).show();
                }
            }
        });





        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

    }
    //sign out method
    public void signOut() {
        auth.signOut();
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
    public final void launchFacebook() {
        final String urlFb = "fb://page/"+"655341055078340";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(urlFb));

        // If a Facebook app is installed, use it. Otherwise, launch
        // a browser
        final PackageManager packageManager = getPackageManager();
        List<ResolveInfo> list =
                packageManager.queryIntentActivities(intent,
                        PackageManager.MATCH_DEFAULT_ONLY);
        if (list.size() == 0) {
            final String urlBrowser = "https://www.facebook.com/"+"655341055078340";
            intent.setData(Uri.parse(urlBrowser));
        }

        startActivity(intent);
    }

}