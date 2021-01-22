package dev.ahrsoft.dagger2curso.di;

import javax.inject.Singleton;

import dagger.Component;
import dev.ahrsoft.dagger2curso.ui.WebService.WebServiceActivity;
import dev.ahrsoft.dagger2curso.ui.login.LoginActivity;
import dev.ahrsoft.dagger2curso.ui.profile.ProfileActivity;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(LoginActivity loginActivity);
    void inject(ProfileActivity profileActivity);
    void inject(WebServiceActivity webServiceActivity);
}
