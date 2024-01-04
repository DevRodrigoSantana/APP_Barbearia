package com.santos.barbeariafacil.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.santos.barbeariafacil.R;
import com.santos.barbeariafacil.config.ConfigFirebase;

public class Cadastro extends AppCompatActivity {

    private TextInputEditText campoNome, campoEmail, campoSenha, campoConfirmaSenha,campoTelefone;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        campoNome = findViewById(R.id.edit_nome);
        campoEmail = findViewById(R.id.edit_email);
        campoSenha = findViewById(R.id.edit_senha);
        campoConfirmaSenha = findViewById(R.id.edit_confirma_senha);
        campoTelefone = findViewById(R.id.edit_celular);

    }
    public void cadastroUsuario(){
        autenticacao = ConfigFirebase.getFirebaseAutenticaca();
        autenticacao.createUserWithEmailAndPassword("",""

        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

            }
        });
    }

    public void validarCadastroUsuario(View view){

        String textNome = campoNome.getText().toString();
        String textEmail = campoEmail.getText().toString();
        String textSenha = campoSenha.getText().toString();
        String textConfirmaSenha = campoConfirmaSenha.getText().toString();
        String textTelefone = campoTelefone.getText().toString();

        if(!textNome.isEmpty()){
            if(!textEmail.isEmpty()){
                if (!textSenha.isEmpty()){
                    if(!textConfirmaSenha.isEmpty()){
                        if(!textTelefone.isEmpty()){
                            if(textSenha  != textConfirmaSenha){

                            }else {
                                Toast.makeText(Cadastro.this,
                                        "Preencha senhas são diferentes corretamente!",
                                        Toast.LENGTH_SHORT).show();
                            }

                        }else{
                            Toast.makeText(Cadastro.this,
                                    "Preencha o campo  telefone corretamente!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(Cadastro.this,
                                "Preencha a senha de confirmação corretamente!",
                                Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(Cadastro.this,
                            "Preencha o senha corretamente!",
                            Toast.LENGTH_SHORT).show();

                }

            }else{
                Toast.makeText(Cadastro.this,
                        "Preencha o Email corretamente!",
                        Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(Cadastro.this,
                    "Preencha o nome corretamente!",
                    Toast.LENGTH_SHORT).show();
        }


    }
}