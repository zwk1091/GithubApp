package com.example.wenkun.githubapp;

import java.util.Observable;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Wenkun on 2017/12/21.
 */

public interface GithubService {
    @GET("/users/{user}")
    rx.Observable<Github> getUser(@Path("user") String user);
}
