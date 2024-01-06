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
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.santos.barbeariafacil.R;
import com.santos.barbeariafacil.config.ConfigFirebase;
import com.santos.barbeariafacil.model.Usuario;

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
    public void cadastroUsuario(Usuario user){
        autenticacao = ConfigFirebase.getFirebaseAutenticaca();
        autenticacao.createUserWithEmailAndPassword(user.getEmail(),user.getSenha()

        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Cadastro.this,
                            "Sucesso ao cadastrar usuario!",
                            Toast.LENGTH_SHORT).show();


                } else {
                    String excepcao = "";
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthWeakPasswordException e) {//excepção para senha de autenticação fraca
                        excepcao = "Digite uma senha mais forte!";
                    } catch (FirebaseAuthInvalidCredentialsException e) {//excepção para e-mail invalido
                        excepcao = "Por favor, digite um e-mail válido";
                    } catch (FirebaseAuthUserCollisionException e) {//excepção para conta ja cadastrada
                        excepcao = "está conta já foi cadastrada!";
                    } catch (Exception e) {
                        excepcao = "Erro ao cadastrar usuário:" + e.getMessage();//excepção  para erro ainda não esclarecido
                        e.printStackTrace();
                    }

                }
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
                            if(textSenha.equals(textConfirmaSenha)){

                                Usuario usuario = new Usuario();
                                usuario.setNomeCompleto(textNome);
                                usuario.setEmail(textEmail);
                                usuario.setSenha(textSenha);
                                usuario.setConfirmarSenha(textConfirmaSenha);
                                usuario.setTelefone(textTelefone);

                                cadastroUsuario(usuario);

                            }else {
                                Toast.makeText(Cadastro.this,
                                        "Preencha corretamente as senhas!",
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