package dev.ahrsoft.dagger2curso.ui.WebService;

import android.util.Log;

import java.util.List;

import dev.ahrsoft.dagger2curso.api.ApiClient;
import dev.ahrsoft.dagger2curso.model.GitHubRepo;
import dev.ahrsoft.dagger2curso.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WebServicePresenter implements WebService.Presenter {

    private User user;
    private ApiClient api;
    private WebService.View view;

    public WebServicePresenter(User user, ApiClient apiClient){
        this.user = user;
        this.api = apiClient;
    }

    @Override
    public void setView(WebService.View view) {
        this.view = view;
        view.showUser(user);
    }

    @Override
    public void solicitudWebService() {
        Call<List<GitHubRepo>> call = api.getListRepoForUser("AlfredoHdez1709");
        call.enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
                if (response.isSuccessful()){
                    for (GitHubRepo repo : response.body()){
                        Log.d("TAG1", "Respositorio: Nombre: " +repo.getName() + " Avatar: " + repo.getOwner().getAvatarUrl() + "Login: " + repo.getOwner().getLogin());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {

            }
        });
    }
}
