package com.santos.barbeariafacil.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.santos.barbeariafacil.R;
import com.santos.barbeariafacil.config.ConfigFirebase;

public class Splash extends AppCompatActivity {
    private FirebaseAuth autenticacao;
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
    public  void verificarUsuarioLogado(){
        autenticacao= ConfigFirebase.getFirebaseAutenticaca();
        if(autenticacao.getCurrentUser() != null){

        }


    }
    public void openPrincipal(){
        Intent intent = new Intent(Splash.this,Principal.class);
        startActivity(intent);
    }

}