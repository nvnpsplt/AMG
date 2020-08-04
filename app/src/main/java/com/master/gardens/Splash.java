package com.master.gardens;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;


public class Splash extends Activity {

    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(Splash.this, Login.class);
                startActivity(intent);
                finish();
            }

        },2000);

    }
}