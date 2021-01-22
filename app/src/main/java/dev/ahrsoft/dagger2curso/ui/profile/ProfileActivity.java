package dev.ahrsoft.dagger2curso.ui.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import javax.inject.Inject;

import dev.ahrsoft.dagger2curso.R;
import dev.ahrsoft.dagger2curso.di.BaseApp;
import dev.ahrsoft.dagger2curso.model.User;
import dev.ahrsoft.dagger2curso.ui.WebService.WebServiceActivity;
import dev.ahrsoft.dagger2curso.ui.login.LoginActivity;

public class ProfileActivity extends AppCompatActivity implements Profile.View {

    private EditText etName, etEdad;
    private TextView tvLogout;
    private Button btnNextActivity, btUpdate;

    @Inject
    Profile.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ((BaseApp)getApplication()).getAppComponent().inject(this);
        setUpView();
        presenter.setView(this);

    }

    private void setUpView() {
        etName = findViewById(R.id.etName);
        etEdad = findViewById(R.id.etEdad);
        tvLogout = findViewById(R.id.tvLogout);

        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.logout();
            }
        });

        btnNextActivity = findViewById(R.id.btNextActivity);
        btnNextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, WebServiceActivity.class));
            }
        });

        btUpdate = findViewById(R.id.btUpdate);
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setEdad(etEdad.getText().toString());
                user.setUsername(etName.getText().toString());
                presenter.updateUser(user);
            }
        });



    }

    @Override
    public void showUser(User user) {
        etEdad.setText(user.getEdad());
        etName.setText(user.getUsername());
    }

    @Override
    public void logout() {
        Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}