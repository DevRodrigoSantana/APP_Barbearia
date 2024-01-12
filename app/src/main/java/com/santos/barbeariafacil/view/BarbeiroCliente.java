package com.santos.barbeariafacil.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.santos.barbeariafacil.R;

public class BarbeiroCliente extends AppCompatActivity {

    private Button botaoBarbeiro, botaoCliente, botaoLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barbeiro_cliente);

        botaoBarbeiro = findViewById(R.id.button_barbeiro);
        botaoCliente = findViewById(R.id.button_cliente);
        botaoLogin = findViewById(R.id.buttonVoltarLogin);

        botaoBarbeiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCadastroBarbeiro();

            }
        });

        botaoCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCadastroCliente();

            }
        });

        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLogin();

            }
        });

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
        Intent intet = new Intent(BarbeiroCliente.this, CadastroBarbeiro.class);
        startActivity(intet);
    }





}