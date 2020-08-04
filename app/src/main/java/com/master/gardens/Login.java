package com.master.gardens;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import es.dmoral.toasty.Toasty;

public class Login extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(Login.this, Home.class));
            finish();
        }

        setContentView(R.layout.login);

        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnLogin = (Button) findViewById(R.id.btn_login);

        auth = FirebaseAuth.getInstance();
        
        if (ContextCompat.checkSelfPermission(Login.this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(Login.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)){
                ActivityCompat.requestPermissions(Login.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }else{
                ActivityCompat.requestPermissions(Login.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toasty.info(Login.this,  "Enter email address...!", Toast.LENGTH_SHORT, true).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toasty.info(Login.this,  "Enter password...!", Toast.LENGTH_SHORT, true).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    if (password.length() < 6) {
                                        inputPassword.setError(getString(R.string.minimum_password));
                                    } else {
                                        Toasty.info(Login.this,  getString(R.string.auth_failed), Toast.LENGTH_SHORT, true).show();
                                    }
                                } else {
                                    Intent intent = new Intent(Login.this, Home.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        });

    }


    public void checkPermission(String permission, int requestCode)
    {

        if (ContextCompat.checkSelfPermission(
                Login.this,
                permission)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat
                    .requestPermissions(
                            Login.this,
                            new String[] { permission },
                            requestCode);
            Toasty.success(Login.this, "Location Granted.", Toast.LENGTH_SHORT, true).show();
        }
        else {
            Toasty.success(Login.this, "Location Granted.", Toast.LENGTH_SHORT, true).show();
        }
    }

}

