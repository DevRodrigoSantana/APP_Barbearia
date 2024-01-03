package com.santos.barbeariafacil.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.santos.barbeariafacil.R;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView textoNaotemConta = findViewById(R.id.textNaoTemConta);
        String textoClicavel = getString(R.string.naoTemConta);

        int inicioParteClicavel = textoClicavel.indexOf("Cadastre-se");
        int fimParteClicavel = inicioParteClicavel + "Cadastre-se".length();

        SpannableString spannableString = new SpannableString(textoClicavel);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                openCadastro();


            }
        };


        spannableString.setSpan(clickableSpan, inicioParteClicavel, fimParteClicavel, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


        textoNaotemConta.setText(spannableString);


        textoNaotemConta.setMovementMethod(LinkMovementMethod.getInstance());

    }

    public void openCadastro(){
        Intent intent = new Intent(Login.this,Cadastro.class);
        startActivity(intent);

    }
}