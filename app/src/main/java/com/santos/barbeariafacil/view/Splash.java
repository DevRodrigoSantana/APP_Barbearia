package com.santos.barbeariafacil.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.santos.barbeariafacil.R;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            openActivityLogin();
            }
        },3000);
    }

    private void openActivityLogin(){
        Intent intent = new Intent(Splash.this,Login.class);

        startActivity(intent);
        finish();
    }
}