package dev.ahrsoft.dagger2curso.ui.WebService;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import javax.inject.Inject;

import dev.ahrsoft.dagger2curso.R;
import dev.ahrsoft.dagger2curso.di.BaseApp;
import dev.ahrsoft.dagger2curso.model.User;
import dev.ahrsoft.dagger2curso.ui.profile.ProfileActivity;

public class WebServiceActivity extends AppCompatActivity implements WebService.View {

    private Button btVolverProfile, btHacerPeticionWeb;
    private TextView tvUserName, tvEdad;

    @Inject
    WebService.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_service);
        ((BaseApp)getApplication()).getAppComponent().inject(this);
        setUpView();
        presenter.setView(this);
    }

    private void setUpView() {
        btVolverProfile = findViewById(R.id.btVolverAProfile);
        btHacerPeticionWeb = findViewById(R.id.btHacerPeticionWeb);
        tvEdad = findViewById(R.id.tvEdad);
        tvUserName = findViewById(R.id.tvUserName);

        btHacerPeticionWeb.setOnClickListener(v -> {
            presenter.solicitudWebService();
        });

        btVolverProfile.setOnClickListener(v -> {

            startActivity(new Intent(this, ProfileActivity.class));
        });
    }

    @Override
    public void showUser(User user) {
        tvUserName.setText(user.getUsername());
        tvEdad.setText(user.getEdad());
    }
}