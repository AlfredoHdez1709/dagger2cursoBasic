package dev.ahrsoft.dagger2curso.di;

import android.app.Application;
import android.content.Context;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dev.ahrsoft.dagger2curso.api.ApiClient;
import dev.ahrsoft.dagger2curso.model.User;
import dev.ahrsoft.dagger2curso.ui.WebService.WebService;
import dev.ahrsoft.dagger2curso.ui.WebService.WebServicePresenter;
import dev.ahrsoft.dagger2curso.ui.login.Login;
import dev.ahrsoft.dagger2curso.ui.login.LoginPresenter;
import dev.ahrsoft.dagger2curso.ui.profile.Profile;
import dev.ahrsoft.dagger2curso.ui.profile.ProfilePresenter;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    private final Application application;

    public AppModule(Application application){
        this.application = application;
    }

    @Provides
    @Singleton
    public Application provideApplication(){
        return application;
    }

    @Provides
    @Singleton
    public Context provideApplicationContext(){
        return application;
    }

    /*
    User
     */

    @Provides
    @Singleton
    public User provideUser(){
        return new User();
    }

    /*
    Grafo retrofit
     */

    private static final String BASE_URL = "https://api.github.com";
    @Singleton
    @Provides
    GsonConverterFactory providesGsonConverterFactory(){
        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create();
        return gsonConverterFactory;
    }

    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient(){
        return new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(OkHttpClient client, GsonConverterFactory converterFactory){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(converterFactory)
                .client(client)
                .build();
    }

    @Singleton
    @Provides
    ApiClient provideApiClient(Retrofit retrofit){
        return retrofit.create(ApiClient.class);
    }

    /*
    Grafo de activitys
     */

    @Provides
    @Singleton
    Login.Presenter providePresenterLogin(User user){
        return new LoginPresenter(user);
    }

    @Provides
    @Singleton
    Profile.Presenter providePresenteProfile(User user){
        return  new ProfilePresenter(user);
    }

    @Provides
    @Singleton
    WebService.Presenter providePresenterWebService(User user, ApiClient apiClient){
        return new WebServicePresenter(user, apiClient);
    }



}
