package com.santos.barbeariafacil.view;


import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.santos.barbeariafacil.R;
import com.santos.barbeariafacil.config.ConfigFirebase;
import com.santos.barbeariafacil.controller.TemporizadorUserLogin;

public class Principal extends AppCompatActivity {

    private FloatingActionButton fabAdd, fabAgenda, fabCorte;
    private Animation rotateOpenAnimation, rotateCloseAnimation, fromBottomAnimation, toBottomAnimation;
    private boolean isMenuOpen = false;
    private TemporizadorUserLogin temporizadorUserLogin;

    private FirebaseAuth autenticacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        temporizadorUserLogin = new TemporizadorUserLogin(new Runnable() {
            @Override
            public void run() {
                autenticacao = ConfigFirebase.getFirebaseAutenticaca();
                autenticacao.signOut();
                finish();


            }
        });

        fabAdd = findViewById(R.id.fab_add);
        fabAgenda = findViewById(R.id.fab_agenda);
        fabCorte = findViewById(R.id.fab_corte);

        rotateOpenAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate_open_anim);
        rotateCloseAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate_close_anim);
        fromBottomAnimation = AnimationUtils.loadAnimation(this, R.anim.from_bottom_anim);
        toBottomAnimation = AnimationUtils.loadAnimation(this, R.anim.to_bottom_anim);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                toggleMenu();
                temporizadorUserLogin.reiniciarContagemRegressiva(); // Reiniciar o temporizador ao interagir com a tela
            }
        });

        fabAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        fabCorte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        temporizadorUserLogin.iniciarContagemRegressiva(); // Iniciar o temporizador ao retomar a Activity
    }

    @Override
    protected void onPause() {
        super.onPause();
        temporizadorUserLogin.pararContagemRegressiva(); // Parar o temporizador ao pausar a Activity
    }


    private void toggleMenu() {
        if (isMenuOpen) {
            closeMenu();
        } else {
            openMenu();
        }
        isMenuOpen = !isMenuOpen;
    }

    private void openMenu() {
        fabAdd.startAnimation(rotateOpenAnimation);
        fabAgenda.startAnimation(fromBottomAnimation);
        fabCorte.startAnimation(fromBottomAnimation);

        fabAgenda.setVisibility(View.VISIBLE);
        fabCorte.setVisibility(View.VISIBLE);
    }

    private void closeMenu() {
        fabAdd.startAnimation(rotateCloseAnimation);
        fabAgenda.startAnimation(toBottomAnimation);
        fabCorte.startAnimation(toBottomAnimation);

        fabAgenda.setVisibility(View.INVISIBLE);
        fabCorte.setVisibility(View.INVISIBLE);
    }




}
