package dev.ahrsoft.dagger2curso.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;

import dev.ahrsoft.dagger2curso.R;
import dev.ahrsoft.dagger2curso.di.BaseApp;
import dev.ahrsoft.dagger2curso.ui.profile.ProfileActivity;

public class LoginActivity extends AppCompatActivity implements Login.View{

    private EditText etUser, etPassword;
    private Button btLogin;

    @Inject
    Login.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ((BaseApp)getApplication()).getAppComponent().inject(this);
        setUpView();
    }

    private void setUpView() {
        presenter.setView(this);

        etUser = findViewById(R.id.etUser);
        etPassword = findViewById(R.id.etPassword);
        btLogin = findViewById(R.id.btLogin);

        btLogin.setOnClickListener(v -> {
            presenter.validaUser(etUser.getText().toString(), etPassword.getText().toString());
        });
    }

    @Override
    public void usuarioValido() {
        startActivity(new Intent(this, ProfileActivity.class));
    }

    @Override
    public void error() {
        Toast.makeText(this, "El usuario no existe =( ", Toast.LENGTH_LONG).show();
    }
}