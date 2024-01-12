package com.santos.barbeariafacil.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.santos.barbeariafacil.R;

public class BarbeiroCliente extends AppCompatActivity {

    private Button botaoBarbeiro, botaoCliente, botaoLogin;
    private Animation animacaoEntrada, animacaoSaida,animacaoSaidaLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barbeiro_cliente);

        botaoBarbeiro = findViewById(R.id.button_barbeiro);
        botaoCliente = findViewById(R.id.button_cliente);
        botaoLogin = findViewById(R.id.buttonVoltarLogin);
        animacaoEntrada = AnimationUtils.loadAnimation(this,R.anim.entrada_botao);
        animacaoSaida=  AnimationUtils.loadAnimation(this,R.anim.saida_botao);
        animacaoSaidaLeft = AnimationUtils.loadAnimation(this,R.anim.saida_left);

        botaoBarbeiro.startAnimation(animacaoEntrada);
        botaoCliente.startAnimation(animacaoEntrada);
        botaoLogin.startAnimation(animacaoEntrada);

        botaoBarbeiro.setVisibility(View.VISIBLE);
        botaoCliente.setVisibility(View.VISIBLE);
        botaoLogin.setVisibility(View.VISIBLE);

        botaoBarbeiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                botaoCliente.startAnimation(animacaoSaida);
                botaoLogin.startAnimation(animacaoSaidaLeft);
                botaoCliente.setVisibility(View.INVISIBLE);
                botaoLogin.setVisibility(View.INVISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {


                        openCadastroBarbeiro();
                    }
                },400);



            }
        });

        botaoCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                botaoBarbeiro.startAnimation(animacaoSaida);
                botaoLogin.startAnimation(animacaoSaidaLeft);

                botaoBarbeiro.setVisibility(View.INVISIBLE);
                botaoLogin.setVisibility(View.INVISIBLE);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        openCadastroCliente();
                    }
                },400);



            }
        });

        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                botaoBarbeiro.startAnimation(animacaoSaida);
                botaoCliente.startAnimation(animacaoSaida);

                botaoCliente.setVisibility(View.INVISIBLE);
                botaoBarbeiro.setVisibility(View.INVISIBLE);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        openLogin();
                    }
                },400);



            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        botaoBarbeiro.startAnimation(animacaoEntrada);
        botaoCliente.startAnimation(animacaoEntrada);
        botaoLogin.startAnimation(animacaoEntrada);

        botaoBarbeiro.setVisibility(View.VISIBLE);
        botaoCliente.setVisibility(View.VISIBLE);
        botaoLogin.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        botaoBarbeiro.startAnimation(animacaoEntrada);
        botaoCliente.startAnimation(animacaoEntrada);
        botaoLogin.startAnimation(animacaoEntrada);

        botaoBarbeiro.setVisibility(View.VISIBLE);
        botaoCliente.setVisibility(View.VISIBLE);
        botaoLogin.setVisibility(View.VISIBLE);

    }

    public void openLogin(){
        Intent intent = new Intent(BarbeiroCliente.this,Login.class);
        startActivity(intent);
    }
    public void openCadastroCliente(){
        Intent intent = new Intent(BarbeiroCliente.this,CadastroCliente.class);
        startActivity(intent);
    }
    public void openCadastroBarbeiro(){
        Intent intent = new Intent(BarbeiroCliente.this, CadastroBarbeiro.class);
        startActivity(intent);
    }

}