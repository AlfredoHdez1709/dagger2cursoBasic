package dev.ahrsoft.dagger2curso.api;

import java.util.List;

import dev.ahrsoft.dagger2curso.model.GitHubRepo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiClient {
    @GET("/users/{user}/repos")
    Call<List<GitHubRepo>> getListRepoForUser(@Path("user") String user);
}
