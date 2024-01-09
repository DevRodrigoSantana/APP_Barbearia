package com.santos.barbeariafacil.view;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.santos.barbeariafacil.R;

public class Principal extends AppCompatActivity {

    private FloatingActionButton fabAdd, fabAgenda, fabCorte;
    private Animation rotateOpenAnimation, rotateCloseAnimation, fromBottomAnimation, toBottomAnimation;
    private boolean isMenuOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

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
