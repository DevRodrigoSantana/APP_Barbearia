package com.santos.barbeariafacil.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.santos.barbeariafacil.R;
import com.santos.barbeariafacil.config.ConfigFirebase;
import com.santos.barbeariafacil.model.Usuario;

public class Login extends AppCompatActivity {

    private EditText campoEmail,campoSenha;
    private Button botaoEntrar;
    private Usuario user;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //atividade para autenticar useer

        campoEmail =findViewById(R.id.editEmail);
        campoSenha =findViewById(R.id.editSenha);
        botaoEntrar = findViewById(R.id.buttonEntrar);

        botaoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String textoEmail = campoEmail.getText().toString();
                String textoSenha = campoSenha.getText().toString();
                if(!textoEmail.isEmpty()){
                    if(!textoSenha.isEmpty()){

                        user = new Usuario();

                        user.setEmail(textoEmail);
                        user.setSenha(textoSenha);
                        validarLogin();

                    }else{
                        Toast.makeText(Login.this,
                                "Preencha a senha corretamante!",
                                Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(Login.this,
                            "Preencha o Seu Email Corretamente!",Toast.LENGTH_SHORT).show();
                }

            }
        });




        TextView textoNaotemConta = findViewById(R.id.textNaoTemConta);
        String textoClicavel = getString(R.string.naoTemConta);

        int inicioParteClicavel = textoClicavel.indexOf("Cadastre-se");
        int fimParteClicavel = inicioParteClicavel + "Cadastre-se".length();

        SpannableString spannableString = new SpannableString(textoClicavel);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                openEscolha();


            }
        };


        spannableString.setSpan(clickableSpan, inicioParteClicavel, fimParteClicavel, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


        textoNaotemConta.setText(spannableString);


        textoNaotemConta.setMovementMethod(LinkMovementMethod.getInstance());

    }

    @Override
    protected void onStart() {
        super.onStart();
        verificarUsuarioLogado();
    }
    public  void verificarUsuarioLogado(){
        autenticacao= ConfigFirebase.getFirebaseAutenticaca();
        if(autenticacao.getCurrentUser() != null){
            openPrincipal();

        }


    }

    public void openCadastro(){
        Intent intent = new Intent(Login.this, CadastroCliente.class);
        startActivity(intent);

    }
    public void validarLogin(){
        autenticacao = ConfigFirebase.getFirebaseAutenticaca();
        autenticacao.signInWithEmailAndPassword(
                user.getEmail(),
                user.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    openPrincipal();


                }else{

                            String excepcao = "";
                    try {
                        throw task.getException();
                    }catch(FirebaseAuthInvalidUserException e) {
                        excepcao = "Usuário Não está cadastrado.";
                    }catch ( FirebaseAuthInvalidCredentialsException e){
                        excepcao = "E-mail e senha não correspondem a um usuário cadastrado";
                    } catch (Exception e) {
                        excepcao = "Erro ao logar" + e.getMessage();//excepção  para erro ainda não esclarecido
                        e.printStackTrace();
                    }

                    Toast.makeText(Login.this,
                            excepcao,Toast.LENGTH_SHORT).show();

                }
            }
        });


    }
    public void openPrincipal(){
        Intent intent = new Intent(Login.this,Principal.class);
        startActivity(intent);
        finish();
    }
    public void openEscolha(){
        Intent intent = new Intent(Login.this,BarbeiroCliente.class);
        startActivity(intent);

    }
}